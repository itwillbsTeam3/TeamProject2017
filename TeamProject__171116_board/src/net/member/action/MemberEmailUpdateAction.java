package net.member.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberEmailUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberEmailUpdateAction execute()");
		ServletContext context=request.getServletContext();
		String realPath =context.getRealPath("/upload");
		int maxSize = 5*1024*1024;		
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(realPath);
		System.out.println(context);
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = new MemberBean();
		
		mb.setId(multi.getParameter("id"));
		mb.setPass(multi.getParameter("pass"));
		mb.setName(multi.getParameter("name"));
		mb.setAge(Integer.parseInt(multi.getParameter("age")));
		mb.setGender(multi.getParameter("gender"));
		mb.setEmail(multi.getParameter("email"));
		mb.setPhone(multi.getParameter("phone"));
		mb.setMobile(multi.getParameter("mobile"));
		mb.setAddress(multi.getParameter("address"));
		mb.setAddress2(multi.getParameter("address2"));
		mb.setZip_code(multi.getParameter("zip_code"));
		mb.setSelfinfo(multi.getParameter("selfinfo"));
		mb.setProfile(multi.getFilesystemName("profile"));
		if(multi.getFilesystemName("profile") == null){
	        mb.setProfile(multi.getParameter("PreFile"));
		}else{
			mb.setProfile(multi.getFilesystemName("profile"));
		}
		
		int check = mdao.updateMember(mb);
		
		if(check==1){
		    HttpSession session = request.getSession();
		    session.setAttribute("id", mb.getId());
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('변경성공.');");
			out.println("location.href='./Main.me';");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==0){		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('변경실패.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;		
		}else if(check==-1){		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('변경실패．');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;			
		}
		
		return null;
	}

}

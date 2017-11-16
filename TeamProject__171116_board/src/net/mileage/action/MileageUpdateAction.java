package net.mileage.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.history.db.HistoryBean;
import net.history.db.HistoryDAO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.mileage.db.MileBean;
import net.mileage.db.MileDAO;

public class MileageUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MileageUpdateAction execute()");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
//		int num = Integer.parseInt(request.getParameter("num"));
		int mileage = Integer.parseInt(request.getParameter("mileage"));
		
		MileDAO mdao = new MileDAO();
		MileBean mb = new MileBean();
		
		mb.setId(id);
		mb.setNum(1);		
//		mb.setNum(num);
		mb.setMileage(mileage);
		
		int check = mdao.chargeMileage(mb);
		
		
		if(check==1){
		    session = request.getSession();
		    session.setAttribute("id", mb.getId());
		    HistoryDAO hdao = new HistoryDAO();
		    HistoryBean hb = new HistoryBean();
		    hb.setFlag(0);
		    hb.setId(mb.getId());
		    hb.setMileage(mileage);
		    hdao.insertHistory(hb);
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정상적으로 충전되었습니다.');");
			out.println("location.href='./Main.me';");
			out.println("</script>");
			out.close();
			return null;
			
		}else if(check==0){		
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('충전실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;		
		}
		return null;
	}
	
}

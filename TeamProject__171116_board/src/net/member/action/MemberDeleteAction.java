package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDeleteAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.deleteMember(id,pass);
		
		if (check == 1) {
			HttpSession session = request.getSession();
			session.invalidate();
			ActionForward forward = new ActionForward();
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
			
		} else if (check == 0) {
			// check==0 아이디 있고 비밀번호 틀린경우
			// "비밀번호틀림" 뒤로이동
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else {
			// check==-1 아이디 없음 "아이디없음" 뒤로이동
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
	
	}

}

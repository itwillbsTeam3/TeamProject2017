package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberCheckPwAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberCheckPwAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.checkPw(id, pass);
		
		if(check == 1){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('비밀번호 확인 성공'); location.href='./MemberInfoAction.me'; </script>");
			out.close();
			return null;
		}else if(check == 0){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('비밀번호 틀림'); history.back(); </script>");
			out.close();
			return null;
		}else{ //check == -1
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('아이디 없음'); history.back(); </script>");
			out.close();
			return null;
		}
		
	}
	

}

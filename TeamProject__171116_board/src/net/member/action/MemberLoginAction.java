package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.idCheck(id, pass);
		
		if(check == 1){
			System.out.println("로그인 성공");
			HttpSession session = request.getSession(); session.setAttribute("id", id);
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('로그인 성공'); location.href='./Main.me'; </script>");
			out.close();
			return null;
			
		}else if(check == 0){
			System.out.println("비밀번호 틀림, 로그인 실패");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('비밀번호 틀림, 로그인 실패'); history.back(); </script>");
			out.close();
			return null;
		
		}else if(check == -1){
			System.out.println("아이디 없음, 로그인 실패");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('입력값 없음, 로그인 실패'); history.back(); </script>");
			out.close();
			return null;
		}
	
		return null;
	}

}

package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberCheckAuthAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberCheckPwAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id =(String)session.getAttribute("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.checkAuth(id, name, email, mobile);
		
		if(check == 1){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('본인인증 확인 성공'); location.href='./Milemain.mi'; </script>");
			out.close();
			return null;
		}else if(check == 0){
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('본인인증 실패'); history.back(); </script>");
			out.close();
			return null;
		}else{ //check == -1
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'> alert('없음'); history.back(); </script>");
			out.close();
			return null;
		}
	}

}

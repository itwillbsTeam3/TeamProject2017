package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLogoutAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(); 
        //세션값 초기화
        session.invalidate();
        
        
        //자바스크립트를 뿌리고 이동
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'> alert('로그아웃 되었습니다.'); location.href='./Main.me'; </script>");
		out.close();
		return null;
		
        //그냥 이동
//		ActionForward forward = new ActionForward();
//        //가상의 파일주소 이동시   true  *.me(do)?
//        forward.setRedirect(true);
//        forward.setPath("./Main.me");
//        
//        return forward;
	}

}

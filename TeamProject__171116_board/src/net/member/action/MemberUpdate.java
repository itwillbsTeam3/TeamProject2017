package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdate execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = new MemberBean();
		
		mb = mdao.getMember(id);
		
		request.setAttribute("mb", mb);
		
		ActionForward forward = new ActionForward();
	    forward.setPath("./Member/Update.jsp");
	    forward.setRedirect(false);
	    return forward;
	}

}

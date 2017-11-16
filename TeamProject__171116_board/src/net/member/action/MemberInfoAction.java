package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfoAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = null; id =(String)session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.getMember(id);		
		
		request.setAttribute("mb", mb);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./Member/Info.jsp");		
		return forward;
	}
	
}

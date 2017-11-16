package net.mileage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mileage.db.MileBean;
import net.mileage.db.MileDAO;


public class MileageUpdate implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MileageUpdate execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MileDAO mdao = new MileDAO();
		MileBean mb = mdao.getMileage(id);
	
		request.setAttribute("mb", mb);
		
		ActionForward forward = new ActionForward();
	    forward.setPath("./Mileage/Milemain.jsp");
	    forward.setRedirect(false);
	    return forward;
	}

}

package net.mileage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mileage.db.MileBean;
import net.mileage.db.MileDAO;

public class MileageInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberInfoAction execute()");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = null; id =(String)session.getAttribute("id");
		
		MileDAO midao = new MileDAO();
		MileBean mibean = midao.getMileage(id);
		
		request.setAttribute("mibean", mibean);
		return null;
		
	}
	

}

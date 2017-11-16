package net.booking.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Host.db.HostingBean;


public class Booking implements Action{
@Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session = request.getSession();
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	HostingBean hb=new HostingBean();
	hb.setNum(Integer.parseInt(request.getParameter("num")));
	
	
	request.setAttribute("hb", hb);
	ActionForward forward = new ActionForward();
	forward.setRedirect(false);
	forward.setPath("./host/booking.jsp");
	return forward;
}
}

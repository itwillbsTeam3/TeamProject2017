package net.history.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.db.BookingBean;
import net.history.db.HistoryDAO;

public class HosthistoryAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		reponse.setContentType("text/html; charset=UTF-8");
		PrintWriter out = reponse.getWriter();
//		String id="test";
//		if(id==null){
//			out.println("<script>");
//			out.println("alert('로그인 상태가 아닙니다.');");
//			out.println("location.href = './Main.me'");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
		HistoryDAO hdao = new HistoryDAO();
		ArrayList<BookingBean> hb = hdao.getHosthistory(id);
		System.out.println(hb.size());
		request.setAttribute("hb", hb);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./Member/Hosthistory.jsp");
		System.out.println("Hosthistory execute()");
		return forward;
	}

}

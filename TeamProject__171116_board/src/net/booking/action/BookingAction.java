package net.booking.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.db.BookingBean;
import net.booking.db.BookingDAO;
import net.history.db.HistoryBean;
import net.history.db.HistoryDAO;
import net.mileage.db.MileDAO;


public class BookingAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("예약 시작");
	
	request.setCharacterEncoding("UTF-8");
	HttpSession session = request.getSession();
	
	int check = 0;
	BookingBean bb = new BookingBean();
	BookingDAO bkdao = new BookingDAO();
	bb.setSubject(request.getParameter("subject"));
	bb.setHost_id(request.getParameter("host_id"));
	bb.setGuest_id((String)session.getAttribute("id"));//(임시값)
	bb.setCheckin(request.getParameter("checkin"));
	bb.setCheckout(request.getParameter("checkout"));
	bb.setEtc(request.getParameter("etc"));
	
	Mydatecarculator master = new Mydatecarculator();
	int y,m,d,yy,mm,dd;
	String []qe = bb.getCheckin().split("-");
	yy =Integer.parseInt(qe[0]);
	mm =Integer.parseInt(qe[1]);
	dd =Integer.parseInt(qe[2]);
	String []qee = bb.getCheckout().split("-");
	y =Integer.parseInt(qee[0]);
	m =Integer.parseInt(qee[1]);
	d =Integer.parseInt(qee[2]);
	int interval = master.GetDifferenceOfDate(y, m, d, yy, mm, dd);
	bb.setPrice(Integer.parseInt(request.getParameter("price"))*interval);
	bb.setEtc(request.getParameter("etc"));

	MileDAO miledao = new MileDAO();
	
	check = bkdao.checkbooking(bb.getHost_id(),bb.getCheckin(),bb.getCheckout());
	System.out.println(check);
	if(check == 0){
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('예약할수 없는 기간입니다. 날짜를 정확히 입력해주세요.');");
		out.println("window.close();");
		out.println("</script>");
		out.close();
		return null;
	}
	check = miledao.useMileage(bb.getGuest_id(),bb.getPrice(),bb.getCheckin(),bb.getCheckout(),bb.getHost_id());
	//호스트에게 마일리지 주기 
	if(check==-1){		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('잔액이 부족합니다.마일리지를 충전해주세요.');");
		out.println("location.href='./Main.me' ");
		out.println("</script>");
		out.close();
		return null;		
	}
	miledao.updateMileage(bb.getHost_id(), bb.getGuest_id(), bb.getPrice());
	bkdao.insertBooking(bb);
	HistoryDAO historydao = new HistoryDAO();
	HistoryBean htb = new HistoryBean();
	htb.setFlag(1);
	htb.setId(bb.getGuest_id());
	htb.setMileage(bb.getPrice());
	historydao.insertHistory(htb);
	
	htb.setFlag(2);
	htb.setId(bb.getHost_id());
	htb.setMileage(bb.getPrice());
	historydao.insertHistory(htb);
	
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<script>");
	out.println("alert('예약이 완료 되었습니다.');");
	out.println("location.href='./Main.me' ");
	out.println("</script>");
	out.close();
	return null;

	}
}

package net.history.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.booking.action.Mydatecarculator;
import net.booking.action.checkinout;
import net.booking.db.BookingBean;
import net.history.db.HistoryBean;
import net.history.db.HistoryDAO;
import net.refund.db.RefundDAO;

public class HistorydeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		System.out.println("컨텐트 처리 페이지");
		request.setCharacterEncoding("UTF-8");
		reponse.setContentType("text/html; charset=UTF-8");
		PrintWriter out = reponse.getWriter();
		
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		int Rnum=0;
		int flag = Integer.parseInt(request.getParameter("flag"));
		BookingBean hb = new BookingBean();
		HistoryBean htb = new HistoryBean();
		HistoryDAO hdao = new HistoryDAO();
		RefundDAO rdao = new RefundDAO();
		if(request.getParameter("num")==null){
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		if(flag==3){//예약자가 예약취소 신청시
			System.out.println(num);
			hdao.insertHistory(num);
//			int price =hb.getPrice();
//			String host_id=hb.getHost_id();
//			String guest_id=hb.getGuest_id();
//			//마일리지 게스트에게 가격만큼 추가
//			htb.setHost_id("host_id");
//			htb.setGuest_id("guest_id");
//			htb.setFlag(0);
//			htb.setPrice(price);
//			//환불내역에 넣는 dao호출
//			Rnum=rdao.appRefund(htb);
//			
		}else if(flag==4){ //호스트가 예약취소 승인사
			hdao.getHistory(num);
			int price =hb.getPrice();
			String host_id=hb.getHost_id();
			String guest_id=hb.getGuest_id();
			
			//마일리지 호스트에서 가격만큼 차감
			rdao.refundMileage_host(host_id,price);
			rdao.refundMileage_guest(guest_id,price);
			//호스트 결제 내역에 환불 내용 들어가는 구문
			htb.setFlag(4);
			htb.setId(hb.getHost_id());
			htb.setMileage(hb.getPrice());
			hdao.insertHistory(htb);
			
			//호스트 결제 내역에 환불 내용 들어가는 구문
			htb.setFlag(3);
			htb.setId(hb.getGuest_id());
			htb.setMileage(hb.getPrice());
			hdao.insertHistory(htb);
			//예약테이블에서 해당 내용 삭제시키는 구문
			hdao.deleteHistory(num);
		}
		System.out.println("종료 페이지 이동");
		out.println("<script>");
		if(flag==3){
		out.println("alert('예약취소가 신청되었습니다.');");
		}else{
		out.println("alert('예약취소가 승인되었습니다 .');");
		}
		out.println("history.back();");
		out.println("</script>");
		out.close();
		System.out.println("종료 페이지 끝");
		return null;
	}
}

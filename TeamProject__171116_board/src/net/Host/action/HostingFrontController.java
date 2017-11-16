package net.Host.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Host.action.*;




public class HostingFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가상주소 가져오기
		//  http://localhost:8080/Model2/OrderStar.or
		//  /Model2/OrderStar.or
		String requestURI=request.getRequestURI();
		//  /Model2
		String contextPath=request.getContextPath();
		//  /OrderStar.or
		String command = requestURI.substring(contextPath.length());
		System.out.println("controller");
		Action action=null;
		ActionForward forward=null;
		
		System.out.println(command);
		
		if(command.equals("/HostinginsertAction.ho")){
			System.out.println("HostinginsertAction");
			action = new HostinginsertAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/Hostinginsert.ho")){
			System.out.println("호스팅 글쓰기");
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./host/Insert.jsp");
		}
//		else if(command.equals("/HostingContent.ho")){
//			forward=new ActionForward();
//			forward.setRedirect(false);
//			forward.setPath("./host/content.jsp");
//		}	
		else if(command.equals("/HostingContentAction.ho")){
			action=new HostingContentAction();
			try{
				forward=action.execute(request,response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(command.equals("/HostingBooking.ho")){
			System.out.println("Hostingbooking");
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./host/booking.jsp");
		}
	
		else if(command.equals("/Hostingupdate.ho")){
			System.out.println("호스트글 업데이트 페이지");
			action = new Hostingupdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		else if(command.equals("/HostupdateAction.ho")){
			System.out.println("호스트글 업데이트 액션");
			action = new HostupdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}/////////////////////////////////////////////////
		else if(command.equals("/HostingListAction.ho")){
			System.out.println("호스트 리스트 액션");
			action = new HostingListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   }
		
		else if(command.equals("/BoardContent.ho")){
			System.out.println("BoardContent");
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./host/Content.jsp");
	   }
		
		//이동
		if(forward!=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}

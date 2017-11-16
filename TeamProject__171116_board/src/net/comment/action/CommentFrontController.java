package net.comment.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class CommentFrontController extends HttpServlet{
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
		
		if(command.equals("/recommentaction.co")){
			System.out.println("코맨트에 코맨트달기");
			action = new recommentaction();
 			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/insertcomment.co")){
			System.out.println("코맨트 넣기");
			action = new insertcomment();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
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

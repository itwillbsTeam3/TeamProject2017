package net.message.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");
		String requestURI=request.getRequestURI();
		System.out.println("URI주소 : "+requestURI);
		String contextPath=request.getContextPath();
		System.out.println("프로젝트주소 : "+contextPath);
		System.out.println("프로젝트주소 길이 : "+contextPath.length());
		String command=requestURI.substring(contextPath.length());
		System.out.println("가상주소뽑기 : "+command);
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/MessageSending.ms")){
			System.out.println("MessageSending");
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Message/MessageSending.jsp");
		}
		else if(command.equals("/MessageSendingAction.ms")){
			System.out.println("MessageSendingAction");
			action = new MessageSendingAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//3. 이동
		// 이동정보 있는경우
		if(forward!=null){
			if(forward.isRedirect()){
				//이동방식 true sendRedirect
				response.sendRedirect(forward.getPath());
			}else{
				//이동방식 false forward
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MessageFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MessageFrontController doPost()");
		doProcess(request, response);
	}
	}



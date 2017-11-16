package net.chat.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatFrontController extends HttpServlet{

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
		
		if(command.equals("/Chat.ch")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Chat/chat.jsp");
			System.out.println("채팅처리");
		}else if(command.equals("/ChatIdFind.ch")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Chat/find.jsp");
			System.out.println("채팅아이디검색처리");
		}else if(command.equals("/ChatIdCheck.ch")){
			System.out.println("채팅 아이디 검색 동작 처리");
			action=new ChatIdCheckAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ChatList.ch")){
			System.out.println("채팅 리스트 동작 처리");
			action=new ChatListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/ChatSubmit.ch")){
			System.out.println("채팅 보내기 동작 처리");
			action=new ChatSubmitAction();
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
		System.out.println("MemberFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()");
		doProcess(request, response);
	}
}

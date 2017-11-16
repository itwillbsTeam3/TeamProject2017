package net.alram.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.email.action.ActionForward;

public class AlramFrontController extends HttpServlet{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AlramFrontController doProcess()");
		String requestURI=request.getRequestURI();
		System.out.println("URI주소 : "+requestURI);
		String contextPath=request.getContextPath();
		System.out.println("프로젝트주소 : "+contextPath);
		System.out.println("프로젝트주소 길이 : "+contextPath.length());
		String command=requestURI.substring(contextPath.length());
		System.out.println("가상주소뽑기 : "+command);
		ActionForward forward=null;
		Action action=null;
		if(command.equals("/Alram.ar")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./alram/alram.jsp");
			System.out.println("알림 처리");
		}else if(command.equals("/Sysalram.ar")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./alram/sysalram.jsp");
			System.out.println("알림 동작 처리");
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
		System.out.println("AlramFrontController doGet()");
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AlramFrontController doPost()");
		doProcess(request, response);
	}
	

}

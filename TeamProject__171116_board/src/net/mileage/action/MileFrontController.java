package net.mileage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MileFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MileFrontController doProcess()");
		String requestURI=request.getRequestURI();
		System.out.println("URI주소 : "+requestURI);
		String contextPath=request.getContextPath();
		System.out.println("프로젝트주소 : "+contextPath);
		System.out.println("프로젝트주소 길이 : "+contextPath.length());
		String command=requestURI.substring(contextPath.length());
		System.out.println("가상주소뽑기 : "+command);
		ActionForward forward=null;
		Action action=null;
		
		if(command.equals("/Milemain.mi")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Mileage/Milemain.jsp");
			System.out.println("마일리지메인");
		}
		if(command.equals("/MileUpdate.mi")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Mileage/MileUp.jsp");
			System.out.println("마일리지 충전");
		}else if(command.equals("/MileageUpdateAction.mi")){
			System.out.println("MileageUpdate");
			action=new MileageUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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

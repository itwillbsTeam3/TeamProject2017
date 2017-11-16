package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{

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
		if(command.equals("/MemberJoin.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/Join.jsp");
			System.out.println("회원가입처리");
		}else if(command.equals("/MemberJoinAction.me")){
			System.out.println("회원가입 동작 처리");
			action=new MemberJoinAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogin.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/Login.jsp");
			System.out.println("로그인처리");	
		}else if(command.equals("/MemberLoginAction.me")){
			System.out.println("로그인 동작 처리");			
			action=new MemberLoginAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberLogoutAction.me")){
			System.out.println("로그아웃 동작 처리");	
			action=new MemberLogoutAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberInfoAction.me")){
			System.out.println("회원정보 처리");	
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberCheckPwAction.me")){
//			System.out.println("회원정보 처리");	
			action = new MemberCheckPwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberCheckPw.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/CheckPw.jsp");
//			System.out.println("회원정보가입 전 약관 동의 처리");	
		}else if(command.equals("/MemberCheckAuthAction.me")){
			action = new MemberCheckAuthAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberCheckAuth.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/CheckAuth.jsp");
		}else if(command.equals("/MemberUpdate.me")){
			System.out.println("회원정보얻기 처리");	
			action=new MemberUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/MemberUpdateAction.me")){
			System.out.println("회원정보수정 처리");	
			action=new MemberUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberEmailUpdate.me")){
			System.out.println("회원이메일정보얻기 처리");	
			action=new MemberEmailUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/MemberEmailUpdateAction.me")){
			System.out.println("이메일 변경 처리");	
			action=new MemberEmailUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberJoinreg.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/Joinreg.jsp");
			System.out.println("회원가입 전 약관 동의 처리");	
		}else if(command.equals("/MemberIdCheck.me")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Member/IdDup.jsp");
			System.out.println("메인화면");
		}else if(command.equals("/Main.me")){
			System.out.println("메인");	
			action=new Main();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/MemberDelete.me")){
			System.out.println("회원탈퇴 처리");	
			action=new MemberDelete();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/MemberDeleteAction.me")){
			System.out.println("회원탈퇴 동작 처리");	
			action=new MemberDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberDel.me")){
			forward = new ActionForward();
			forward.setPath("./Member/MemberSelectDel.jsp");
			forward.setRedirect(false);
			System.out.println("회원관리 목록 회원삭제 처리");	
		}else if(command.equals("/MemberSearchId.me")){
			forward = new ActionForward();
			forward.setPath("./Member/SearchId.jsp");
			forward.setRedirect(false);
			System.out.println("회원 아이디 찾기 처리");	
		}else if(command.equals("/MemberSearchPwd.me")){
			forward = new ActionForward();
			forward.setPath("./Member/SearchPwd.jsp");
			forward.setRedirect(false);
			System.out.println("회원 비밀번호 찾기 처리");	
		}else if(command.equals("/MemberFindId.me")){
			forward = new ActionForward();
			forward.setPath("./Member/FindId.jsp");
			forward.setRedirect(false);
			System.out.println("회원 아이디 찾은 값 처리");	
		}else if(command.equals("/MemberFindPwd.me")){
			forward = new ActionForward();
			forward.setPath("./Member/FindPw.jsp");
			forward.setRedirect(false);
			System.out.println("회원 비밀번호 찾은 값 처리");	
		}else if(command.equals("/MemberList.me")){
			System.out.println("회원목록 처리");	
			action=new MemberList();
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

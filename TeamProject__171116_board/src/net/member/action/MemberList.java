package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberList execute()");
		request.setCharacterEncoding("UTF-8");
		
		//세션값 가져오기
		HttpSession session = request.getSession(); 
		String id = null; id = (String)session.getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		
		int count = mdao.getMemberCount();
		// 한페이지에 보여줄 글의 개수
		int pageSize = 5;
		// 현페이지가 몇페이지인지 가져오기(기본 1페이지)
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null)
			pageNum = "1"; // pageNum없으면 무조건 1페이지
		// 시작글 구하기 1 11 21 31 ... <= pageNum, pageSize 조합
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		// 끝행구하기
		int endRow = currentPage * pageSize;
		
		
		//MemberDAO mdao 객체 생성
		//MemberBean mb 객체 생성
//		MemberDAO mdao = new MemberDAO();
		MemberBean mb = new MemberBean();
		
		//MemberBean mb = 메서드 호출 getMember(id)
		ArrayList<MemberBean> memberList = null;
		
		if(count != 0){
			memberList = mdao.getMemberList(startRow,pageSize);
		}
		
		//데이터 저장
		request.setAttribute("mb", memberList);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		
		//이동 ./member/info.jsp		
		ActionForward forward = new ActionForward();
        
		//가상의 파일주소 이동시   true  *.me(do)?
		forward.setPath("./Member/MemberList.jsp");
		
		//forward.setPath("./MemberUpdateView.me");
        forward.setRedirect(false);
        return forward;
	}

}

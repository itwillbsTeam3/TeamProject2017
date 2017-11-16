package net.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Host.db.HostingBean;
import net.Host.db.HostingDAO;

public class Main implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("메인화면 액숀");
		HostingDAO htdao = new HostingDAO();
		ArrayList<HostingBean> list = new ArrayList<HostingBean>();
		list = htdao.getcontentList("price");
		request.setAttribute("plist", list);//가격순
		list = htdao.getcontentList("readcount");
		request.setAttribute("rlist", list);//조회순
		list = htdao.getcontentList("grade");
		request.setAttribute("glist", list);//평점순
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./Member/Main.jsp");
		
		return forward;
	}

}

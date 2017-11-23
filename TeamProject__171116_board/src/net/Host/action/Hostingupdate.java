package net.Host.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.Host.db.HostingBean;
import net.Host.db.HostingDAO;
import net.Option.db.HostingOptionBean;
import net.Option.db.HostingOptionDAO;

public class Hostingupdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("호스트 글수정 Action");
		HttpSession session  = request.getSession();
		String id =(String)session.getAttribute("id");
		HostingBean temp = new HostingBean();
		HostingDAO btdao = new HostingDAO();
		temp = btdao.getContent(id);
		request.setAttribute("temp", temp);
		
		HostingOptionBean hto = new HostingOptionBean();
		HostingOptionDAO htodao = new HostingOptionDAO();
		
		hto = htodao.getRoomOptionBoard(temp.getNum());
		request.setAttribute("hto", hto);

		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./host/hostUpdate.jsp");
		return forward;
	}

}

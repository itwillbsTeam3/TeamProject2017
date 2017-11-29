package net.Host.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Host.db.HostingBean;
import net.Host.db.HostingDAO;

public class HostingListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int pageNum;
		int size = 2;
		int start;
		int count = 0;
		String address;
		String checkin = null;
		String checkout = null;
		pageNum = Integer.parseInt(request.getParameter("pageNum"));
		//System.out.println(pageNum);
		start = (pageNum-1)*size; 
		if(request.getParameter("address")==null){
			address = "";
			request.setAttribute("address","부산 진구 부전동");
		}
		else{
			address = request.getParameter("address");
			request.setAttribute("address",address);
		}
		if(!(request.getParameter("checkin")==null && request.getParameter("checkout")==null)){
			if(!request.getParameter("checkin").equals("")){
				checkin = request.getParameter("checkin");
			}
			if(!request.getParameter("checkout").equals("")){
				checkout = request.getParameter("checkout");
			}
		}
		HostingDAO htdao = new HostingDAO();
		ArrayList<HostingBean> list = new ArrayList<HostingBean>();
		
		if(checkin==null && checkout==null ){
		System.out.println("날짜안드감");
		list = htdao.getcontentList(start, size, address);
		count = htdao.gethostingcount(address);
		}
		else{
		System.out.println("날짜드감");
		list = htdao.getcontentList(start,size,address,checkin,checkout);
		count = htdao.gethostingcount(address,checkin,checkout);
		}
		request.setAttribute("list",list);

		System.out.println("리스트 사이즈"+list.size());
		System.out.println("pageNum  = " + pageNum);
		request.setAttribute("pageNum",pageNum);
		if(list.size()==0){
			request.setAttribute("pagesize", 1);
		}
		else{
			int pagesize=((count-1)/size)+1;
			request.setAttribute("pagesize",pagesize);
			System.out.println("pageSize :"+pagesize);
		}
		
		System.out.println("pageNum :"+  pageNum);
		System.out.println(address);
		request.setAttribute("address", address);
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./host/List.jsp");
		return forward;
	}

}

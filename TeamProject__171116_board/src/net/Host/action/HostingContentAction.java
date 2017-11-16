package net.Host.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.BooleanLiteral;

import net.Host.db.HostingBean;
import net.Host.db.HostingDAO;
import net.Option.db.HostingOptionBean;
import net.Option.db.HostingOptionDAO;
import net.booking.action.Mydatecarculator;
import net.booking.action.checkinout;
import net.booking.db.BookingDAO;
import net.comment.db.CommentBean;
import net.comment.db.CommentDAO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.Host.action.*;

public class HostingContentAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		System.out.println("컨텐트 처리 페이지");
		request.setCharacterEncoding("UTF-8");
		reponse.setContentType("text/html; charset=UTF-8");
		PrintWriter out = reponse.getWriter();
		int num = 0; 
		int pageNum = 1;

		if(request.getParameter("num")==null){
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href = './HostingListAction.ho?pageNum=1'");
			out.println("</script>");
			out.close();
			return null;
		}
		else{
		num = Integer.parseInt(request.getParameter("num"));
		}
		if(request.getParameter("pageNum") == null){
			pageNum = 1;
		}
		else{
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		boolean sc;
		if(request.getParameter("countstop")==null){
			sc = true;
		}
		else{
			sc = false;
		}
		HostingDAO hdao = new HostingDAO();
		MemberDAO mbdao = new MemberDAO();
		HostingOptionDAO htodao = new HostingOptionDAO();
		HostingBean hb = hdao.getContent(num,sc);
		request.setAttribute("hb", hb);
		HostingOptionBean hto = new HostingOptionBean();
		hto = htodao.getRoomOptionBoard(hb.getNum());
		request.setAttribute("hto", hto);
		MemberBean mb = mbdao.getMember(hb.getId());
		request.setAttribute("mb", mb);
		
		BookingDAO bkdao = new BookingDAO();
		ArrayList<checkinout> list = new ArrayList<checkinout>();
		list = bkdao.getDatelist(mb.getId());
		//System.out.println(list.size());
		//System.out.println(bkdao.getDatelist(mb.getId()));
		Mydatecarculator cal = new Mydatecarculator();
		ArrayList<String> listtemp = new ArrayList<String>();
		ArrayList<String> listtemp1 = new ArrayList<String>();
		String dates;
		String indate;
		String outdate;
		int yy,mm,dd,y,m,d;
			
		for(int i = 0 ; i < list.size();i++){
			indate = list.get(i).getCheckin();
			outdate = list.get(i).getCheckout();
	//		System.out.println("in = " + indate);
	//		System.out.println("out = " + outdate);
			String []qe = indate.split("-");
			yy =Integer.parseInt(qe[0]);
			mm =Integer.parseInt(qe[1]);
			dd =Integer.parseInt(qe[2]);
			String []qee = outdate.split("-");
			y =Integer.parseInt(qee[0]);
			m =Integer.parseInt(qee[1]);
			d =Integer.parseInt(qee[2]);
			listtemp.add(cal.checkoutDatelist(yy, mm, dd, y, m, d).toString());
			listtemp1.add(cal.checkinDatelist(yy, mm, dd, y, m, d).toString());
			}
		//System.out.println("리스트 템프 :"+ listtemp.size());
		request.setAttribute("listtemp", listtemp);
		request.setAttribute("listtemp1", listtemp1);
		//comment
		ArrayList<CommentBean> clist = new ArrayList<CommentBean>();
		CommentDAO cdao = new CommentDAO();
		int commentcount = 0;
		commentcount = cdao.getcommentcount(num);
		request.setAttribute("ccount", commentcount);
		System.out.println("시부레"+num +""+ pageNum);
		clist = cdao.getCommentList(num, pageNum);
		request.setAttribute("clist", clist);
		System.out.println(clist.size());
		
		int commentcountorigin = 0;
		int pagesize = 0;
		commentcountorigin = cdao.getcommentcountorigin(num);
		System.out.println("commentcountorigin = "+commentcountorigin);
		request.setAttribute("cmcount", commentcountorigin);
		if(commentcountorigin==0){
			pagesize = 1;
		}
		else if(commentcountorigin==1){
			pagesize = 1;
		}
		else{
			pagesize = ((commentcountorigin-1)/6)+1;
		}
		System.out.println("pagesize = "+pagesize);
		request.setAttribute("pagesize",pagesize);
		request.setAttribute("pageNum", pageNum);
		boolean scroll;
		if(request.getParameter("scroll") == null){
			System.out.println("오예쓰으으으으으으으");
			scroll = false;
		}
		else{
			System.out.println("부와와와와와와와와왕ㅋ");
			scroll = true;
		}
		request.getSession().setAttribute("scroll", scroll);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./host/Content.jsp");
		System.out.println("HostingContentAction execute()");
		return forward;
	}
}

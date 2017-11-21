package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.comment.db.CommentBean;
import net.comment.db.CommentDAO;

public class recommentaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("대댓글 처리부분입니다.");
		String name = request.getParameter("name");
		String comment = request.getParameter("recomment");
		String target = request.getParameter("target");
		int num =Integer.parseInt(request.getParameter("num"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		CommentBean temp = new CommentBean();
		CommentDAO cdao = new CommentDAO();
		
		temp.setNum(num);
		temp.setTarget(target);
		temp.setName(name);
		temp.setContent(comment);
		temp.setRe_ref(re_ref);
		boolean scroll = cdao.insertReComment(temp);
		request.setAttribute("scroll", scroll);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./HostingContentAction.ho?num="+num);
		return forward;
		
	}
	

}

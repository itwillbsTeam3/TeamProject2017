package net.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.comment.action.ActionForward;
import net.comment.db.CommentBean;
import net.comment.db.CommentDAO;

public class insertcomment implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String target = request.getParameter("target");
		int num =Integer.parseInt(request.getParameter("num"));
		double grade = 0 ; 
		if(request.getParameter("star-input")==null){
			grade = 0;
		}
		else{
			grade = Double.parseDouble(request.getParameter("star-input"));
		}
		CommentBean temp = new CommentBean();
		CommentDAO cdao = new CommentDAO();
		temp.setGrade(grade);
		temp.setNum(num);
		temp.setTarget(target);
		temp.setName(name);
		temp.setContent(content);
		boolean scroll = cdao.insertComment(temp);
		request.setAttribute("scroll", scroll);
		request.getSession().setAttribute("scroll", scroll);
		//스크롤 처리해야함
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./HostingContentAction.ho?num="+num+"&scroll=ok");
		return forward;
	
	}

}

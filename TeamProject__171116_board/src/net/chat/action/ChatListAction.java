package net.chat.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.chat.db.ChatDAO;

public class ChatListAction implements Action{
       
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fromId = request.getParameter("fromId");
		String toId = request.getParameter("toId");
		String listType = request.getParameter("listType");
		ChatDAO cdao = new ChatDAO();
		if(fromId == null || fromId.equals("") || toId == null || toId.equals("") || listType == null || listType.equals("")) 
			response.getWriter().write("");
		else if(listType.equals("ten")) 
			response.getWriter().write(cdao.getTen(URLDecoder.decode(fromId, "utf-8"), URLDecoder.decode(toId, "utf-8")));
		else {
			try {
				response.getWriter().write(cdao.getId(URLDecoder.decode(fromId, "utf-8"), URLDecoder.decode(toId, "utf-8"), listType));
			}catch(Exception e) {
				response.getWriter().write("");
			}
		}
		return null;
	}

}

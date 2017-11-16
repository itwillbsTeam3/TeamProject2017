package net.chat.action;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.chat.db.ChatDAO;

public class ChatSubmitAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fromId = request.getParameter("fromId");
		String toId = request.getParameter("toId");
		String chatContent = request.getParameter("chatContent");
		
		if(fromId == null || fromId.equals("") || toId == null || toId.equals("") || chatContent == null || chatContent.equals("")) {
			response.getWriter().write("0");
		}else {
			fromId = URLDecoder.decode(fromId, "utf-8");
			toId = URLDecoder.decode(toId, "utf-8");
			chatContent = URLDecoder.decode(chatContent, "utf-8");
			response.getWriter().write(new ChatDAO().submit(fromId, toId, chatContent) +"");
		}
		return null;
	}

}

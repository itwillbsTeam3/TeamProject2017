package net.message.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.message.db.MessageBean;
import net.message.db.MessageDAO;

public class MessageSendingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MessageSendingAction excute()");
		request.setCharacterEncoding("UTF-8");
		
		MessageBean msb = new MessageBean();
		MessageDAO msdao = new MessageDAO();

		
		String rid = request.getParameter("rid");
		String sid = request.getParameter("sid");
		String message = request.getParameter("message");
		int flag = 0;
		
		msb.setRid(rid);
		msb.setSid(sid);
		msb.setMessage(message);
		msb.setFlag(flag);
		msb.setDate(new Timestamp(System.currentTimeMillis()));
		
		msdao.insertMessage(msb);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('메시지가 성공적으로 보내졌습니다.');");
		out.println("window.close();");
		out.println("</script>");
		out.close();
		return null;
		}
	
		

}

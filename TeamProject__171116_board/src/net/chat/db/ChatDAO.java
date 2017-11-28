package net.chat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class ChatDAO {
	
	private Connection getConnection()throws Exception{
		Connection conn = null;
		// Context 객체생성
		Context init=new InitialContext(); 
		// DataSource=디비연동 이름 불러오기
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		// con = DataSource
		conn=ds.getConnection();
		return conn;
		}	
	
	public int getChatCount(String toId){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "select count(*) from chat where toId = ? and chatRead = 0";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, toId);
		rs = pstmt.executeQuery();
		
		if(rs.next())
			count = rs.getInt("count(*)");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){try {pstmt.close();}catch(Exception pstmte){pstmte.printStackTrace();}
			if(con!=null){try {con.close();}catch(Exception cone){cone.printStackTrace();}
			if(rs!=null){try {rs.close();}catch(Exception rse){rse.printStackTrace();}
			}
			}
			}
		}
		return count;
	}
	public ArrayList<ChatBean> getChatList(String toId){
		ArrayList<ChatBean> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "select * from chat where toId = ? and chatRead = 0 group by fromId order by num desc";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, toId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatBean>();
			while(rs.next()) {
				ChatBean chat = new ChatBean();
				chat.setNum(rs.getInt("num"));
				chat.setFromId(rs.getString("fromId"));
				chat.setToId(rs.getString("toId"));
				chat.setChatContent(rs.getString("chatContent"));
				chat.setChatTime(rs.getTimestamp("chatTime"));
				chat.setChatRead(rs.getInt("chatRead"));
				chat.setImg(rs.getString("img"));
				chatList.add(chat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(pstmt!=null){try {pstmt.close();}catch(Exception pstmte){pstmte.printStackTrace();}
			if(conn!=null){try {conn.close();}catch(Exception cone){cone.printStackTrace();}
			if(rs!=null){try {rs.close();}catch(Exception rse){rse.printStackTrace();}
			}
				}
			}
		}
		 return chatList;
	}
	
	public ArrayList<ChatBean> getChatListById(String fromId, String toId, String num){
		ArrayList<ChatBean> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM CHAT WHERE ((fromId = ? AND toId =? ) OR (fromId = ? AND toId=?)) AND num > ? ORDER BY chatTime";
		
		try {
			MemberBean temp = new MemberBean();
			MemberDAO mdao = new MemberDAO();
			temp = mdao.getMember(fromId);
			
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, toId);
			pstmt.setString(4, fromId);
			pstmt.setInt(5, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatBean>();
			while(rs.next()) {
				ChatBean chat = new ChatBean();
				chat.setNum(rs.getInt("num"));
				chat.setFromId(rs.getString("fromId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToId(rs.getString("toId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatTime(rs.getTimestamp("chatTime"));
				chat.setImg(temp.getProfile());
				chatList.add(chat);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		 return chatList; //리스트 반환
	}
	
	public ArrayList<ChatBean> getChatListByRecent(String fromId, String toId, int number){
		ArrayList<ChatBean> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM CHAT WHERE ((fromId = ? AND toId =? ) OR (fromId = ? AND toId=?)) AND num > (SELECT MAX(num) - ? FROM CHAT WHERE (fromId = ? AND toId =?) OR (fromId = ? AND toId =?)) ORDER BY chatTime";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, toId);
			pstmt.setString(4, fromId);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromId);
			pstmt.setString(7, toId);
			pstmt.setString(8, toId);
			pstmt.setString(9, fromId);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatBean>();
			while(rs.next()) {
				ChatBean chat = new ChatBean();
				chat.setNum(rs.getInt("num"));
				chat.setFromId(rs.getString("fromId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToId(rs.getString("toId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatTime(rs.getTimestamp("chatTime"));
				chat.setImg(rs.getString("img"));
				chatList.add(chat);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return chatList; //리스트 반환
	}
	
	public int submit(String fromId, String toId, String chatContent){
		ArrayList<ChatBean> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberBean temp = new MemberBean();
		MemberDAO mdao = new MemberDAO();
		temp = mdao.getMember(fromId);
		
		String SQL = "insert into chat values (null, ?, ?, ?, now(), 0, ?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, chatContent);
			pstmt.setString(4,temp.getProfile());
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; //데이터베이스 오류
	}
	
	public int readChat(String fromId, String toId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "UPDATE CHAT SET chatRead = 1 WHERE (fromId = ? AND toId = ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, toId);
			pstmt.setString(2, fromId);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	public int getAllUnreadChat(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT COUNT(num) FROM CHAT WHERE toId =? AND chatRead = 0";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(num)");
			}
				return 0; //받은 메시지가 없을 때
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}
	
	public String getTen(String fromId, String toId) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatBean> chatList = chatDAO.getChatListByRecent(fromId, toId, 2147483647);
		if(chatList.size() == 0 ) return "";
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getImg()+ "\"}]");
			
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getNum() + "\"}");
		chatDAO.readChat(fromId, toId);
		return result.toString();
	}

	public String getId(String fromId, String toId, String num) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatBean> chatList = chatDAO.getChatListById(fromId, toId, num);
		if(chatList.size() == 0 ) return "";
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getImg()+ "\"}]");
			
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getNum() + "\"}");
		chatDAO.readChat(fromId, toId);
		return result.toString();
	}
	
}

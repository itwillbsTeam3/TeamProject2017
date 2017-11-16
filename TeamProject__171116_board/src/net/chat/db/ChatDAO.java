package net.chat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	public ArrayList<ChatBean> getChatListById(String fromId, String toId, String num){
		ArrayList<ChatBean> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM CHAT WHERE ((fromId = ? AND toId =? ) OR (fromId = ? AND toId=?)) AND num > ? ORDER BY chatTime";
		
		try {
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
		
		String SQL = "insert into chat values (null, ?, ?, ?, now(), 0)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			pstmt.setString(3, chatContent);
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
		ArrayList<ChatBean> chatList = chatDAO.getChatListByRecent(fromId, toId, 100);
		if(chatList.size() == 0 ) return "";
		for(int i=0; i<chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToId()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent()+ "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+ "\"}]");
			
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
			result.append("{\"value\": \"" + chatList.get(i).getChatTime()+ "\"}]");
			
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() -1).getNum() + "\"}");
		chatDAO.readChat(fromId, toId);
		return result.toString();
	}
	
}

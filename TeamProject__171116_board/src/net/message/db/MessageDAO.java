package net.message.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MessageDAO {
	private Connection getConnection()throws Exception{
		Connection con = null;
		Context init=new InitialContext(); 
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		con=ds.getConnection();
		return con;
	}
	
	//send
	public void insertMessage(MessageBean msb){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int alram = 0;
		ResultSet rs = null;
		try{
		con=getConnection();
		System.out.println("sql 시작");
		sql = "insert into message(num, rid, sid, message, flag, date) values(NULL, ?, ?, ?, ?, ?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, msb.getRid()); 
		pstmt.setString(2, msb.getSid()); 
		pstmt.setString(3, msb.getMessage());     
		pstmt.setInt(4, msb.getFlag());
		pstmt.setTimestamp(5, msb.getDate());
		pstmt.executeUpdate();
		
		//---------------------------------------------------------
		sql = "select max(num) from alram";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()){
			alram = rs.getInt("max(num)")+1;
		}
		else{
			alram = 1;
		}
		//num, name, type, target, url, date, flag, number, content		
		sql = "insert into alram values(?,?,?,?,?,now(),0,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, alram);
		pstmt.setString(2, msb.getRid());//target
		pstmt.setInt(3, 2);
		pstmt.setString(4, msb.getSid());//name
		pstmt.setString(5, "message");
		pstmt.setInt(6, 0);
		pstmt.setString(7, msb.getMessage());
		pstmt.executeUpdate();
		System.out.println("sql 끝");
		//---------------------------------------------------------
		}catch(Exception e){
			e.printStackTrace();
			}finally{
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
		}
}

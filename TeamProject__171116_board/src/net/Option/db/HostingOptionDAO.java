package net.Option.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.Host.db.HostingBean;

import java.sql.PreparedStatement;


public class HostingOptionDAO {
	Connection con=null;
	PreparedStatement pstmt =null;
	ResultSet rs=null;
	String sql="";

	private Connection getConnection() throws Exception{
		
		Connection con = null;
		Context init =new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		con =  ds.getConnection();	
		return con;		
	}
	public HostingOptionBean getRoomOptionBoard(int num){
		HostingOptionBean temp = new HostingOptionBean();
		try {
			con = getConnection();
			sql="select * from room where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp.setNumberOfBed(rs.getString("numberOfBed"));
				temp.setNumberOfGuest(rs.getString("numberOfGuest"));
				temp.setNumberOfRoom(rs.getString("numberOfRoom"));
				temp.setNumberOfToilet(rs.getString("numberOfToilet"));
				temp.setOption1(rs.getString("option1"));
				temp.setOption2(rs.getString("option2"));
				temp.setOption3(rs.getString("option3"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return temp;
	}
	public void insertRoomOptionBoard(HostingOptionBean hb)throws Exception{	
		try{
			con=getConnection();
		
			sql="insert into room (numberOfGuest,numberOfRoom,numberOfBed,numberOfToilet,option1,option2,option3) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, hb.getNumberOfGuest());
			pstmt.setString(2, hb.getNumberOfRoom());
			pstmt.setString(3, hb.getNumberOfBed());
			pstmt.setString(4, hb.getNumberOfToilet());
			pstmt.setString(5, hb.getOption1());
			pstmt.setString(6, hb.getOption2());	
			pstmt.setString(7, hb.getOption3());			
			pstmt.executeUpdate(); //구문 
			
		}catch(Exception e){
			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}

	}//매서드
	public void updateRoomOptionBoard(HostingOptionBean hb , int num)throws Exception{
		try{
			con = getConnection();
			sql="update room set numberOfGuest=?,numberOfRoom=?,numberOfBed=?,numberOfToilet=?,option1=?,option2=?,option3=? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hb.getNumberOfGuest());
			pstmt.setString(2, hb.getNumberOfRoom());
			pstmt.setString(3, hb.getNumberOfBed());
			pstmt.setString(4, hb.getNumberOfToilet());
			pstmt.setString(5, hb.getOption1());
			pstmt.setString(6, hb.getOption2());
			pstmt.setString(7, hb.getOption3());
			pstmt.setInt(8, num);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
	}
}

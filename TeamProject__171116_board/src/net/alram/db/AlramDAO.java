package net.alram.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AlramDAO {
	public Connection getConnection() throws Exception{
		Connection con = null;
		Context init = new InitialContext();
		DataSource ds =(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		con = ds.getConnection();
		return con;
	}
	public int getAlramCount(String name){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "select count(*) from alram where name = ? and flag = 0" ;
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
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
	public int getallAlramCount(String name){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "select count(*) from alram where name = ? and flag = 0" ;
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
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
	public ArrayList<AlramBean> getAlram(String name){
		ArrayList<AlramBean> templist = new ArrayList<AlramBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from alram where flag = 0 and name = ? and type in(1,2) order by date desc";
		try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		while(rs.next()){
			AlramBean temp = new AlramBean();
			temp.setNum(rs.getInt("num"));
			temp.setName(rs.getString("name"));
			temp.setType(rs.getInt("type"));
			temp.setDate(rs.getTimestamp("date"));
			temp.setTarget(rs.getString("target"));
			temp.setUrl(rs.getString("url"));
			temp.setFlag(rs.getInt("flag"));
			temp.setNumber(rs.getInt("number"));
			temp.setContent(rs.getString("content"));
			templist.add(temp);
			}
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
		return templist;
	}
	
	public ArrayList<AlramBean> getsysAlram(String name){
		ArrayList<AlramBean> templist = new ArrayList<AlramBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from alram where flag = 0 and name = ? and type in(3) order by date desc";
		try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		while(rs.next()){
			AlramBean temp = new AlramBean();
			temp.setNum(rs.getInt("num"));
			temp.setName(rs.getString("name"));
			
//			============추가===============
			temp.setType(rs.getInt("type"));
			temp.setTarget(rs.getString("target"));
			temp.setUrl(rs.getString("url"));
//			===============================
			
//			============원본===============
			temp.setDate(rs.getTimestamp("date"));
//			===============================
			
			
//			============추가===============
			temp.setFlag(rs.getInt("flag"));
			temp.setNumber(rs.getInt("number"));
			temp.setContent(rs.getString("content"));
//			===============================
			
//			============원본===============
			templist.add(temp);
//			===============================
			}
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
		return templist;
	}
	
	public void setsysflag(int num){
		String sql =null;
		Connection con=null;
		PreparedStatement pstmt=null ;

		try{
		con = getConnection();
		sql = "update alram set flag = 1 where num = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,num);
		pstmt.executeUpdate();
		}
	
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(pstmt!=null){try {pstmt.close();}catch(Exception pstmte){pstmte.printStackTrace();}
			if(con!=null){try {con.close();}catch(Exception cone){cone.printStackTrace();}
			}
			}
		}
	}
}

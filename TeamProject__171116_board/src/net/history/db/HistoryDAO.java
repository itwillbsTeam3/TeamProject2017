package net.history.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import net.booking.db.BookingBean;

public class HistoryDAO {
	public Connection getConnection() throws Exception{
		Connection con = null;
		Context init = new InitialContext();
		DataSource ds =(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		con = ds.getConnection();
		return con;
	}
	public void insertHistory(HistoryBean temp){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "insert into history values(null,?,?,?,now())" ;
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, temp.getFlag());
		pstmt.setString(2, temp.getId());
		pstmt.setInt(3,temp.getMileage());
		pstmt.executeUpdate();
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
	}
	public int getHistoryCount(String id, int flag){ //내역 갯수>>페이징
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "select count(*) from history where id = ?" ;
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, flag);
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
	public ArrayList<HistoryBean> getHistory(String id){ // 결제내역 내용 출력
		ArrayList<HistoryBean> templist = new ArrayList<HistoryBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from history where id=? order by date desc";
		try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			HistoryBean temp = new HistoryBean();
			temp.setId(rs.getString("id"));
			temp.setFlag(rs.getInt("flag"));
			temp.setMileage(rs.getInt("mileage"));
			temp.setDate(rs.getDate("date"));
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
	public ArrayList<BookingBean> getHosthistory(String id){ // 호스트내역 내용 출력
		ArrayList<BookingBean> templist = new ArrayList<BookingBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from booking where host_id=? order by checkin";
		try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			BookingBean temp = new BookingBean();
			temp.setSubject(rs.getString("subject"));
			temp.setHost_id(rs.getString("host_id"));
			temp.setGuest_id(rs.getString("guest_id"));
			temp.setCheckin(rs.getString("checkin"));
			temp.setCheckout(rs.getString("checkout"));
			temp.setDate(rs.getDate("date"));
			temp.setPrice(rs.getInt("price"));
			temp.setEtc(rs.getString("etc"));
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
	public ArrayList<BookingBean> getBookinghistory(String id){ // 예약내역 내용 출력
		ArrayList<BookingBean> templist = new ArrayList<BookingBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from booking where guest_id=? order by checkin";
		try{
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while(rs.next()){
			BookingBean temp = new BookingBean();
			temp.setSubject(rs.getString("subject"));
			temp.setHost_id(rs.getString("host_id"));
			temp.setGuest_id(rs.getString("guest_id"));
			temp.setCheckin(rs.getString("checkin"));
			temp.setCheckout(rs.getString("checkout"));
			temp.setDate(rs.getDate("date"));
			temp.setPrice(rs.getInt("price"));
			temp.setEtc(rs.getString("etc"));
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
}

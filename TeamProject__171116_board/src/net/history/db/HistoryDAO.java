package net.history.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.Host.db.HostingBean;
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
	
	public void insertHistory(int num, String host_id, String guest_id){
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int alram = 0;
			String sql = "";
			try{
			con = getConnection();
			
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
			System.out.println(alram+"1");
			sql = "insert into alram values(?,?,?,?,?,now(),0,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, alram);
			pstmt.setString(2, host_id);//받는 사람 (주인)
			pstmt.setInt(3, 3);
			pstmt.setString(4, guest_id);//보내는 사람
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7,"\""+host_id+"님에게 "+guest_id+"님이 예약 취소를 요청하셨습니다."+"\"");
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
			sql = "insert into alram values(?,?,?,?,?,now(),0,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, alram);
			pstmt.setString(2, guest_id);//받는 사람 (손님)
			pstmt.setInt(3, 3);
			pstmt.setString(4, host_id);//보내는 사람
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7,"\""+guest_id+"님에게 "+host_id+"님에게 예약 취소요청 되셨습니다."+"\"");
			pstmt.executeUpdate();
			
			sql = "update booking set flag=? where num=?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,3);
			pstmt.setInt(2,num);
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
//	public int getHistoryCount(String id, int flag){ //내역 갯수
//		int count = 0;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try{
//		con = getConnection();
//		String sql = "select count(*) from history where id = ?" ;
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, id);
//		pstmt.setInt(2, flag);
//		rs = pstmt.executeQuery();
//		
//		if(rs.next())
//			count = rs.getInt("count(*)");
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			if(pstmt!=null){try {pstmt.close();}catch(Exception pstmte){pstmte.printStackTrace();}
//			if(con!=null){try {con.close();}catch(Exception cone){cone.printStackTrace();}
//			if(rs!=null){try {rs.close();}catch(Exception rse){rse.printStackTrace();}
//			}
//			}
//			}
//		}
//		return count;
//	}
	public ArrayList<HistoryBean> getHistory(String id){ // 결제내역 내용 출력
		ArrayList<HistoryBean> templist = new ArrayList<HistoryBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from history where id=? order by num desc";
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
			temp.setNum(rs.getInt("num"));
			temp.setSubject(rs.getString("subject"));
			temp.setHost_id(rs.getString("host_id"));
			temp.setGuest_id(rs.getString("guest_id"));
			temp.setCheckin(rs.getString("checkin"));
			temp.setCheckout(rs.getString("checkout"));
			temp.setDate(rs.getDate("date"));
			temp.setPrice(rs.getInt("price"));
			temp.setEtc(rs.getString("etc"));
			temp.setFlag(rs.getInt("flag"));
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
			temp.setNum(rs.getInt("num"));
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
	public int deleteHistory(int num){ // 매개변수선언
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int check=-1;
		try{
			//예외가 발생할 것 같은 명령문
			//1단계 드라이버로더			//2단계 디비연결
			con=getConnection();
			//3단계 sql id 조건에 해당하는  pass가져오기
			sql="select * from booking where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4단계 rs=실행 
			rs=pstmt.executeQuery();
			//5 rs 첫행 데이터 있으면 비교 맞으면 check=1
			if(rs.next()){
					sql="delete from booking where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					//4단계 실행
					pstmt.executeUpdate();
					check=1;
			}else{
				check=-1;//내역없음
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			//예외 상관없이 마무리 작업
			//객체 생성 닫기
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return check;
	}
	public BookingBean getHistory(int num){ // 예약내역 내용 출력
		BookingBean hb = new BookingBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		con = getConnection();
		String sql = "select * from booking where num=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		rs.next();
			hb.setNum(rs.getInt("num"));
			hb.setHost_id(rs.getString("host_id"));
			hb.setGuest_id(rs.getString("guest_id"));
			hb.setPrice(rs.getInt("price"));
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
		return hb;
	}
}

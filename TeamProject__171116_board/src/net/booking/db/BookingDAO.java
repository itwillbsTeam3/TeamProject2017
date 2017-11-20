package net.booking.db;

import java.nio.channels.NetworkChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.booking.action.Mydatecarculator;
import net.booking.action.checkinout;
import net.booking.db.BookingBean;

public class BookingDAO {
		Connection con = null;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "";
		
		private Connection getConnection() throws Exception{
			Connection con=null;

			Context init=new InitialContext();
			
			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
			con=ds.getConnection();
			return con;
		}
	public void insertBooking(BookingBean hb){

		try {
		         con = getConnection();
		         sql = "insert into booking(subject,host_id,guest_id,checkin,checkout,date,price,etc)"
		               + "values(?,?,?,?,?,now(),?,?)";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, hb.getSubject());
		         pstmt.setString(2, hb.getHost_id());
		         pstmt.setString(3, hb.getGuest_id());
		         pstmt.setString(4, hb.getCheckin());
		         pstmt.setString(5, hb.getCheckout());
		         pstmt.setInt(6, hb.getPrice());
		         pstmt.setString(7, hb.getEtc());
		         pstmt.executeUpdate();

	      } catch (Exception e) {
		         // 예외처리
		         e.printStackTrace();
		  } finally {
		         if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		         if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
	      }
	}
	public int checkbooking(String host_id,String checkin,String checkout){
		  int check = 0;
		  Mydatecarculator car = new Mydatecarculator();
			int y,m,d,yy,mm,dd;
			String []qe = checkin.split("-");
			yy =Integer.parseInt(qe[0]);
			mm =Integer.parseInt(qe[1]);
			dd =Integer.parseInt(qe[2]);
			String []qee = checkout.split("-");
			y =Integer.parseInt(qee[0]);
			m =Integer.parseInt(qee[1]);
			d =Integer.parseInt(qee[2]);
			int interval = car.GetDifferenceOfDate(y, m, d, yy, mm, dd);
			System.out.println(interval);
			if(interval<=0){
				return 0;
			}
		  System.out.println("ㅅㅅㅅㅅㅅㅅ");
		      try {
		         con = getConnection();
		         sql = "select distinct(host_id) from booking where host_id = ? and checkin > ? and checkout < ?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, host_id);
		         pstmt.setString(2, checkin);
		         pstmt.setString(3, checkout);
		         rs = pstmt.executeQuery();

		         if(rs.next()){
		        	 check = 0;
		         }
		         else{
		        	 check = 1;
		         }
		      } catch (Exception e) {
		         // 예외처리
		         e.printStackTrace();
		      } finally {
		         if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		         if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		      }
		      return check;
    }

	public ArrayList<checkinout> getDatelist(String id){
		ArrayList<checkinout> list = new ArrayList<checkinout>();
		checkinout temp;
	      try {
		         con = getConnection();
		         sql = "select checkin,checkout from booking where host_id =?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, id);
		         rs = pstmt.executeQuery(); 
		         while(rs.next()){
		        	 temp = new checkinout(rs.getString("checkin"),rs.getString("checkout"));
		        	 list.add(temp);
		         }
		      } catch (Exception e) {
		         // 예외처리
		         e.printStackTrace();
		      } finally {
		    	 if(rs!=null) try{rs.close();}catch(SQLException se){}
		         if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		         if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
			
		return list;
	}
}


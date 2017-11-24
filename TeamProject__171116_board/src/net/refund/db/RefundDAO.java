package net.refund.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.booking.db.BookingBean;
import net.history.db.HistoryBean;

public class RefundDAO {
	String dbUrl = "jdbc:mysql://localhost:3306/bnb";
	String dbUser = "bnbid";
	String dbpass = "bnbpass";
	String sql = null;
	Connection con = null;
	PreparedStatement pstmt=null;		
	ResultSet rs = null;
	private Connection getConnection()throws Exception{
		Connection con = null;
		// Context 객체생성
		Context init=new InitialContext(); 
		// DataSource=디비연동 이름 불러오기
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		// con = DataSource
		con=ds.getConnection();
		return con;
		}
	
	//마일리지 환불
	public int refundMileage_guest(String guest_id,int price){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int MyMoney = 0;
		int check = 0; //
		int alram = 0;
		System.out.println("게스트:" + guest_id);
		
		try{
			con = getConnection();
			System.out.println("useMileage sql 시작");
			sql = "select * from mileage where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, guest_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				MyMoney = rs.getInt("mileage");
			}
			if(MyMoney - price < 0){
				check = -1; //Acition 에서 check = -1 (금액 부족)
				return check;
			}
			
			sql = "update mileage set mileage = mileage + ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, guest_id);
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
			pstmt.setString(2, guest_id);
			pstmt.setInt(3, 3);
			pstmt.setString(4, "sys");
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "\""+price+"마일리지를 환불받으셨습니다."+"\"");
			pstmt.executeUpdate();
			
			check=1;//정상결제
			//---------------------------------------------------------
			System.out.println("useMileage sql 끝");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return check;
	}
	
	public int refundMileage_host(String host_id,int price){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int MyMoney = 0;
		int check = 0; //
		int alram = 0;
		System.out.println("게스트:" + host_id);
		
		try{
			con = getConnection();
			System.out.println("useMileage sql 시작");
			sql = "select * from mileage where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, host_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				MyMoney = rs.getInt("mileage");
			}
			if(MyMoney - price < 0){
				check = -1; //Acition 에서 check = -1 (금액 부족)
				return check;
			}
			
			sql = "update mileage set mileage = mileage + ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, price);
			pstmt.setString(2, host_id);
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
			pstmt.setString(2, host_id);
			pstmt.setInt(3, 3);
			pstmt.setString(4, "sys");
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "\""+price+"마일리지를 환불하였습니다."+"\"");
			pstmt.executeUpdate();
			
			check=1;//정상결제
			//---------------------------------------------------------
			System.out.println("useMileage sql 끝");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return check;
	}
}

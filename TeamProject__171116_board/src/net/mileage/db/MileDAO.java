package net.mileage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MileDAO {
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
	
	
	//마일리지 정보
	public MileBean getMileage(String id){ 
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		MileBean mb=null; 
		try{
			con=getConnection();
			System.out.println("getMileage sql 시작");
			sql="select * from mileage where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mb=new MileBean();
				mb.setId(rs.getString("id"));
				mb.setNum(rs.getInt("num"));
				mb.setMileage(rs.getInt("mileage"));
				System.out.println("getMileage sql 끝");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return mb;
	}
	
	//마일리지 충전
	public int chargeMileage(MileBean mb){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int check = 0;
		int alram = 0;
		System.out.println(mb.getMileage());
		System.out.println(mb.getId());
		try{
			con = getConnection();
			System.out.println("updateMileage sql 시작");
			sql = "update mileage set mileage = mileage + ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, mb.getMileage());
			pstmt.setString(2, mb.getId());
			pstmt.executeUpdate();
			check = 1;
			
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
			pstmt.setString(2, mb.getId());
			pstmt.setInt(3, 3);
			pstmt.setString(4, "sys");
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7,"\""+mb.getMileage()+"마일리지를 충전"+"\"");
			pstmt.executeUpdate();
			//---------------------------------------------------------
			System.out.println("updateMileage sql 끝");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return check;
	}
	//마일리지사용
	public int useMileage(String id,int money){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int MyMoney = 0;
		int check = 0; //
		int alram = 0;
		System.out.println("마일리지 대상" + id);
		System.out.println(money);
		try{
			con = getConnection();
			System.out.println("useMileage sql 시작");
			sql = "select mileage from mileage where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				MyMoney = rs.getInt("mileage");
			}
			if(MyMoney - money < 0){
				check = -1; //Acition 에서 check = -1 (금액 부족)
				return check;
			}
			
			sql = "update mileage set mileage = mileage - ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, id);
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
			pstmt.setString(2, id);
			pstmt.setInt(3, 3);
			pstmt.setString(4, "sys");
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "\""+money+"마일리지를 사용"+"\"");
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
	public int updateMileage(String host_id,String guest_id,int money){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int check = 0;
		int alram = 0;
		System.out.println(money);
		System.out.println(host_id);
		try{
			con = getConnection();
			System.out.println("updateMileage sql 시작");
			sql = "update mileage set mileage = mileage + ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, host_id);
			pstmt.executeUpdate();
			check = 1;
			
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
			pstmt.setString(4, guest_id);
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7,"\""+guest_id+"님이 숙소를 예약"+"\"");
			pstmt.executeUpdate();
			//---------------------------------------------------------
			System.out.println("updateMileage sql 끝");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return check;
	}
	//마일리지 환불
	public int refundMileage(String id,int money){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int MyMoney = 0;
		int check = 0; //
		int alram = 0;
		System.out.println("마일리지 조작대상" + id);
		
		try{
			con = getConnection();
			System.out.println("useMileage sql 시작");
			sql = "select mileage from mileage where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				MyMoney = rs.getInt("mileage");
			}
			if(MyMoney - money < 0){
				check = -1; //Acition 에서 check = -1 (금액 부족)
				return check;
			}
			
			sql = "update mileage set mileage = mileage + ? where id = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, id);
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
			pstmt.setString(2, id);
			pstmt.setInt(3, 3);
			pstmt.setString(4, "sys");
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "\""+money+"마일리지를 환불받으셨습니다."+"\"");
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

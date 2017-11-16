package net.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {
	Connection con=null;
	PreparedStatement pstmt =null;
	ResultSet rs=null;
	String sql="";
	int size = 6;
	
	private Connection getConnection() throws Exception{
		
		Connection con = null;
		Context init =new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		con =  ds.getConnection();	
		return con;		
	}
	public int getcommentcount(int num){
		int count = 0;
		
			try {		
			con = getConnection();
			sql="select count(*) from comment where num = ? and re_lev = 0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count(*)");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return count;
	}
	public int getcommentcountorigin(int num){
		int count = 0;
			try {		
			con = getConnection();
			sql="select count(*) from comment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count(*)");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return count;
	}
	
	public ArrayList<CommentBean> getCommentList(int num,int pageNum){
		ArrayList<CommentBean> list = new ArrayList<CommentBean>();
		int start = (pageNum-1)*size;
		if(pageNum==0){
			start = 0;
		}
		//System.out.println("start = "+start);
		try {
			
			con = getConnection();
			sql="select * from comment where num = ? order by re_ref desc,re_lev asc,date desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CommentBean temp = new CommentBean();
				temp.setName(rs.getString("name"));
				temp.setContent(rs.getString("content"));
				temp.setDate(rs.getTimestamp("date"));
				temp.setTarget(rs.getString("target"));
				temp.setProfile(rs.getString("profile"));
				temp.setNum(rs.getInt("num"));
				temp.setRe_ref(rs.getInt("re_ref"));
				temp.setRe_lev(rs.getInt("re_lev"));
				list.add(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return list;
	}
	public boolean insertReComment(CommentBean temp){
		double grade = 0;
		int alram = 0;
		String profile = "";
		int re_lev = 0;
		try {
			
			con = getConnection();
			
			sql="select profile from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, temp.getTarget());
			rs = pstmt.executeQuery();
			if(rs.next()){
				profile = rs.getString("profile");
			}
			
			sql="select max(re_lev) from comment where re_ref = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, temp.getRe_ref());
			rs = pstmt.executeQuery();
			if(rs.next()){
				re_lev = rs.getInt("max(re_lev)");
			}

			sql="insert into comment values(?,?,?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, temp.getNum());
			pstmt.setString(2,temp.getName());
			pstmt.setString(3,temp.getContent());
			pstmt.setInt(4, temp.getRe_ref());
			pstmt.setInt(5, re_lev+1);
			pstmt.setInt(6, 0);
			pstmt.setString(7, temp.getTarget());
			pstmt.setDouble(8, 10);
			pstmt.setString(9, profile);
			pstmt.executeUpdate();
			
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
			pstmt.setString(2, temp.getTarget());
			pstmt.setInt(3, 1);
			pstmt.setString(4, temp.getName());
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "님이 답글을 남기셨습니다.");
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return true;
	}
	public boolean insertComment(CommentBean temp){
		double grade = 0;
		int alram = 0;
		String profile = "";
		try {
			int re_ref = 0;
			con = getConnection();
			
			sql="select profile from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, temp.getTarget());
			rs = pstmt.executeQuery();
			if(rs.next()){
				profile = rs.getString("profile");
			}
			
			sql="select max(re_ref) from comment where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, temp.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				re_ref = rs.getInt("max(re_ref)");
			}
			else{
				re_ref = 1;
			}
			
			sql="insert into comment values(?,?,?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, temp.getNum());
			pstmt.setString(2,temp.getName());
			pstmt.setString(3,temp.getContent());
			pstmt.setInt(4, re_ref + 1);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setString(7, temp.getTarget());
			pstmt.setDouble(8, temp.getGrade());
			pstmt.setString(9, profile);
			pstmt.executeUpdate();
			
			
			sql="select avg(grade) from comment where num = ? and grade <= 5.0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, temp.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				grade = rs.getDouble("avg(grade)");
			}
			
			int tt;
			tt = (int) (grade * 100);
			grade = tt;
			grade = grade /100;
			System.out.println((grade*100)%10);
			if((int)((grade * 100) % 10)>= 5){
				grade = grade + 0.1;
				tt = (int) (grade * 10);
				grade = tt;
				grade = grade /10;
			}
			else{
				tt = (int)(grade * 10);
				grade = tt;
				grade = grade / 10;
			}
			sql="update hosting set grade = ? where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, grade);
			pstmt.setInt(2, temp.getNum());
			pstmt.executeUpdate();
			
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
			pstmt.setString(2, temp.getTarget());
			pstmt.setInt(3, 1);
			pstmt.setString(4, temp.getName());
			pstmt.setString(5, "sys");
			pstmt.setInt(6, 0);
			pstmt.setString(7, "님이 후기를 남기셨습니다.");
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
		return true;
	}
}

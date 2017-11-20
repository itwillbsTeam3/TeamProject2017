package net.Host.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.Host.db.*;
import net.member.db.MemberBean;

public class HostingDAO {
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
	
	public void insertContent(HostingBean temp){
		try {
			con = getConnection();
			sql="insert into hosting(id,subject,content,price,address,date,etc,file1,file2,file3,file4,file5,readcount) values(?,?,?,?,?,now(),?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,temp.getId());
			pstmt.setString(2,temp.getSubject());
			pstmt.setString(3,temp.getContent());
			pstmt.setInt(4,temp.getPrice());
			pstmt.setString(5,temp.getAddress());
			pstmt.setString(6,temp.getEtc());
			pstmt.setString(7,temp.getFile1());
			pstmt.setString(8,temp.getFile2());
			pstmt.setString(9,temp.getFile3());
			pstmt.setString(10,temp.getFile4());
			pstmt.setString(11,temp.getFile5());
			pstmt.setInt(12, 0);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();} catch (SQLException se) {
				} 
		}	
	}	
	
	public void updatereadcount(int num){
	try {
		con = getConnection();
		sql = "update hosting set readcount = readcount+1 where num =?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num); 
		pstmt.executeUpdate();
	} catch (Exception e) {
		// 예외처리
		e.printStackTrace();
	} finally {
		if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		if(con!=null) try{con.close();} catch (SQLException se) {
			} // 마무리 객체닫기
	}
	}
	
	public HostingBean getContent(int num ,boolean check) {
		HostingBean content = new HostingBean();
		try {
			con = getConnection();
			sql = "select * from hosting where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); 
			rs = pstmt.executeQuery();
			if (rs.next()) {
			content.setNum(rs.getInt("num"));
			content.setId(rs.getString("id"));
			content.setSubject(rs.getString("subject"));
			content.setContent(rs.getString("content"));
			content.setPrice(rs.getInt("price"));
			content.setAddress(rs.getString("address"));
			content.setDate(rs.getDate("date"));
			content.setEtc(rs.getString("etc"));
			content.setFile1(rs.getString("file1"));
			content.setFile2(rs.getString("file2"));
			content.setFile3(rs.getString("file3"));
			content.setFile4(rs.getString("file4"));
			content.setFile5(rs.getString("file5"));
			content.setReadcount(rs.getInt("readcount"));
			content.setGrade(rs.getDouble("grade"));
			
			}
		} catch (Exception e) {
			// 예외처리
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();} catch (SQLException se) {
				} // 마무리 객체닫기
		}
		if(check){
		updatereadcount(num);
		}
		return content;
	}
	
	public HostingBean getContent(String id) {
		HostingBean temp = new HostingBean();
		
		try {
			con = getConnection();
			sql="select * from hosting where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				temp.setNum(rs.getInt("num"));
				temp.setAddress(rs.getString("address"));
				temp.setDate(rs.getDate("date"));
				temp.setEtc(rs.getString("etc"));
				temp.setPrice(rs.getInt("price"));
				temp.setSubject(rs.getString("subject"));
				temp.setContent(rs.getString("content"));
				temp.setFile1(rs.getString("file1"));
				temp.setFile2(rs.getString("file2"));
				temp.setFile3(rs.getString("file3"));
				temp.setFile4(rs.getString("file4"));
				temp.setFile5(rs.getString("file5"));
				temp.setReadcount(rs.getInt("readcount"));
				temp.setGrade(rs.getDouble("grade"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
	
		return temp;
	}
	public int updatecontent(HostingBean temp){
		try {
			con = getConnection();
			sql="update hosting set subject=?,content=?, price=?, address=?, date = now(), etc=?";
			if(!(temp.getFile1()==null)){
				sql += ", file1 = ?";
			}
			if(!(temp.getFile2()==null)){
				sql += ", file2 = ?";
			}
			if(!(temp.getFile3()==null)){
				sql += ", file3 = ?";
			}
			if(!(temp.getFile4()==null)){
				sql += ", file4 = ?";
			}
			if(!(temp.getFile5()==null)){
				sql += ", file5 = ?";
			}
			int index = 1;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(index,temp.getSubject());index++;
			pstmt.setString(index,temp.getContent());index++;
			pstmt.setInt(index, temp.getPrice());index++;
			pstmt.setString(index, temp.getAddress());index++;
			pstmt.setString(index, temp.getEtc());index++;
			if(!(temp.getFile1()==null)){
			//	System.out.println("file1 값들어옴");
				pstmt.setString(index,temp.getFile1());index++;
			}
			if(!(temp.getFile2()==null)){
			//	System.out.println("file2 값들어옴");
				pstmt.setString(index,temp.getFile2());index++;
			}
			if(!(temp.getFile3()==null)){
			//	System.out.println("file3 값들어옴");
				pstmt.setString(index,temp.getFile3());index++;
			}
			if(!(temp.getFile4()==null)){
			//	System.out.println("file4 값들어옴");
				pstmt.setString(index,temp.getFile4());index++;
			}
			if(!(temp.getFile5()==null)){
			//	System.out.println("file5 값들어옴");
				pstmt.setString(index,temp.getFile5());index++;
			}
			pstmt.executeUpdate();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return 1;
	}
	public ArrayList<HostingBean> getcontentList(int start,int size,String address){
		
		
		address = "%"+address+"%";
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	sql = "select * from hosting where address like ? limit ?,?";
        	pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setInt(2, start);
			pstmt.setInt(3, size);
        	rs = pstmt.executeQuery(); 
            while(rs.next()) 
            {
                HostingBean temp = new HostingBean();
                temp.setNum(rs.getInt("num"));
                temp.setId(rs.getString("id"));
                temp.setSubject(rs.getString("subject"));
                temp.setContent(rs.getString("content"));
                temp.setPrice(rs.getInt("price"));
                temp.setAddress(rs.getString("address"));
                temp.setDate(rs.getDate("date"));
                temp.setEtc(rs.getString("etc"));
                temp.setFile1(rs.getString("file1"));
                temp.setFile2(rs.getString("file2"));
                temp.setFile3(rs.getString("file3"));
                temp.setFile4(rs.getString("file4"));
                temp.setFile5(rs.getString("file5"));
    			temp.setReadcount(rs.getInt("readcount"));
    			temp.setGrade(rs.getDouble("grade"));
				hostList.add(temp);
            }
             
       } catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}finally {
		//마무리 객체닫기
		if(rs!=null) try{rs.close();}catch(SQLException se){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		if(con!=null) try{con.close();}catch(SQLException se){}
	}
		
		return hostList;
	}
	public ArrayList<HostingBean> getcontentList(int start,int size,String address,String checkin,String checkout){
		address = "%"+address+"%";
		System.out.println("주소 : "+address);
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	sql = "select * from hosting where address like ? and not id = any(select host_id from booking where (checkin <= ? and checkout > ?) or (checkin < ? and checkout >= ?) or(checkin>=? and checkout<=?)) limit ?,?;";
        	pstmt = con.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, checkin);
			pstmt.setString(3, checkin);
			pstmt.setString(4, checkout);
			pstmt.setString(5, checkout);
			pstmt.setString(6, checkin);
			pstmt.setString(7, checkout);
			pstmt.setInt(8, start);
			pstmt.setInt(9, size);
        	rs = pstmt.executeQuery(); 
            while(rs.next()) 
            {
                HostingBean temp = new HostingBean();
                temp.setNum(rs.getInt("num"));
                temp.setId(rs.getString("id"));
                temp.setSubject(rs.getString("subject"));
                temp.setContent(rs.getString("content"));
                temp.setPrice(rs.getInt("price"));
                temp.setAddress(rs.getString("address"));
                temp.setDate(rs.getDate("date"));
                temp.setEtc(rs.getString("etc"));
                temp.setFile1(rs.getString("file1"));
                temp.setFile2(rs.getString("file2"));
                temp.setFile3(rs.getString("file3"));
                temp.setFile4(rs.getString("file4"));
                temp.setFile5(rs.getString("file5"));
    			temp.setReadcount(rs.getInt("readcount"));
    			temp.setGrade(rs.getDouble("grade"));
				hostList.add(temp);
            }
             
       } catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}finally {
		//마무리 객체닫기
		if(rs!=null) try{rs.close();}catch(SQLException se){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		if(con!=null) try{con.close();}catch(SQLException se){}
	}
		
		return hostList;
	}

	public ArrayList<HostingBean> getcontentList(String type){
		
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	if(!type.equals("price")){
        		sql = "select * from hosting order by "+type+" desc limit 0,7";
        	}
        	else{
        		sql = "select * from hosting order by "+type+" asc limit 0,7";
        	}
        	pstmt = con.prepareStatement(sql);
 
        	rs = pstmt.executeQuery();
            while(rs.next()) 
            {
                HostingBean temp = new HostingBean();
                temp.setNum(rs.getInt("num"));
                temp.setId(rs.getString("id"));
                temp.setSubject(rs.getString("subject"));
                temp.setContent(rs.getString("content"));
                temp.setPrice(rs.getInt("price"));
                temp.setAddress(rs.getString("address"));
                temp.setDate(rs.getDate("date"));
                temp.setEtc(rs.getString("etc"));
                temp.setFile1(rs.getString("file1"));
                temp.setFile2(rs.getString("file2"));
                temp.setFile3(rs.getString("file3"));
                temp.setFile4(rs.getString("file4"));
                temp.setFile5(rs.getString("file5"));
    			temp.setReadcount(rs.getInt("readcount"));
    			temp.setGrade(rs.getDouble("grade"));
				hostList.add(temp);
            }
             
       } catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	}finally {
		//마무리 객체닫기
		if(rs!=null) try{rs.close();}catch(SQLException se){}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
		if(con!=null) try{con.close();}catch(SQLException se){}
	}
		
		return hostList;
	}
	
	public void updatereadcount(String id){
		
	}
	
	
}


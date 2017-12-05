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
			sql="insert into hosting(id,subject,content,price,address,date,etc,file1,file2,file3,file4,file5,readcount,oc) values(?,?,?,?,?,now(),?,?,?,?,?,?,?,?)";
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
			pstmt.setInt(13, 1);
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
				temp.setOc(rs.getInt("oc"));
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
			sql += "where id = ?";
			
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
			pstmt.setString(index, temp.getId());
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
	public ArrayList<HostingBean> getcontentList(int start,int size, String address){
		
		String str []; 
		str = address.split(",");
		int index = 1;
		for(int i = 0 ; i < str.length;i++){
			str[i] = str[i].trim();
			str[i] = "%"+str[i]+"%";
			System.out.println(str[i]);
		}
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	sql = "select * from hosting where(";
        	for(int i  = 1 ; i <= str.length ; i ++){
        		 sql = sql +" address like ?";
        		 if(i != str.length){
        			 //System.out.println(i+" "+str.length);
        			 sql = sql +" or"; 
        		 }
        	}
        	sql = sql + ") and oc = 1 limit ?,?";
        	pstmt = con.prepareStatement(sql);
        	for(int i = 0 ; i < str.length ; i++){
			pstmt.setString(index, str[i]);index++;
        	}
			pstmt.setInt(index, start);index++;
			pstmt.setInt(index, size);
			System.out.println(sql);
			System.out.println("리미트 :"+ start+" "+size);
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
		String str []; 
		str = address.split(",");
		int index = 1;
		for(int i = 0 ; i < str.length;i++){
			str[i] = str[i].trim();
			str[i] = "%"+str[i]+"%";
			System.out.println(str[i]);
		}
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	sql = "select * from hosting where (";
        	for(int i = 1 ; i <= str.length ; i++){
        		 sql = sql +" address like ?";
        		 if(i != str.length){
        			 //System.out.println(i+" "+str.length);
        			 sql = sql +" or"; 
        		 }
        	}
        	sql = sql + ") and oc = 1 and not id = any(select host_id from booking where (checkin <= ? and checkout > ?) or (checkin < ? and checkout >= ?) or(checkin>=? and checkout<=?)) limit ?,?;";
        	pstmt = con.prepareStatement(sql);
        	for(int i = 0 ; i < str.length ; i++){
    			pstmt.setString(index, str[i]);index++;
            }
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkout);index++;
			pstmt.setString(index, checkout);index++;
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkout);index++;
			pstmt.setInt(index, start);index++;
			pstmt.setInt(index, size);
			System.out.println(sql);
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
        		sql = "select * from hosting where oc = 1 order by "+type+" desc limit 0,7";
        	}
        	else{
        		sql = "select * from hosting where oc = 1 order by "+type+" asc limit 0,7";
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
	
	public String OpenClose(String id){
		int flag = 0;
		String text = "";
		try {
			con = getConnection();
			sql="select oc from hosting where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = rs.getInt("oc");
			}
			sql="update hosting set oc = ? where id = ?";
			pstmt = con.prepareStatement(sql);
			if(flag == 1){
			pstmt.setInt(1, 0);
			text = "일시중지";
			}
			else{
			pstmt.setInt(1, 1);
			text = "호스팅중";
			}
			pstmt.setString(2, id);
			pstmt.executeUpdate();	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return text;
	}
	
	public int roomStatus(String id){
		int flag = 0;
		try {
			con = getConnection();
			sql="select oc from hosting where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = rs.getInt("oc");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return flag;
	}
	public int gethostingcount(){
		int size = 0;
		try {
			con = getConnection();
			sql="select count(*) from hosting where oc = 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				size = rs.getInt("count(*)");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return size;
	}
	public int gethostingcount(String address){
		String str []; 
		str = address.split(",");
		int index = 1;
		for(int i = 0 ; i < str.length;i++){
			str[i] = str[i].trim();
			str[i] = "%"+str[i]+"%";
			System.out.println(str[i]);
		}
		int size = 0;
		try {
	       	con = getConnection();
        	sql = "select count(*) from hosting where(";
        	for(int i  = 1 ; i <= str.length ; i ++){
        		 sql = sql +" address like ?";
        		 if(i != str.length){
        			 //System.out.println(i+" "+str.length);
        			 sql = sql +" or"; 
        		 }
        	}
        	sql = sql + ") and oc = 1";
        	pstmt = con.prepareStatement(sql);
        	for(int i = 0 ; i < str.length ; i++){
			pstmt.setString(index, str[i]);index++;
        	}
			System.out.println(sql);
        	rs = pstmt.executeQuery();
			if(rs.next()){
				size = rs.getInt("count(*)");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return size;
	}
	public int gethostingcount(String address,String checkin,String checkout){
		int size = 0;
		String str []; 
		str = address.split(",");
		int index = 1;
		for(int i = 0 ; i < str.length;i++){
			str[i] = str[i].trim();
			str[i] = "%"+str[i]+"%";
			System.out.println(str[i]);
		}
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
		try {
        	con = getConnection();
        	sql = "select count(*) from hosting where (";
        	for(int i = 1 ; i <= str.length ; i++){
        		 sql = sql +" address like ?";
        		 if(i != str.length){
        			 //System.out.println(i+" "+str.length);
        			 sql = sql +" or"; 
        		 }
        	}
        	sql = sql + ") and oc = 1 and not id = any(select host_id from booking where (checkin <= ? and checkout > ?) or (checkin < ? and checkout >= ?) or(checkin>=? and checkout<=?));";
        	pstmt = con.prepareStatement(sql);
        	for(int i = 0 ; i < str.length ; i++){
    			pstmt.setString(index, str[i]);index++;
            }
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkout);index++;
			pstmt.setString(index, checkout);index++;
			pstmt.setString(index, checkin);index++;
			pstmt.setString(index, checkout);
			System.out.println(sql);
        	rs = pstmt.executeQuery(); 
			if(rs.next()){
				size = rs.getInt("count(*)");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return size;
	}
	public int gethostingcount(String checkin,String checkout){
		System.out.println("날짜만 으로 count");
		ArrayList<HostingBean> hostList = new ArrayList<HostingBean>();
    	int size = 0;
    	try {
    		con = getConnection();
    		sql="select count(*) from hosting where oc = 1 and (checkin <= ? and checkout > ?) or (checkin < ? and checkout >= ?) or(checkin>=? and checkout<=?));";
    		pstmt = con.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		if(rs.next()){
    			size = rs.getInt("count(*)");
    		}
        	pstmt = con.prepareStatement(sql);
        	pstmt.setString(1, checkin);
			pstmt.setString(2, checkin);
			pstmt.setString(3, checkout);
			pstmt.setString(4, checkout);
			pstmt.setString(5, checkin);
			pstmt.setString(6, checkout);
			System.out.println(sql);
        	rs = pstmt.executeQuery(); 
			if(rs.next()){
				size = rs.getInt("count(*)");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}// 마무리 객체닫기
		}
		return size;
	}
	
}


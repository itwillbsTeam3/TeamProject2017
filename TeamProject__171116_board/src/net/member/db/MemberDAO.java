package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
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
	
	//회원가입할 기능(insert)메서드----------------------------------------------------------------
	public int insertMember(MemberBean mb){// 매개변수선언
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		int result = 0;
		
		ResultSet rs = null;
		int num=0;
		
		try{
			//예외가 발생할 것 같은 명령문
			//1단계 드라이버로더			//2단계 디비연결
			con=getConnection();
			//3단계 sql 객체 생성
			sql="insert into member(id, pass, name, reg_date, age, gender, email, address, address2, zip_code, phone, mobile, selfinfo, profile) values(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mb.getId()); 
			pstmt.setString(2, mb.getPass()); 
			pstmt.setString(3, mb.getName()); 
			pstmt.setTimestamp(4, mb.getReg_date()); 
			pstmt.setInt(5, mb.getAge()); 
			pstmt.setString(6, mb.getGender()); 
			pstmt.setString(7, mb.getEmail());
			pstmt.setString(8, mb.getAddress()); 
			pstmt.setString(9, mb.getAddress2());
			pstmt.setString(10, mb.getZip_code()); 
			pstmt.setString(11, mb.getPhone()); 
			pstmt.setString(12, mb.getMobile()); 
			pstmt.setString(13, mb.getSelfinfo()); 
			pstmt.setString(14, mb.getProfile());
			
			//4단계 실행
			result = pstmt.executeUpdate(); // insert, update, delete
			
			System.out.println("insertMileage sql 시작");
			sql="select max(num) from mileage";
			pstmt=con.prepareStatement(sql);
	        rs= pstmt.executeQuery();
	        if(rs.next()){
	        	num=rs.getInt("max(num)")+1;
	        }else{
	        	num = 1;
	        }
			sql = "insert into mileage(num, id, mileage) values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, mb.getId());
			pstmt.setInt(3, 10000000);
			result = pstmt.executeUpdate();
		    System.out.println("insertMileage sql 끝");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//예외 상관없이 마무리 작업
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return result;
	}//insertMember메서드
	
	//아이디 비밀번호체크 메서드-------------------------------------------------------------------------
	public int idCheck(String id, String pass){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		//실행할 내용
		ResultSet rs=null;
		int check=-1;
		try{
			//1단계			//2단계
			con=getConnection();
			//3단계 sql id에 해당하는 pass 가져오기
			sql="select pass from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4단계 rs=실행 결과 저장
			rs=pstmt.executeQuery();
			//5단계 rs첫행이동 데이터 있으면 "아이디 있음"
			//		비밀번호비교 맞으면 check=1 틀리면 check=0
			//				      없으면 "아이디 없음" check=-1;
			if(rs.next()){
				//아이디 있음
				if(pass.equals(rs.getString("pass"))){
					check=1;//비밀번호 맞음
				}else{
					check=0;//비밀번호 틀림
				}
			}else{
				check=-1;//아이디 없음
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//객체닫기
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}

		}
		return check;
	}//idCheck메서드

	//중복 아이디 체크 메서드-------------------------------------------------------------------------
	public int joinIdCheck(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		//실행할 내용
		ResultSet rs=null;
		int check=0;
		
		try{
			//1단계			//2단계
			con=getConnection();
			//3단계 sql id에 해당하는 pass 가져오기
			sql="select id from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4단계 rs=실행 결과 저장
			rs=pstmt.executeQuery();
			//5단계 rs첫행이동 데이터 있으면 "아이디 있음"
			//		id 비교, 중복이면 check=1 아니면 check=0

			if(rs.next()){
				check=1;//중복 id 
				}else{
				check=0;//중복 id 없음
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//객체닫기
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}

		}
		return check;

	}// joinIdCheck class
	
	//회원정보 가져오기----------------------------------------------------------------------------
	public MemberBean getMember(String id){ // 매개변수선언
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		MemberBean mb=null;  //선언
		try{
			//1단계 드라이버로더			//2단계 디비연결
			con=getConnection();
			//3단계 sql id에 해당하는 member 모든정보 가져오기
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4단계  rs 실행결과 저장
			rs = pstmt.executeQuery(); //select			
			//5단계 rs 첫번째 행 이동했을때 데이터 있으면
			//		mb 객체생성
			//		mb 안에 있는 id변수에 rs에 들어있는 "id"열을 저장
			if(rs.next()){
				mb=new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setAge(rs.getInt("age"));
				mb.setGender(rs.getString("gender"));
				mb.setEmail(rs.getString("email"));
				
				mb.setAddress(rs.getString("address"));
				mb.setAddress2(rs.getString("address2"));
				mb.setZip_code(rs.getString("Zip_code"));
				mb.setPhone(rs.getString("phone"));
				mb.setMobile(rs.getString("mobile"));
				mb.setSelfinfo(rs.getString("selfinfo"));
				mb.setProfile(rs.getString("profile"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//객체닫기
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return mb;
	}	//getMember() 메서드
	
	//회원정보 업데이트-------------------------------------------------------------------------------
	public int updateMember(MemberBean mb){ // 매개변수선언
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			int check=-1;
			int alram = 0;
			try{
				//예외가 발생할 것 같은 명령문
				//1단계 드라이버로더			//2단계 디비연결
				con=getConnection();
				//3단계 sql 객체 생성 id에 해당하는  pass가져오기
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				//4단계 실행
				rs=pstmt.executeQuery();
				//5단계 실행 첫행으로 이동 데이터 있으면 "아이디 있음"
				if(rs.next()){
					//"아이디있음"
/*//					if(mb.getPass().equals(rs.getString("pass"))){
*/				//		비밀번호 비교 맞으면 check=1
						 check = 1;
						  if(check == 1){
				//				//3. sql 생성 id해당하는 name, age, gender, email수정
						sql="update member set pass=?,name=?,age=?,gender=?,email=?, phone=?, mobile=?, zip_code=?, address=?, address2=?, selfinfo=?, profile=? where id=?";
						pstmt=con.prepareStatement(sql);
						
						pstmt.setString(1, mb.getPass());
						pstmt.setString(2, mb.getName());
						pstmt.setInt(3, mb.getAge());
						pstmt.setString(4, mb.getGender());
						pstmt.setString(5, mb.getEmail());
						pstmt.setString(6, mb.getPhone());
						pstmt.setString(7, mb.getMobile());
						pstmt.setString(8, mb.getZip_code());
						pstmt.setString(9, mb.getAddress());
						pstmt.setString(10, mb.getAddress2());
						pstmt.setString(11, mb.getSelfinfo());
						pstmt.setString(12, mb.getProfile());
						pstmt.setString(13, mb.getId());
				//4단계 실행
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
						//num, name, type, target, url, date, flag, number, content
						
						//type = 3 => 회원정보 수정(시스템)
						//기존 type = 2 => 댓글
						//기존 type = 1 => 대댓글
						
						sql = "insert into alram values(?,?,?,?,?,now(),0,?,?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, alram);
						pstmt.setString(2, mb.getId());
						pstmt.setInt(3, 3);
						pstmt.setString(4, "sys");
						pstmt.setString(5, "sys");
						pstmt.setInt(6, 0);
						pstmt.setString(7, "회원정보 수정하셨습니다.");
						pstmt.executeUpdate();
						//---------------------------------------------------------
						
						  }
					/*}*/else{
				//				틀리면 check=0
						check=0;
					}
				}
				else{
					check=-1;
				}

				//				없으면 "아이디 없음" check=-1
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				//예외 상관없이 마무리 작업
				//객체 생성 닫기
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return check;
		}//updateMember()메서드

	// 회원정보 삭제------------------------------------------------------------------------------
	public int deleteMember(String id, String pass){ // 매개변수선언
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
			sql="select pass from member where id=?";
			pstmt=con.prepareStatement(sql);

			pstmt.setString(1, id);
			//4단계 rs=실행 
			rs=pstmt.executeQuery();
			//5 rs 첫행 데이터 있으면 비밀번호 비교 맞으면 check=1
			if(rs.next()){
				//아이디 있음
				if(pass.equals(rs.getString("pass"))){
					//				//3. sql 생성 id해당하는 정보삭제
					sql="delete from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);
					//4단계 실행
					pstmt.executeUpdate();

					check=1;
				}else{
					check=0;//비밀번호 틀림
				}
			}else{
				check=-1;//아이디 없음
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
		
	} //deleteMember()메서드
	
	//본인인증 체크 메서드-------------------------------------------------------------------------
	public int checkAuth(String id, String name, String email, String mobile){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		//실행할 내용
		ResultSet rs=null;
		int check=-1;
		try{
			con=getConnection();
			sql="select name, email, mobile from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if((name.equals(rs.getString("name")))
				&&(email.equals(rs.getString("email")))
				&&(mobile.equals(rs.getString("mobile")))
				){
					check=1;//맞음
				}else{
					check=0;//틀림
				}
			}else{
				check=-1;//없음
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//객체닫기
			if(rs!=null){try{rs.close();}catch(SQLException ex){}}
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}

		}
		return check;
	}
	//아이디 비밀번호체크 메서드-------------------------------------------------------------------------
		public int checkPw(String id, String pass){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			//실행할 내용
			ResultSet rs=null;
			int check=-1;
			try{
				//1단계			//2단계
				con=getConnection();
				//3단계 sql id에 해당하는 pass 가져오기
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4단계 rs=실행 결과 저장
				rs=pstmt.executeQuery();
				//5단계 rs첫행이동 데이터 있으면 "아이디 있음"
				//		비밀번호비교 맞으면 check=1 틀리면 check=0
				//				      없으면 "아이디 없음" check=-1;
				if(rs.next()){
					//아이디 있음
					if(pass.equals(rs.getString("pass"))){
						check=1;//비밀번호 맞음
					}else{
						check=0;//비밀번호 틀림
					}
				}else{
					check=-1;//아이디 없음
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				//객체닫기
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}

			}
			return check;
		}//idCheck메서드
		
	public String searchId(String name, String mobile){
	 	Connection con = null;
		String sql="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select id from member where name = ? and mobile = ?");
			pstmt.setString(1, name);
		    pstmt.setString(2, mobile);
			rs = pstmt.executeQuery();
            if(rs.next()){
            	return(rs.getString("id"));
            }
           
          } catch (Exception e) { //에러처리
           e.printStackTrace();
          } finally {
			if(rs!=null)try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return null;
	}		
 
 public String searchPwd(String id, String mobile, String email){
	    Connection con = null;
		String sql="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select pass from member where id = ? and mobile = ? and email = ?"); 
			pstmt.setString(1, id);
		    pstmt.setString(2, mobile);
		    pstmt.setString(3, email);
			rs = pstmt.executeQuery();
            if(rs.next()){
            	return(rs.getString("pass"));
            }
            } catch (Exception e) { //에러처리
		           e.printStackTrace();
    		} finally {
			if(rs!=null)try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
		}
		return null;
	}
 /**
  * member테이블의 ID에 해당하는 레코드 삭제
  */     
	public int delMemberlist(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int result = 0;
		try {// 실행
			con=getConnection();
			sql = "delete from member where id = ?";
			// ?개수만큼 값 지정
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();  //쿼리 실행으로 삭제된 레코드 수 반환.

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//예외 상관없이 마무리 작업
			//객체 생성 닫기
			if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
			if(con!=null){try{con.close();}catch(SQLException ex){}}
			
		}
		return result;
	}// delMemberlist	
	
	 public ArrayList<MemberBean> getMemberList(int startRow, int pageSize){
		   	Connection con = null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			
	        ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
	       
	        try {
	        	con = getConnection();
	        	//예외발생 명령문
	        	//3단계 sql   select  조건 id에 해당하는 member에 있는 모든 정보 가져오기
	        	sql = "select * from member limit ?, ?";
	        	pstmt = con.prepareStatement(sql);
	        	pstmt.setInt(1, startRow-1);
	        	pstmt.setInt(2, pageSize);
				
	        	//4단계 rs = 결과 저장
	        	rs = pstmt.executeQuery();
	            
	        	//5단계 rs 첫행이동 데이터 있으면 
	            while(rs.next()) 
	            {
	                MemberBean mb = new MemberBean();
	                mb.setId(rs.getString("id"));
	                mb.setPass(rs.getString("pass"));
	                mb.setName(rs.getString("name"));
	                mb.setReg_date(rs.getTimestamp("reg_date"));
	                mb.setAge(rs.getInt("age"));
	                mb.setGender(rs.getString("gender"));
	                mb.setEmail(rs.getString("email"));
	                
	                mb.setAddress(rs.getString("address"));
					mb.setAddress2(rs.getString("address2"));
					mb.setZip_code(rs.getString("Zip_code"));
					mb.setPhone(rs.getString("phone"));
					mb.setMobile(rs.getString("mobile"));
					mb.setSelfinfo(rs.getString("selfinfo"));
					mb.setProfile(rs.getString("profile"));
	                memberList.add(mb);
	            }
	            
	            return memberList;
	            
	       } catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}finally {
			//마무리 객체닫기
			if(rs!=null) try{rs.close();}catch(SQLException se){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException se){}
			if(con!=null) try{con.close();}catch(SQLException se){}
		}
	  return memberList;
	}//getMemberList()
	 
	 public int getMemberCount(){
		 ResultSet rs = null;
		 Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
		int count = 0;
		try {
			//1,2디비연결 메서드호출
			con = getConnection();
			//num 게시판 글번호 구하기
			//sql 함수 최대값 구하기 max()
			sql = "select count(id) from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (rs != null) {try {rs.close();} catch (SQLException ex) {}	}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException ex) {}}
			if (con != null) {try {con.close();} catch (SQLException ex) {	}}
		}
		return count;
	 }
	 
}

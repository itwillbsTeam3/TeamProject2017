package net.member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	//Action 인터페이스 상속
	// execute() 메서드 오버라이딩  alt shif s   v
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("MemberJoinAction execute()");
	ServletContext context=request.getServletContext();
	String realPath =context.getRealPath("/upload");
	int maxSize = 5*1024*1024;		
	MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
	//request 한글처리
	request.setCharacterEncoding("UTF-8");
	
	String id = multi.getParameter("id");
	String pass = multi.getParameter("pass");
	String name =  multi.getParameter("name");
	int age = Integer.parseInt( multi.getParameter("age"));
	String gender =  multi.getParameter("gender");
	String email =  multi.getParameter("email");	
	String address =  multi.getParameter("address");
	String address2 =  multi.getParameter("address2");
	String zip_code =  multi.getParameter("zip_code");
	String phone =  multi.getParameter("phone");
	String mobile =  multi.getParameter("mobile");
	String selfinfo =  multi.getParameter("selfinfo");
	String profile = multi.getFilesystemName("profile");
	
	//패키지  net.member.db  파일이름 MemberBean
	//MemberBean mb 객체생성
	//mb 메서드 호출 setId(id 파라미터 정보 가져오기)
	//날짜 저장
	MemberBean mb = new MemberBean();
	mb.setId(id);
	mb.setPass(pass);
	mb.setName(name);
	mb.setReg_date(new Timestamp(System.currentTimeMillis()));
	mb.setAge(age);
	mb.setGender(gender);
	mb.setEmail(email);
	mb.setAddress(address);
	mb.setAddress2(address2);
	mb.setZip_code(zip_code);
	mb.setPhone(phone);	
	mb.setMobile(mobile);
	mb.setSelfinfo(selfinfo);
	mb.setProfile(profile);
	
	MemberDAO mdao = new MemberDAO();
	int result = mdao.insertMember(mb);
	
	//패키지  net.member.db  파일이름 MemberDAO
	//MemberDAO mdao객체생성
	//메서드호출 insertMember(mb)
	//이동정보 저장   ./MemberLogin.me 
	//      =>  ./member/loginForm.jsp
	//ActionForward forward 객체생성 메서드호출 값 저장
	if(result == 1){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'> alert('회원가입 성공!!'); location.href='./Main.me'; </script>");
		out.close();
		return null;
	}else{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'> alert('회원가입 실패'); history.back(); </script>");
		out.close();
		return null;
	}
	}
}

package net.email.db;
import org.apache.commons.mail.*;
public class EmailDAO {
	public void send(String to,String Msg) throws EmailException{
	Email email = new SimpleEmail(); 
	email.setHostName("smtp.naver.com");
	email.setSmtpPort(465);
	email.setAuthentication("helloworld0126", "qwer1234");//1. 이메일 전송시 ID,PW 기입할 것
	email.setSSLOnConnect(true);
	email.setFrom("helloworld0126@naver.com");//2. 이메일 전송시, 보내는사람의 이메일 주소 정보 기입할 것
	email.setSubject("저희 HOSTER에 오신것을 환영 합니다. 회원가입시 이메일 인증번호는 아래와 같습니다.");
	email.setMsg("인증번호  : "+ Msg);
	email.addTo(to);
	email.send();
	}
}
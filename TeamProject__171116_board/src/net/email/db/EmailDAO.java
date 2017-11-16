package net.email.db;
import org.apache.commons.mail.*;
public class EmailDAO {
	public void send(String to,String Msg) throws EmailException{
	Email email = new SimpleEmail(); 
	email.setHostName("smtp.naver.com");
	email.setSmtpPort(465);
	email.setAuthentication("", "");
	email.setSSLOnConnect(true);
	email.setFrom("");
	email.setSubject("저희 BNB HOST에 오신것을 환영 합니다. 회원가입시 이메일 인증번호는 아래와 같습니다.");
	email.setMsg("인증번호  : "+ Msg);
	email.addTo(to);
	email.send();
	}
}
package net.Host.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.Host.db.HostingBean;
import net.Host.db.HostingDAO;
import net.Option.db.HostingOptionBean;
import net.Option.db.HostingOptionDAO;

public class HostinginsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String path = request.getServletContext().getRealPath("/upload");
		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8",new DefaultFileRenamePolicy());
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int price =Integer.parseInt(multi.getParameter("price"));
		String etc = multi.getParameter("Etc");
		String address = multi.getParameter("address");//주소
		String subject = multi.getParameter("subject"); // 소재목
		String content = multi.getParameter("content"); // 글내용
		String file1 = multi.getFilesystemName("File1");
		String file2 = multi.getFilesystemName("File2");
		String file3 = multi.getFilesystemName("File3");
		String file4 = multi.getFilesystemName("File4");
		String file5 = multi.getFilesystemName("File5");
		
		HostingBean temp = new HostingBean();
		HostingDAO btdao = new HostingDAO();
		
		temp.setId(id);
		temp.setPrice(price);
		temp.setAddress(address);
		temp.setEtc(etc);
		temp.setSubject(subject);
		temp.setContent(content);
		temp.setFile1(file1);
		temp.setFile2(file2);
		temp.setFile3(file3);
		temp.setFile4(file4);
		temp.setFile5(file5);
		btdao.insertContent(temp);
				
		
		String option1[]= new String[30];  
		for(int i = 0 ; i< 30; i++){
			if(multi.getParameter("op1_"+i)==null){option1[i] = "0";}
			else{option1[i]="1";}  
		} 
		String option2[]= new String[16]; 
		for(int i = 0 ; i< 16; i++){
			if(multi.getParameter("op2_"+i)==null){option2[i] = "0";}
			else{option2[i]="1";}  
		} 

		String option3[]= new String[4];  
		for(int i = 0 ; i< 4; i++){
			if(multi.getParameter("op3_"+i)==null){option3[i] = "0";}
			else{option3[i]="1";}  		
		} 	
		String arrayToString1 = "";
		for(int i=0; i<option1.length; i++){
			arrayToString1 = arrayToString1+option1[i];
		}
		String arrayToString2 = "";
		for(int i=0; i<option2.length; i++){
			arrayToString2 = arrayToString2+option2[i];
		}
		String arrayToString3 = "";
		for(int i=0; i<option3.length; i++){
			arrayToString3 = arrayToString3+option3[i];
		}
		HostingOptionBean hto = new HostingOptionBean();
		HostingOptionDAO htodao = new HostingOptionDAO();
		
		String numberOfGuest = multi.getParameter("numberOfGuest");
		String numberOfRoom = multi.getParameter("numberOfRoom");
		String numberOfBed = multi.getParameter("numberOfBed");
		String numberOfToilet = multi.getParameter("numberOfToilet");
		
		temp = btdao.getContent(id);	
		hto.setNum(temp.getNum());
		hto.setNumberOfGuest(numberOfGuest);
		hto.setNumberOfRoom(numberOfRoom);
		hto.setNumberOfBed(numberOfBed);
		hto.setNumberOfToilet(numberOfToilet);
		hto.setOption1(arrayToString1);
		hto.setOption2(arrayToString2);
		hto.setOption3(arrayToString3);	
		
		htodao.insertRoomOptionBoard(hto);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./Main.me");
		return forward;
	}

}

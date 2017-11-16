package net.booking.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.math.*;
public class Mydatecarculator{
	private int year;
	private int month;
	private int day;
//	private int t_year;
//	private int t_month;
//	private int t_day;
//	Mydatecarculator(int year,int month,int day,int t_year,int t_month,int t_day){
//			this.year = year;
//			this.month = month;
//			this.day = day;
//			this.t_year = t_year;
//			this.t_month = t_month;
//			this.t_day = t_day;
//	}
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		public int GetDifferenceOfDate (int nYear1,int nMonth1,int nDate1,int nYear2,int nMonth2, int nDate2){ 
		Calendar cal = Calendar.getInstance ( ); 
		int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0; 
		 
		if ( nYear1 > nYear2 ) { 
			for ( int i = nYear2; i < nYear1; i++ ) { 
				cal.set ( i, 12, 0 ); nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR );
			} 
			nTotalDate1 += nDiffOfYear; 
		} else if ( nYear1 < nYear2 ) { 
			for ( int i = nYear1; i < nYear2; i++ ) { 
				cal.set ( i, 12, 0 ); 
				nDiffOfYear += cal.get ( Calendar.DAY_OF_YEAR ); 
		} 
			nTotalDate2 += nDiffOfYear; 
		} 
		cal.set ( nYear1, nMonth1-1, nDate1 ); 
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR ); 
		nTotalDate1 += nDiffOfDay; 
		cal.set ( nYear2, nMonth2-1, nDate2 ); 
		nDiffOfDay = cal.get ( Calendar.DAY_OF_YEAR ); 
		nTotalDate2 += nDiffOfDay; 
		return nTotalDate1-nTotalDate2; 
		} 
		// 2일부터 5일까지 숙박시설을 예약했으면  2일 3일 4일 은 비활성화  5일은 활성화<<그날에 바로 채크인 가능하기때문>>
					//받아서 이런식으로 쪼개야함 
		
		public ArrayList<String> checkoutDatelist(int year,int month,int day,int t_year,int t_month,int t_day){
		ArrayList<String> Dates = new ArrayList<String>();
//		System.out.print(year +" ");
//		System.out.print(month + " ");
//		System.out.println(day + " ");
//		System.out.print(t_year +" ");
//		System.out.print(t_month + " ");
//		System.out.println(t_day + " ");
		int date_to_date = GetDifferenceOfDate(t_year, t_month, t_day, year, month, day);
		//System.out.println(date_to_date);
		String temp;
					for(int i = 0 ; i  < date_to_date ; i ++){
					Date x = new Date(year-1900,month-1,day+i);
				//	System.out.println("씨발런아" + x);
					String z = "\""+x.toString()+"\"";
					temp = "";
					for(int q = 0 ; q < z.length() ; q++){
						if('-' == z.charAt(q)){
							
							if('0'==z.charAt(q+1)){
								z = z.substring(0, q+1) + z.substring(q+2,z.length());
								//System.out.println(z);
							}
						}
					}
				    Dates.add(z);
					}
					//System.out.println("사이즈 =  :" + Dates.size());
			return Dates;
	}
		public ArrayList<String> checkinDatelist(int year,int month,int day,int t_year,int t_month,int t_day){
			ArrayList<String> Dates = new ArrayList<String>();
//			System.out.print(year +" ");
//			System.out.print(month + " ");
//			System.out.println(day + " ");
//			System.out.print(t_year +" ");
//			System.out.print(t_month + " ");
//			System.out.println(t_day + " ");
			t_day++;
			day++;
			int date_to_date = GetDifferenceOfDate(t_year, t_month, t_day, year, month, day);
			//System.out.println(date_to_date);
			String temp;
						for(int i = 0 ; i  < date_to_date ; i ++){
						Date x = new Date(year-1900,month-1,day+i);
					//	System.out.println("씨발런아" + x);
						String z = "\""+x.toString()+"\"";
						temp = "";
						for(int q = 0 ; q < z.length() ; q++){
							if('-' == z.charAt(q)){
								
								if('0'==z.charAt(q+1)){
									z = z.substring(0, q+1) + z.substring(q+2,z.length());
									//System.out.println(z);
								}
							}
						}
					    Dates.add(z);
						}
					//	System.out.println("사이즈 =  :" + Dates.size());
				return Dates;
		}
}

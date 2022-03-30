package com.test.hyewontest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class HyewonTestApplicationTests {

	@Test
	void contextLoads() {
		String msg = "Y22020700001OY-sdfg";
		System.out.println(" 헤니는 " + msg);
		String m[] = msg.split("-");
		System.out.println("mm : " + m.length);
		System.out.println("mm : " + m[0]);
	}


	@Test
	void contextLoads2() {
		Date a = new Date();
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
		try {
			Date d1 = f.parse(LocalTime.now().toString());
			Date d2 = f.parse("11:00:00");
			Date d3 = f.parse("17:00:00");
			Date d4 = f.parse("23:00:00");
			System.out.println("d1 : " + d1);
			System.out.println("d2 : " + d2);
			System.out.println("d3 : " + d3);
			System.out.println("d4 : " + d4);
			long diff = d1.getTime() - d2.getTime();
			long sec = diff / 1000;
			System.out.println("diff : " + diff);
			System.out.println("sec : " + sec);
		} catch (Exception e) {
			System.out.println("e : " + e);
		}
		//현재시간
		System.out.println("LocalTime.now : " + LocalTime.now());
		System.out.println("a.getTime() : " + a.getTime());
	}

	@Test
	void timerule(){
		String today = null;


		Date date = new Date();

		System.out.println(date); //Thu May 13 13:25:57 KST 2021

// 포맷변경 ( 년월일 시분초)
		SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Java 시간 더하기

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		today = sdformat.format(cal.getTime());
		System.out.println("지금 : " + today); //05/13/2021 13:25:57

// 3분 더하기
		cal.add(Calendar.MINUTE, 3);

		today = sdformat.format(cal.getTime());
		System.out.println("3분후 : " + today); //05/13/2021 13:28:57

		cal.setTime(date);
// 1시간 전
		cal.add(Calendar.HOUR, -1);
		today = sdformat.format(cal.getTime());
		System.out.println("1시간 전 : " + today); //05/13/2021 12:25:57

		cal.add(Calendar.MINUTE, -90);
		today = sdformat.format(cal.getTime());
		System.out.println("1시간 반전 : " + today); //05/13/2021 12:25:57

		cal.setTime(date);
// 하루 전
		cal.add(Calendar.DATE, -1);

		today = sdformat.format(cal.getTime());
		System.out.println("1일 전 : " + today); //05/12/2021 13:25:57
	}

	@Test
	void timeget(){
		String today = null;
		String before = null;
		Date date = new Date();
		System.out.println(date); //Thu May 13 13:25:57 KST 2021
		// 포맷변경 ( 년월일 시분초)
		SimpleDateFormat sdformat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		// Java 시간 더하기
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		today = sdformat.format(cal.getTime());
		System.out.println("지금 : " + today);
		cal.add(Calendar.MINUTE, -90);
		before = sdformat.format(cal.getTime());
		System.out.println("90분전 : " + before); //05/13/2021 13:28:57

		try {
			// 포맷터
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			// 문자열 -> Date
			String am1 = formatter.format(date).concat(" 11:00:00");
			String pm1 = formatter.format(date).concat(" 17:00:00");
			String pm2 = formatter.format(date).concat(" 23:00:00");
			System.out.println("am : "+ am1); // Sat Jun 19 21:05:07 KST 2021
			System.out.println("pm1 : "+ pm1); // Sat Jun 19 21:05:07 KST 2021
			System.out.println("pm2 : "+ pm2); // Sat Jun 19 21:05:07 KST 2021

		} catch (Exception e){
			System.out.println("e : " + e);
		}


//		Date pm1 = new Date("17:00:00");
//		System.out.println("pm1 : " + pm1);
	}
}

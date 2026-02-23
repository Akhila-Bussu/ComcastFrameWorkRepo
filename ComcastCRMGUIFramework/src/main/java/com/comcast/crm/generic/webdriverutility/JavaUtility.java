package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random=new Random();
		int randomInt = random.nextInt(9000);
		
		return randomInt;
	}
	
	public String getSystemData() {
	
		Date date=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String data = sdf.format(date);
		
		return data;
	}
	
	public String getRequriedDate(int days) {
		Date date=new Date();
		SimpleDateFormat sif=new SimpleDateFormat("yyyy-MM-dd");
		sif.format(date);
		Calendar cal=sif.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sif.format(cal.getTime());
		
		return reqDate;
		
	}
	
	}

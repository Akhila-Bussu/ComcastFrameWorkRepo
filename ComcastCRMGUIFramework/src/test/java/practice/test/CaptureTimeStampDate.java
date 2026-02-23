package practice.test;

import java.util.Date;

public class CaptureTimeStampDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String time= new Date().toString();
//		System.out.println(time);
		

		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		System.out.println(time);
		
	}

}

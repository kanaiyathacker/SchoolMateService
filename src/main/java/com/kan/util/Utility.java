package com.kan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

	public static String getCurrentDateAsString() {
		Date  ss1=new Date();
		SimpleDateFormat formatter5=new SimpleDateFormat("dd-MM-yyyy");
		String formats1 = formatter5.format(ss1);
		System.out.println(formats1);
		return formats1;
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDateAsString());
	}
}

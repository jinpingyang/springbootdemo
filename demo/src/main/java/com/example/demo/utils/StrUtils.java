package com.example.demo.utils;



public class StrUtils {
	
	 public static String replace(String strSource, String strFrom, String strTo) {
	        String strDest = "";
	        int intFromLen = strFrom.length();
	        int intPos;
	        if (strSource == null) {
	            strDest = "";
	            strSource = "";
	        } else {
	            while ((intPos = strSource.indexOf(strFrom)) != -1) {
	                strDest = strDest + strSource.substring(0, intPos);
	                strDest = strDest + strTo;
	                strSource = strSource.substring(intPos + intFromLen);
	            }
	        }
	        strDest = strDest + strSource;
	        return strDest;
	    }
	
	
}
package com.vaka.util;

import java.util.UUID;

public class Utils {
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	public String randomKey()
	{
		return Long.toString(Math.abs( UUID.randomUUID().getLeastSignificantBits())).substring(10);		
	}
}

package com.fixit.util;

import java.util.Collection;

public class YaUtil {

	public static boolean isEmpty(Collection c){
		if (c != null && c.size() > 0){
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isNotEmpty(Collection c){
		return !isEmpty(c);
	}
	
}

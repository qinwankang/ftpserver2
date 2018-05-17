package com.tf.ftpserver.util;

public class StringUtil {
	
	/**
	 * str：判断的字符串为空
	 * 为空则返回true
	 * 不为空返回false
	 * @param str
	 * @return
	 */
	public static boolean isEmpty (String str) {
		
		boolean status = false;
		
		if (str == null || "".equals(str) || str.length() == 0 ) {
			
			status = true;
			
		}
		
		return status;
	}
	
	/**
	 * str：判断的字符串不为空
	 * 为空则返回false
	 * 不为空返回true
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty (String str) {
		
		boolean status = false;
		
		if (!isEmpty(str)) {
			
			status = true;
			
		}
		
		return status;
	}

}

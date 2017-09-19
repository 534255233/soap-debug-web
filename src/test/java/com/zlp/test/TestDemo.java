package com.zlp.test;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TestDemo {
	
	@Test
	public void test1() {
		
		String s = "/1BCDWB/WSS0170316013950306025";
		System.out.println(s);
		
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("InDate", "2017-03-16");
		hashMap.put("InMatnr", "");
		hashMap.put("OutMatnr", "");
		System.out.println(hashMap);
		
		
		Map<String, String> treeMap = new TreeMap<>();
		treeMap.put("InDate", "2017-03-16");
		treeMap.put("InMatnr", "");
		treeMap.put("OutMatnr", "");
		System.out.println(treeMap);
		
		Map<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("InDate", "2017-03-16");
		linkedHashMap.put("InMatnr", "");
		linkedHashMap.put("OutMatnr", "");
		System.out.println(linkedHashMap);
		
		String encoding = "Basic WkdFX1JGQzphZG1pbjY2ODE=";
		String userPasswd = encoding.replace("Basic ", "");
		System.out.println(userPasswd);
		byte[] bytes = Base64.getDecoder().decode(userPasswd.trim().getBytes());
		String pp = new String(bytes);
		String[] p2 = pp.split(":");
		System.out.println(p2[0]);
		System.out.println(p2[1]);
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date date = c.getTime();
		System.out.println(date);
		
		
	}

}

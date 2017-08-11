package com.lazycoder.app.test.utility;

import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

public class TestUtility {
	
	private static final String DEFAULT_LOCATION = "/src/test/resources/";

	@SuppressWarnings("restriction")
	public static String loadFile(String fileName){
		String fileContent = null;
		try{
			String packagePath = getCallerPackageName().replace(".", "/");
			//String packagePath = sun.reflect.Reflection.getCallerClass(3).getPackage().getName().replace(".", "/");
			System.out.println(packagePath);
			String filePath = new StringBuffer().append(DEFAULT_LOCATION).append(packagePath).append("/").append(fileName).toString();
			System.out.println(filePath);
			URL url = Resources.getResource(filePath);
			fileContent = Resources.toString(url, Charsets.UTF_8);
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileContent;
	}
	
	public static String getCallerPackageName() { 
	    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
	    String callerPackageName = null;
	    for (int i=1; i<stElements.length; i++) {
	        StackTraceElement ste = stElements[i];
	        if (!ste.getClassName().equals(TestUtility.class.getName()) && ste.getClass().getPackage().getName().indexOf("java.lang.Thread")!=0) {
	            if (callerPackageName==null) {
	            	callerPackageName = sun.reflect.Reflection.getCallerClass(i).getPackage().getName();
	                //callerPackageName = ste.getClass().getPackage().getName();
	            }
	        }
	    }
	    return callerPackageName;
	 }
	
	public static <T> T createObjectFromJsonFile(String fileName, Class<T> responseClass){
		return new Gson().fromJson(loadFile(fileName), responseClass);
	}

	public static <T> T createObjectFromJsonString(String jsonString, Class<T> responseClass){
		return new Gson().fromJson(jsonString, responseClass);
	}
	
}

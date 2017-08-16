package com.lazycoder.app.test.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class TestUtility {
	
	private static final String TEST_RESOURCE_PATH = System.getProperty("user.dir")+".src.test.resources.";
	private static final String SEPERATOR = System.getProperty("file.separator");
	
	public static String loadFile(String fileName){
		String fileContent = null;
		try{
			fileContent = IOUtils.toString(new FileInputStream(new File(getFilePath(fileName))));
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileContent;
	}
	
	private static String getFilePath(String fileName) {
		String folderPath = TEST_RESOURCE_PATH+getCallerClassPackageName()+".";
		return folderPath.replace(".", SEPERATOR)+fileName;
	}

	private static String getCallerClassPackageName() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		for(StackTraceElement stackTraceElement: stackTraceElements){
			String className = stackTraceElement.getClassName();
			if(!className.equals(TestUtility.class.getName()) && className.indexOf("java.lang") == -1){
				return className.substring(0, className.lastIndexOf("."));
			}
			
		}
		return null;
	}

	public static <T> T createObjectFromJsonFile(String fileName, Class<T> responseClass){
		return new Gson().fromJson(loadFile(fileName), responseClass);
	}

	public static <T> T createObjectFromJsonString(String jsonString, Class<T> responseClass){
		return new Gson().fromJson(jsonString, responseClass);
	}
	
}

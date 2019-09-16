package com.zip.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataFromPropertiesFile {
	
	//we use the static modifier so we can use these values along the way in our different classes
	//that extend DataFromPropertiesFile such as FilesFromFolder or AllowedFormats or FolderTemplateName
	
	public static String pathIn = "";
	public static String pathOut = "";
	public static String allowedFormats = "";
	public static String folderTemplateName = "";

	public DataFromPropertiesFile(){
		
		String file = "properties/parser.properties";

		try {

		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		    String line;
		    
		    
	    	int lineCount = 0;

		    while ((line = br.readLine()) != null) {
		    	
		    	lineCount++;
		    	
		    	int beginning = line.indexOf("=") + 1;	//we know the exact configuration of our properties file
		    	
		    	if(lineCount == 1) {
		    		pathIn = line.substring(beginning);
		    	}
		    	if(lineCount == 2) {
		    		pathOut = line.substring(beginning);
		    	}
		    	if(lineCount == 3) {
		    		allowedFormats = line.substring(beginning);
		    	}
		    	if(lineCount == 4) {
		    		folderTemplateName = line.substring(beginning);
		    	}

		    }
		    
		    br.close();

		} catch (IOException e) {
		    System.out.println("ERROR: unable to read file " + file);
		    e.printStackTrace();   
		}
		
		//System.out.println(pathIn + " " + pathOut + " " + allowedFormats + " " + folderTemplateName);
	}
}



package com.zip.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DataFromPropertiesFile {
	
	private static String parserSourcePath = "parser.source.path";
	private static String parserDestinationPath = "parser.destination.path";
	private static String parserAllowedFormat = "parser.allowed.format";
	private static String parserZipFolderTemplateName = "parser.zip.folder.template.name";
	
	private static String parserSourcePathValue = "";
	private static String parserDestinationPathValue = "";
	private static String parserAllowedFormatValue = "";
	private static String parserZipFolderTemplateNameValue = "";
	
	private static Properties parserProperties = new Properties();
	
	static {
		
		try {
			InputStream fileParserProperties = new FileInputStream("properties/parser.properties");
			parserProperties.load(fileParserProperties);
		} catch (IOException e) {
			
        	System.out.println("WARNING: IOException");
			e.printStackTrace();
		}
		
		
		parserSourcePathValue = parserProperties.getProperty(parserSourcePath);
        parserDestinationPathValue = parserProperties.getProperty(parserDestinationPath);
        parserAllowedFormatValue = parserProperties.getProperty(parserAllowedFormat);
        parserZipFolderTemplateNameValue = parserProperties.getProperty(parserZipFolderTemplateName);
	}
	
	
	public static String getParserSourcePathValue() {
		
		return parserSourcePathValue;
		
	}
	
	public static String getParserDestinationPathValue() {
			
			return parserDestinationPathValue;
			
	}
	
	public static String getParserAllowedFormatValue() {
		
		return parserAllowedFormatValue;
		
	}
	
	public static String getParserZipFolderTemplateNameValue() {
		
		return parserZipFolderTemplateNameValue;
		
	}
	
	public static List<String> getParserAllowedFormatValueAsList(){
		
		List<String> allowedFormatList = Arrays.asList(getParserAllowedFormatValue().split(","));
		
		return allowedFormatList;
	}
	
	public static void setParserSourcePathValue(String newParserSourcePathValue) {
		parserSourcePathValue = newParserSourcePathValue;
		parserProperties.replace(parserSourcePath, parserSourcePathValue);
	}
	
	public static void setParserDestinationPathValue(String newParserDestinationPathValue) {
		parserDestinationPathValue = newParserDestinationPathValue;
		parserProperties.replace(parserDestinationPath, parserDestinationPathValue);
	}
	
	public static void setParserAllowedFormatValue(String newParserAllowedFormatValue) {
		parserAllowedFormatValue = newParserAllowedFormatValue;
		parserProperties.replace(parserAllowedFormat, parserAllowedFormatValue);
	}
	
	public static void setParserZipFolderTemplateNameValue(String newParserZipFolderTemplateNameValue) {
		parserZipFolderTemplateNameValue = newParserZipFolderTemplateNameValue;
		parserProperties.replace(parserZipFolderTemplateName, parserZipFolderTemplateNameValue);
		
	}
	
	public static void updatePropertiesFile() throws IOException {
		RandomAccessFile fileProperties = new RandomAccessFile("properties/parser.properties", "rwd");
		fileProperties.setLength(0);
		
		updateInformation(fileProperties);
		
		fileProperties.close();
	}
	
	public static void updateInformation(RandomAccessFile fileProperties) throws IOException {
		
		fileProperties.writeBytes(parserSourcePath + " = " + getParserSourcePathValue() + '\n');
		fileProperties.writeBytes(parserDestinationPath + " = " + getParserDestinationPathValue() + '\n');
		fileProperties.writeBytes(parserAllowedFormat + " = " + getParserAllowedFormatValue() + '\n');
		fileProperties.writeBytes(parserZipFolderTemplateName + " = " + getParserZipFolderTemplateNameValue() + '\n');
		
	}
}
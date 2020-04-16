package com.zip.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ParseFiles implements Variables, Runnable {	
	
	private ArrayList<String> filesToBeParsedFromSourceFolder;

	private static String createID() {
	    return String.valueOf(UUID.randomUUID());
	}
	
	static String getNameOfZip() {
		
		String nameOfZip = DataFromPropertiesFile.getParserZipFolderTemplateNameValue();
		
		if(nameOfZip.contains(date)) {
			nameOfZip = nameOfZip.replace(date, dateFormat);
		}
		
		if(nameOfZip.contains(time)) {
			nameOfZip = nameOfZip.replace(time, timeFormat);
		}
		
		if(nameOfZip.contains(uid)) {
			nameOfZip = nameOfZip.replace(uid, createID());
		}
		
		return  nameOfZip + ".zip";
	}
	
	public ParseFiles(ArrayList<String> filesToBeParsedFromSourceFolder){
	        this.filesToBeParsedFromSourceFolder = filesToBeParsedFromSourceFolder;
	}

	@Override
	public void run() {
		try {
			
	        List<String> srcFiles = new ArrayList<>();
		
			for(String path : filesToBeParsedFromSourceFolder) {
				srcFiles.add(path);
			}
			
	        FileOutputStream fos = new FileOutputStream(DataFromPropertiesFile.getParserDestinationPathValue() + getNameOfZip());
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        for (String srcFile : srcFiles) {
	        	
	            File fileToZip = new File(srcFile);
	            FileInputStream fis = new FileInputStream(fileToZip);
	            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
	            zipOut.putNextEntry(zipEntry);
	 
	            byte[] bytes = new byte[1024];
	            int length;
	            
	            while((length = fis.read(bytes)) >= 0) {
	                zipOut.write(bytes, 0, length);
	            }
	            
	            fis.close();
	        }
	        
	        filesToBeParsedFromSourceFolder.clear();
	        
	        for(String srcFile : srcFiles) {
	        	if(Paths.get(srcFile).toFile().delete() != true) {
	        		System.out.println("WARNING: Error");
	        	}
	        }
	        
	        zipOut.close();
	        fos.close();
	        
        } catch(Exception e) {
        	//We issue a warning regarding the exception, if present, and proceed to print what it actually means
			
			System.out.println("WARNING: Exception");
			e.printStackTrace();
        }
		
	}

}

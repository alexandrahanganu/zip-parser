package com.zip.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ManualZIP implements Variables, Runnable {	
	
	private ArrayList<String> filesToBeParsed;
	
	public ManualZIP(ArrayList<String> filesToBeParsed){
	        this.filesToBeParsed = filesToBeParsed;
	}

	@Override
	public void run() {
		try {
			
	        List<String> srcFiles = new ArrayList<>();
		
			for(String path : filesToBeParsed) {
				srcFiles.add(path);
			}
			
	        FileOutputStream fos = new FileOutputStream(DataFromPropertiesFile.getParserDestinationPathValue() + ParseFiles.getNameOfZip());
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
	        
	        filesToBeParsed.clear();
	        
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

package com.zip.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CheckForFilesLoop{
	
	private static ArrayList<String> filesParsingSource = new ArrayList<String>();

	public CheckForFilesLoop() throws InterruptedException {
		//TimeUnit.SECONDS.sleep(10);
		
		checkForFiles();
	}
	
	private boolean isOfAllowedFormat(Path pathOfFileToBeChecked) {
		
		String nameOfFile = pathOfFileToBeChecked.toString();
		String formatOfFile = nameOfFile.substring(nameOfFile.indexOf('.')+1).trim();
		
		if(DataFromPropertiesFile.getParserAllowedFormatValue().contains(formatOfFile)) {
			return true;
		}
		
		return false;
	}
	
	public static ScheduledExecutorService checkFilesFromFolder = Executors.newScheduledThreadPool(100);
	
	private final void checkForFiles() {

		checkFilesFromFolder.scheduleAtFixedRate(new Runnable() {

		@Override
		  public void run() {
					
			readFiles();
					
			if(!filesParsingSource.isEmpty()) {
				Thread th = new Thread(new ParseFiles(filesParsingSource));
				th.start();
			}
			
		  }
 
		}, 0, 5, TimeUnit.SECONDS);
	}
	
	private void readFiles() {
		try {
			
			Stream<Path> sourceFilePathStream = Files.walk(Paths.get(DataFromPropertiesFile.getParserSourcePathValue()));
			
			sourceFilePathStream.forEach(sourceFilePath -> {
		        if (Files.isRegularFile(sourceFilePath)) {
		        	
		        	System.out.println(sourceFilePath);
		        	
		        	
		        		if(isOfAllowedFormat(sourceFilePath)) {
			        		filesParsingSource.add(sourceFilePath.toString());
			        	}
		        		

		        }
			});
			
			//??????//ZipParserMenuController.newFilesFound(filesParsingSource.size());
			
			sourceFilePathStream.close();   

		} catch (IOException e) {
			//We issue a warning regarding the exception, if present, and proceed to print what it actually means
			System.out.println("WARNING: IOException");
			e.printStackTrace();
		}
	}

}

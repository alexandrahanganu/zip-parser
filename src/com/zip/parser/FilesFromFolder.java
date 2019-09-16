package com.zip.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesFromFolder extends DataFromPropertiesFile {
	
	public FilesFromFolder(String pathIn) {
			try (Stream<Path> filePathStream = Files.walk(Paths.get(pathIn))) {
			    filePathStream.forEach(filePath -> {
			        if (Files.isRegularFile(filePath)) {
			            System.out.println(filePath);
			        }
		    });
			} catch (IOException e) {
				
				System.out.println("WARNING: EMPTY FOLDER IN GIVEN FOLDER OR NOT ACCESSIBLE");
			}
	}
	
	public static void main(String[] args) {
		
		new FilesFromFolder(pathIn);
		
	}

}

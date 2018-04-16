package com.example.demo.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.exceptions.FileException;

@Service
public class FileUploadService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void uploadFile(MultipartFile file, String directoryPath, String fileName) {
    	try {
	    	byte[] bytes = file.getBytes();
	    	isValidDirectory(directoryPath);
	        Path path = Paths.get(directoryPath+fileName);
	        Files.write(path, bytes);
    	} catch (IOException ex) {
    		logger.error("Error uploading file", ex);
    		throw new FileException("Unable to upload file");
        }
    }
	
	public void isValidDirectory(String directoryPath) {
		File file = new File(directoryPath);
		if(!file.isDirectory()) {
			throw new FileException("Invalid Directory Path or Directory doesn't exists");
		}
	}
}

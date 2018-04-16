package com.example.demo.fileupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Value("${upload.folder.path}")
	private String directoryPath;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping(path="/files")
    public ResponseEntity fileupload(@RequestPart("file") MultipartFile file, @RequestParam(required=false) String fileName) {
		String fName = fileName != null ? fileName : file.getOriginalFilename();
		HttpStatus returnStatus = HttpStatus.CREATED;
		if(file != null && !file.isEmpty()) {
			fileUploadService.uploadFile(file, directoryPath, fName);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
		}
		return new ResponseEntity<>("Successfully uploaded: "+ fName, returnStatus);
    }
		
}

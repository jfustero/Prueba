package com.example.drago.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.example.drago.entity.Users;
import com.example.drago.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ControllerDemo {

	private final static String MESSAGE_FILE_UPLOAD = "Fichero subido correctamente";
	private final static String TXT_CVS = "text/csv";
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/users")
	public ResponseEntity<?> getUsers() throws Exception {
		
		try {
			File file = usersService.getUsers();
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        headers.add("Pragma", "no-cache");
	        headers.add("Expires", "0");
	
		    return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(file.length())
		            .contentType(MediaType.valueOf(TXT_CVS))
		            .body(resource);
		} catch (Exception e) {
		    return ResponseEntity
		    		.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    		.body(e.getMessage());
		}
	}
	
	@PostMapping("/copy")
	public ResponseEntity<?> copyFile(@RequestParam("file")MultipartFile cvsFile) throws Exception {
		try {
			List<Users> listUsers = usersService.copyFile(cvsFile.getInputStream());
			usersService.aSyncWriteFile(listUsers);
			return ResponseEntity.ok().body(MESSAGE_FILE_UPLOAD);
		} catch (Exception e) {
		    return ResponseEntity
		    		.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    		.body(e.getMessage());
		}
	}
}
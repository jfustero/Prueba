package com.example.drago.services.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.drago.entity.Users;
import com.example.drago.repositories.UsersRepository;
import com.example.drago.services.UsersService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

	private final static String TEMP_DIR = ".\\temp";
	private final static String CSV_FILENAME = "\\listUsers.csv";
	private final static String CSV_FILENAME_COPY = "\\copylistUsers.csv";
	private final static String SUCCESS = "SUCCESS";
	private final String S_USER = "User";
	private final String S_FULLNAME = "Full name";
	private final String S_ADRESS = "Adress";
	private final String S_PHONE = "Phone";
	private static final String CSV_SEPARATOR = ",";
	
	
	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public File getUsers() throws Exception {
		
		logger.info("Ini - getUsers");
	
		return writeFile(usersRepository.findAll(), CSV_FILENAME);
	}

	@Override
	public List<Users> copyFile(InputStream inputStream) throws Exception {
		logger.info("Ini - copyFile");
	    
		List<Users> listUsers = readFile(inputStream);
		
		return listUsers;
	}

	@Override
	public CompletableFuture<String> aSyncWriteFile(List<Users> listUsers) throws IOException{
		
		logger.info("Ini - aSyncWriteFile");
		try {
			writeFile(listUsers,  CSV_FILENAME_COPY);
		} catch (IOException e) {
			throw e;
		}
		
		return CompletableFuture.completedFuture(SUCCESS);
	}
	
	private List<Users> readFile(InputStream inputStream) throws Exception  {
		
		logger.info("Ini - readFile");
		
		List<Users> listUsers = new ArrayList<Users>();
		
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	
			bufferedReader.lines()
				.skip(1)
				.map(Users::parse)
				.forEach(u -> listUsers.add(u));
		}catch(Exception e) {
			throw new Exception(e);
		}
	    return listUsers;
	}
  
    private File writeFile(List<Users> listUsers, String filename) throws UnsupportedEncodingException, FileNotFoundException, IOException{
    	
        File tempdir = new File(TEMP_DIR).getAbsoluteFile();
        tempdir.mkdirs();
        
    	File csvOutputFile = new File(TEMP_DIR + File.separator + filename);
    	
    	try{

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvOutputFile), "UTF-8"));
            StringBuffer header = new StringBuffer();

            header.append(S_USER);
            header.append(CSV_SEPARATOR);
            header.append(S_FULLNAME);
            header.append(CSV_SEPARATOR);
            header.append(S_PHONE);
            header.append(CSV_SEPARATOR);
            header.append(S_ADRESS);
			bw.write(header.toString());
			bw.newLine();
			
            listUsers.forEach(u -> {
            	StringBuffer line = new StringBuffer();
                line.append(u.getUsername());
                line.append(CSV_SEPARATOR);
                line.append(u.getFullname());
                line.append(CSV_SEPARATOR);
                line.append(u.getPhone());
                line.append(CSV_SEPARATOR);
                line.append(u.getAddress());
               
                try {
					bw.write(line.toString());
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
    			
            }

            );			
            
            bw.flush();
            bw.close();

        }
        catch (UnsupportedEncodingException unsupportedEncodingException){
        	throw unsupportedEncodingException;
        }catch (FileNotFoundException fileNotFoundException){
        	throw fileNotFoundException;
        }catch (IOException iOException){
        	throw iOException;
        }
    	
		return csvOutputFile;
    }
	
}
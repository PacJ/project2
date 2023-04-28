package com.example.common.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static UUID saveCopyFile(MultipartFile file, String filePath) {
		String fileName = file.getOriginalFilename();
		
		UUID random = UUID.randomUUID(); //중복파일명을 처리하기 위한 난수 발생 : UUID는 16진수의 난수값을 발생한다.
		
		//난수값을 발생하여 "난수값_파일명"을 urlPath첨부파일이 저장될 위치에 저장한다.
		//File ff = new File(urlPath(request), random + "_" + fileName);
		File ff = new File(filePath, random + "_" + fileName);
		
		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return random; //DB에 저장하기 위해서 첨부파일 정보를 리턴한다.
	}//end saveCopyFile()
	
	//첨부파일이 저장될 위치를 지정한다.
	public static String urlPath(HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root : " + root);
		String saveDirectory = root + "temp" + File.separator; //첨부파일 저장 위치 경로
		
		File fe = new File(saveDirectory); //첨부파일이 저장될 위치에 temp폴더가 없을 경우 temp폴더를 생성한다.
		if(!fe.exists())
			fe.mkdir();
				
		return saveDirectory;
	}//end urlPath()

}//end class

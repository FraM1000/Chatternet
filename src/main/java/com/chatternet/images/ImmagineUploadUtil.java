package com.chatternet.images;

import java.io.*;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;

public class ImmagineUploadUtil {
	
	 public static void saveFile(String uploadDir, String fileName,
	            MultipartFile multipartFile) throws IOException {
	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            System.out.println(filePath.toFile().getAbsolutePath());
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("impossibile salvare immagine: " + fileName, ioe);
	        }      
	    }

}

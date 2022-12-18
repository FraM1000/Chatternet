package com.chatternet.images;

import java.io.*;
import java.nio.file.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ImmagineUploadUtil {
	
	static Logger LOGGER = LoggerFactory.getLogger(ImmagineUploadUtil.class);
	
	 public static void saveFile(String uploadDir, String fileName,
	            MultipartFile multipartFile) throws IOException {
	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            LOGGER.info("Inserisco foto nel path: {}",filePath.toFile().getAbsolutePath());
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("impossibile salvare immagine: " + fileName, ioe);
	        }      
	    }

	 public static void deleteFile(String imageDir, String imageName) throws IOException {
		 Path path = Paths.get(imageDir);
		 Path imagePath = path.resolve(imageName);
		 try {
			 LOGGER.info("Elimino foto dal path: {}",imagePath.toFile().getAbsolutePath());
			 Files.delete(imagePath);
		 } catch (IOException ioe) {
			 throw new IOException("impossibile eliminare immagine: " + imageName, ioe);
		 }
	 }
}

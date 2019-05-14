package co.uk.yktech;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

	@Autowired
	private HttpServletRequest request;

	public void uploadFile(MultipartFile file, String fileName) throws IllegalStateException, IOException {
			if (!file.isEmpty()) {
				String uploadsDir = "/uploads/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}
				String filePath = realPathtoUploads + fileName;
				File dest = new File(filePath);
				file.transferTo(dest);
	}
	
}
}

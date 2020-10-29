package com.eagleshipperapi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;

public class FileUtility {

	public String getImageUrl(MultipartFile file) throws IOException {
		InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./serviceAccountKey.json");
		StorageOptions.newBuilder().setProjectId("eagleshippers-f384c").
  	    		setCredentials(GoogleCredentials.fromStream(serviceAccount)).build().getService();
		HashMap<String, String> hm = new HashMap<>();
   	    hm.put("firebaseStorageDownloadTokens", "3434434343434dfdf");
  
   	    BlobId blobId = BlobId.of("eagleshippers-f384c.appspot.com",file.getOriginalFilename());
		     
		    BlobInfo.newBuilder(blobId).
		    		setContentType("image/jpeg")
		    		.setMetadata(hm)
		    		.build();
		    
		    File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
       
	    String imageUrl = "https://firebasestorage.googleapis.com/v0/b/eagleshippers-f384c.appspot.com/o/"+convertedFile+"?alt=media&token=3434434343434dfdf";
	    return imageUrl;
	}
}

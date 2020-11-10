package com.eagleshipperapi.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.FileUtility;
import com.eagleshipperapi.bean.User;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

	private static final String TAG = "User";
	private ArrayList<User> al = new ArrayList<User>();
	private Firestore dbFirestore = FirestoreClient.getFirestore();
	
	//create new user code here
	public User createUser(MultipartFile file, User user) throws IOException {
		FileUtility image = new FileUtility();
	    String imageUrl = image.getImageUrl(file);
		user.setImageUrl(imageUrl);
		Firestore fireStore = FirestoreClient.getFirestore();
		fireStore.collection(TAG).document(user.getUserId()).set(user);
		return user;
	}
	
	//get all user 
	public ArrayList<User> getUser() throws InterruptedException, ExecutionException{
		List<QueryDocumentSnapshot>document= dbFirestore.collection(TAG).get().get().getDocuments();
		for(QueryDocumentSnapshot queryDocument : document ) {
			al.add(queryDocument.toObject(User.class));
		}
		return al;		
	}
	
	
	//get single user by id
	public User getUser(String id) throws InterruptedException, ExecutionException {
		User user =dbFirestore.collection(TAG).document(id).get().get().toObject(User.class);
		return user;
	}
	
	//delete single user by id 
	public User deleteUser(String id) throws InterruptedException, ExecutionException {
		User user =dbFirestore.collection(TAG).document(id).get().get().toObject(User.class);
		dbFirestore.collection(TAG).document(id).delete();			
		return user;
	}
	
	//update user by id without image
	public User updateUser(User user) throws InterruptedException, ExecutionException {
		User u = dbFirestore.collection(TAG).document(user.getUserId()).get().get().toObject(User.class);
		user.setImageUrl(u.getImageUrl());
		dbFirestore.collection(TAG).document(user.getUserId()).set(user);
		return user;
	}
	
	//update user by id with image
	public User updateUserById(MultipartFile file,String userId) throws InterruptedException, ExecutionException, IOException {
		String imageUrl = new FileUtility().getImageUrl(file);
		User user = dbFirestore.collection(TAG).document(userId).get().get().toObject(User.class);
		user.setImageUrl(imageUrl);
		dbFirestore.collection(TAG).document(userId).set(user);
		return user;
	}
	
}
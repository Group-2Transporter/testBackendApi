package com.eagleshipperapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.FileUtility;
import com.eagleshipperapi.bean.Transporter;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class TransporterService {

	private static final String TAG = "Transporter";
	private Firestore fireStore = FirestoreClient.getFirestore();
	
	//get all transporter
	public ArrayList<Transporter> getTransporter() throws InterruptedException, ExecutionException{
		ArrayList<Transporter>transporterList = new ArrayList<Transporter>();
		List<QueryDocumentSnapshot> list = fireStore.collection(TAG).get().get().getDocuments();	
		for(QueryDocumentSnapshot queryDocument : list ) {
			transporterList.add(queryDocument.toObject(Transporter.class));
		}
		return transporterList;		
	}
	
	
	//get Single Transporter
	public Transporter getTransporter(String id) throws InterruptedException, ExecutionException {
		Transporter t = fireStore.collection(TAG).document(id).get().get().toObject(Transporter.class);
		return t;
	}
	
	// create new transporter
	public Transporter createTransporter(Transporter transporter,MultipartFile file) throws IOException {
			
		String imageUrl = new FileUtility().getImageUrl(file);
		transporter.setImageUrl(imageUrl);
		String id = fireStore.collection(TAG).document().getId();
		transporter.setTransporterId(id);
		fireStore.collection(TAG).document(id).set(transporter);
		return transporter;
	}
	
	//delete Transporter by id
	public Transporter deleteTransporter(String id) throws InterruptedException, ExecutionException {
		Transporter t = fireStore.collection(TAG).document(id).get().get().toObject(Transporter.class);
		fireStore.collection(TAG).document(id).delete();
		return t;
	}
	
	//update transporter details
	public Transporter updateTransporter(Transporter transporter) throws IOException, InterruptedException, ExecutionException {
		fireStore.collection(TAG).document(transporter.getTransporterId()).set(transporter);
		Transporter t = fireStore.collection(TAG).document(transporter.getTransporterId()).get().get().toObject(Transporter.class);
		return t;
	}
	
	//update transporter image
		public Transporter updateTransporter(String transporterId,MultipartFile file) throws IOException, InterruptedException, ExecutionException {
			Transporter t = fireStore.collection(TAG).document(transporterId).get().get().toObject(Transporter.class);
			String imageUrl =  new FileUtility().getImageUrl(file);
			t.setImageUrl(imageUrl);
			fireStore.collection(TAG).document(transporterId).set(t);
			return t;
		}
	
}
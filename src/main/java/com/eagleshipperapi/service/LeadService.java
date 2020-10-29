package com.eagleshipperapi.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.eagleshipperapi.bean.Lead;
import com.eagleshipperapi.bean.User;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class LeadService {
	
	private static final String TAG ="Lead";
	ArrayList<Lead> al = new ArrayList<Lead>();
	Firestore dbFirestore = FirestoreClient.getFirestore();
	public Lead createNewLead(Lead lead) {
		String leadId = dbFirestore.collection(TAG).document().getId();
		lead.setLeadId(leadId);
		dbFirestore.collection(TAG).document(leadId).set(lead);
		return lead;
	}
	//get all lead by user id
		public ArrayList<Lead> getLeadByUserId(String userId) throws InterruptedException, ExecutionException{
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				al.add(queryDocument.toObject(Lead.class));
			}
			return al;		
		}
	//get single lead by id
		public Lead getLeadByLeadId(String leadId) throws InterruptedException, ExecutionException, ResourceNotFoundException {
			Lead lead =  dbFirestore.collection(TAG).document(leadId).get().get().toObject(Lead.class);
		    return lead;
		}
}

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
	
	//create lead 
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
		
	//get Created Load by userId
		public ArrayList<Lead> getCreatedLeadById(String userId) throws Exception, Exception{
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("create"))
					al.add(lead);
			}
			return al;
		}
		
	//get Confirmed Load by userId	
		public ArrayList<Lead> getConfirmedLeadById(String userId) throws Exception, Exception{
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("confirm"))
					al.add(lead);
			}
			return al;
		}
		
	//get Completed Load By userId	
		public ArrayList<Lead> getCompletedLeadById(String userId) throws Exception, Exception{
			ArrayList<Lead> al = new ArrayList<>();
			List<QueryDocumentSnapshot>document = dbFirestore.collection(TAG).whereEqualTo("userId", userId).get().get().getDocuments();
			for(QueryDocumentSnapshot queryDocument : document ) {
				Lead lead = queryDocument.toObject(Lead.class);
				if(lead.getStatus().equalsIgnoreCase("completed"))
					al.add(lead);
			}
			return al;
		}
		
	// update lead by LeadId
		public Lead updateLeadByLeadId(String leadId,Lead lead) throws InterruptedException, ExecutionException {
			Lead l = dbFirestore.collection(TAG).document(leadId).get().get().toObject(Lead.class);
			if(lead.getBidCount()!=0)
				l.setBidCount(lead.getBidCount());
			if(lead.getContactForDelivery()!=null)
				l.setContactForDelivery(lead.getContactForDelivery());
			if(lead.getContactForPickup()!=null)
				l.setContactForPickup(lead.getContactForPickup());
			if(lead.getDateOfCompletion()!=null)
				l.setDateOfCompletion(lead.getDateOfCompletion());
			if(lead.getDealLockedWith()!=null)
				l.setDealLockedWith(lead.getDealLockedWith());
			if(lead.getDeliveryAddress()!=null)
				l.setDeliveryAddress(lead.getDeliveryAddress());
			if(lead.getPickUpAddress()!=null)
				l.setPickUpAddress(lead.getPickUpAddress());
			if(lead.getStatus()!=null || lead.getStatus().equalsIgnoreCase("create")||lead.getStatus().equalsIgnoreCase("confirm"))
				l.setStatus(lead.getStatus());
			if(lead.getTypeOfMaterial()!=null)
				l.setTypeOfMaterial(lead.getTypeOfMaterial());
			l.setVehicleNumber(lead.getVehicleNumber());
			l.setWeight(lead.getWeight());
			dbFirestore.collection(TAG).document(leadId).set(l);
			return l;
		}
		
		
}

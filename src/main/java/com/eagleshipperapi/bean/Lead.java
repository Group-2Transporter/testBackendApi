package com.eagleshipperapi.bean;

public class Lead {
	private String userId;
	private String leadId;
	private String typeOfMaterial;
	private String weight;
	private String pickUpAddress;
	private String deliveryAddress;
	private String contactForPickup;
	private String contactForDelivery;
	private String dateOfCompletion;
	private long timestamp;
	private String status;
	private String vehicleNumber;
	private String dealLockedWith;
	private int bidCount;
	private String transporterName;

	public Lead() {

	}

	public Lead(String userId, String leadId, String typeOfMaterial, String weight, String pickUpAddress,
			String deliveryAddress, String contactForPickup, String contactForDelivery, String dateOfCompletion,
			long timestamp, String status, String vehicleNumber, String dealLockedWith, int bidCount, String transporterName) {
		super();
		this.userId = userId;
		this.leadId = leadId;
		this.typeOfMaterial = typeOfMaterial;
		this.weight = weight;
		this.pickUpAddress = pickUpAddress;
		this.deliveryAddress = deliveryAddress;
		this.contactForPickup = contactForPickup;
		this.contactForDelivery = contactForDelivery;
		this.dateOfCompletion = dateOfCompletion;
		this.timestamp = timestamp;
		this.status = status;
		this.vehicleNumber = vehicleNumber;
		this.dealLockedWith = dealLockedWith;
		this.bidCount = bidCount;
		this.transporterName =transporterName;
	}
;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getTypeOfMaterial() {
		return typeOfMaterial;
	}

	public void setTypeOfMaterial(String typeOfMaterial) {
		this.typeOfMaterial = typeOfMaterial;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPickUpAddress() {
		return pickUpAddress;
	}

	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContactForPickup() {
		return contactForPickup;
	}

	public void setContactForPickup(String contactForPickup) {
		this.contactForPickup = contactForPickup;
	}

	public String getContactForDelivery() {
		return contactForDelivery;
	}

	public void setContactForDelivery(String contactForDelivery) {
		this.contactForDelivery = contactForDelivery;
	}

	public String getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDealLockedWith() {
		return dealLockedWith;
	}

	public void setDealLockedWith(String dealLockedWith) {
		this.dealLockedWith = dealLockedWith;
	}

	public int getBidCount() {
		return bidCount;
	}

	public void setBidCount(int bidCount) {
		this.bidCount = bidCount;
	}
	public String getTransporterName() {
		return transporterName;
	}
	public void setTransporterId(String transporterName) {
		this.transporterName = transporterName;
	}

}

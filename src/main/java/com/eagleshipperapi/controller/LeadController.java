package com.eagleshipperapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eagleshipperapi.bean.Lead;
import com.eagleshipperapi.bean.User;
import com.eagleshipperapi.exception.ResourceNotFoundException;
import com.eagleshipperapi.service.LeadService;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	LeadService leadService;

	// create Lead
	@PostMapping("/")
	public ResponseEntity<Lead> createNewLead(@RequestBody Lead lead) {
		return new ResponseEntity<Lead>(leadService.createNewLead(lead), org.springframework.http.HttpStatus.OK);
	}

	// get All Lead
	@GetMapping("/user/{userId}")
	public ResponseEntity<ArrayList<Lead>> getLeadByUserId(@PathVariable String userId)
			throws InterruptedException, ExecutionException {
		ArrayList<Lead> al = leadService.getLeadByUserId(userId);
		return new ResponseEntity<ArrayList<Lead>>(al, org.springframework.http.HttpStatus.OK);
	}

	// get Single lead by ID
	@GetMapping("/{id}")
	public ResponseEntity<Lead> getLead(@PathVariable String id)
			throws InterruptedException, ExecutionException, ResourceNotFoundException {
		Lead lead = leadService.getLeadByLeadId(id);
		if (lead != null)
			return new ResponseEntity<Lead>(lead, org.springframework.http.HttpStatus.OK);
		else
			throw new ResourceNotFoundException("Lead not found");
	}

}
package com.bitlabs.bischeduler.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bitlabs.bischeduler.entity.Jobs;
import com.bitlabs.bischeduler.repository.JobsRepositoryInterface;

@Service
public class JobsServiceImpl implements JobsServiceInterface{

	@Autowired
	private JobsRepositoryInterface jri;
	@Override
	public boolean saveJobDetails(Jobs jobs) {
		// TODO Auto-generated method stub
		
		boolean b=false;
		Jobs j= jobs;
		if(j!=null) {
			jri.save(jobs);
		 b=true;
		}
		return b;
	}
	@Override
	public List<Jobs> getAllJobDetails() {
		// TODO Auto-generated method stub
		String str="User Details are saved";
		return jri.findAll();
	}
	@Override
	public String deleteJobDetails(int id) {
		// TODO Auto-generated method stub
	  String str="Enter your correct id";
	  Jobs j=jri.findById(id).get();
	  if(j!=null)
	  {
		  jri.deleteById(id);
		  str="jobdetails deleted successfully";
	  }
	  return str;
	}
	@Override
	public String updateJobDetails( Jobs jobdetails) {
		// TODO Auto-generated method stub
		String str="job details are not updated";
		    
		Jobs job= jri.findById(jobdetails.getId()).get();//getting from database
		
		jobdetails.setCreated_at(job.getCreated_at());//time is set to user giving details
		
		Jobs j= jri.save(jobdetails);
		
		
		
		if(j!=null) {
			str="job details are updated successfully";
		}
		return str;
	}
	@Override
	public Jobs getJobDetailsById(int id) {
		// TODO Auto-generated method stub
		return jri.findById(id).get();
	}
	
	
	}
	


package com.bitlabs.bischeduler.service;



import java.util.List;

import com.bitlabs.bischeduler.entity.Jobs;



public interface JobsServiceInterface {

	boolean saveJobDetails(Jobs jobs);

	List<Jobs> getAllJobDetails();

	String deleteJobDetails(int id);

   

	Jobs getJobDetailsById(int id);

	

	String updateJobDetails(Jobs jobdetails);

}
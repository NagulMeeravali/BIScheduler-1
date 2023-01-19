package com.bitlabs.bischeduler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.bischeduler.entity.Jobs;
import com.bitlabs.bischeduler.reponse.ResponseHandler;
import com.bitlabs.bischeduler.service.JobsServiceInterface;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class JobsController {

	@Autowired
	private JobsServiceInterface jsi;
	@PostMapping("/saveJobDetails")
	public ResponseEntity<Object> saveJob(@Valid @RequestBody Jobs jobs,@RequestHeader String s)
	{
		System.out.println(s);
		boolean b= jsi.saveJobDetails(jobs);
		if(b==true) {
			return ResponseHandler.generateResponse("job details are saved successfully", HttpStatus.OK, b);
			}
			else {
				return ResponseHandler.generateResponse("job details are not saved", HttpStatus.BAD_REQUEST, b);
			}
		}
	
@GetMapping("/getAllJobDetails")
public List<Jobs> getAllJobDetails()
{
	return jsi.getAllJobDetails();
}

@DeleteMapping("/deleteJobById")
public String deleteJobDetails(@RequestBody Jobs job) {
		
		return jsi.deleteJobDetails(job.getId());
		
	}
@PutMapping("/updateJobById")
public String updateJobDetails(@RequestBody Jobs jobdetails) {
	
	return jsi.updateJobDetails(jobdetails);

}
@GetMapping("/getJobById")
public Jobs getJobDetailsById(@RequestBody Jobs job)
{
	return jsi.getJobDetailsById(job.getId());
}

}


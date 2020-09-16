package com.foo.healthApp.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foo.healthApp.domain.Dependent;
import com.foo.healthApp.domain.Enrollee;
import com.foo.healthApp.service.EnrolleeService;

@RestController
public class HealthAppController {
	
	@Autowired
	private EnrolleeService enrolleeService;
	
	@RequestMapping(value = "/enrollee/{id}/retreive", method = RequestMethod.GET)
	public Enrollee getEnrolleeById(@PathVariable("id") Long id) {
	    return enrolleeService.getEnrolleeById(id);
	}
	
	@RequestMapping(value = "/enrollee/add", method = RequestMethod.POST)
	public Enrollee addEnrollee(@RequestBody Enrollee enrollee) {
	    return enrolleeService.insertEnrollee(enrollee);
	}
	
	@RequestMapping(value = "/enrollee/{id}/remove", method = RequestMethod.DELETE)
	public Enrollee deleteEnrollee(@PathVariable("id") Long id) {
	   return enrolleeService.deleteEnrollee(id);
	}
	
	@RequestMapping(value = "/dependent/{id}/remove", method = RequestMethod.DELETE)
	public Dependent deleteDependent(@PathVariable("id") Long id) {
	   return enrolleeService.deleteDependent(id);
	}
	
	@RequestMapping(value = "/enrollee/update", method = RequestMethod.PUT)
	public Enrollee updateEnrollee(@RequestBody Enrollee enrollee) {
	    return enrolleeService.updateEnrollee(enrollee);
	}
}

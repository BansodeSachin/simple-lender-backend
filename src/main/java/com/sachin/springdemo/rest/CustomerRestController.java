package com.sachin.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.springdemo.entity.Customer;
import com.sachin.springdemo.service.CustomerService;

@EnableGlobalMethodSecurity
@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;
	
	@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
	@GetMapping("/customers")
	@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/customers/{id}")
	@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
	public Customer getCustomerById(@PathVariable("id") int theId) {
		
		return customerService.getCustomer(theId);
	}
	
	@PostMapping("/customers")
	@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
	public Customer saveCustomer(@RequestBody Customer theCustomer) {
		
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	@PutMapping("/customers")
	@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	@DeleteMapping("/customers/{id}")
	@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
	public String deleteCustomer(@PathVariable("id") int theId) {
		
		customerService.deleteCustomer(theId);
		
		return "Customer with ID " + theId + " is deleted.";
	}
}

package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.CacheControl;*/
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.exception.CompanyCodeAlreadyExistsException;
import com.example.demo.model.Company;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.CompanyService;
import com.example.demo.model.User;

@RestController
@RequestMapping("/api/v1.0/market")
@CrossOrigin("*")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
		
	@PostMapping("/company/register")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyCodeAlreadyExistsException{
		
		//try {
			if(companyService.addCompanyDetails(company)!=null) {
				return new ResponseEntity<Company>(company, HttpStatus.OK);}
		//}
		//catch(CompanyCodeAlreadyExistsException c) {
			//return ResponseHandler.generateResponse(c.getMessage(), HttpStatus.CONFLICT);

		//}
			
		return new ResponseEntity<String>("Sorry data is not inserted!", HttpStatus.CONFLICT);		
	}

	@GetMapping("/company/info/{companyCode}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable String companyCode){
		
		Company c = companyService.getCompanyDetails(companyCode);
		
		if(c!=null) {
			return new ResponseEntity<Company>(c, HttpStatus.OK);}
		else {
			return new ResponseEntity<String>("No such company present", HttpStatus.NO_CONTENT);}
		
	}
	
	@GetMapping("/company/getAll")
	public ResponseEntity<?> getAllCompanyDetails() {

		List<Company> companyList = companyService.getAllCompanyDetails();

		if (companyList != null) {
			/*
			 * CacheControl cacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES); return
			 * ResponseEntity.ok().cacheControl(cacheControl).body(
			 * ResponseHandler.generateResponse("Succesfully retrieved the data",
			 * HttpStatus.OK, companyList));
			 */
			return new ResponseEntity<>(companyList,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No such company present", HttpStatus.NO_CONTENT);

		}
	}

	@DeleteMapping("/company/delete/{companyCode}")
	public ResponseEntity<?> deleteCompany(@PathVariable String companyCode){
		
		if(companyService.deleteCompany(companyCode)) {
			return new ResponseEntity<String>("Record Deleted!", HttpStatus.NO_CONTENT);}
		else {
			return new ResponseEntity<String>("Cannot delete due to internal error", HttpStatus.INTERNAL_SERVER_ERROR);	}
	}
	
	@PostMapping("/stock/add/{companyCode}")
	public ResponseEntity<?> addStock(@PathVariable String companyCode, @RequestBody Company company){
		
		
		if(companyService.addStockPrice(companyCode,company)!=null) {
			return new ResponseEntity<Company>(company, HttpStatus.OK);}
		else {
			return new ResponseEntity<String>("Sorry data is not inserted!", HttpStatus.CONFLICT);}
		
	}
	
	@PutMapping("/stock/put/{companyCode}")
	public ResponseEntity<?> updateStockPrice(@PathVariable String companyCode, @RequestBody Company company){
		
		
		if(companyService.updateStockPrice(companyCode,company)) {
			return new ResponseEntity<String>("Updated!", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Update unsuccessfull",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	


	/*
	 * @Autowired private DiscoveryClient discoveryClient;
	 * 
	 * public void getJWTToken(@RequestBody User user) {
	 * 
	 * List<ServiceInstance> instances =
	 * discoveryClient.getInstances("usermanagementservice"); ServiceInstance
	 * serviceInstance = instances.get(0); String baseUrl =
	 * serviceInstance.getUri().toString(); RestTemplate restTemplate = new
	 * RestTemplate(); baseUrl= baseUrl+"auth/v1/login"; String response =null;
	 * 
	 * 
	 * 
	 * try { //response = restTemplate.exchange(baseUrl,HttpMethod.GET,
	 * getHeaders(),String.class); response =
	 * restTemplate.postForObject(baseUrl,getHeaders(user), String.class);
	 * 
	 * } catch(Exception e) { e.printStackTrace(); }
	 * 
	 * System.out.println(response);//.getBody());
	 * 
	 * 
	 * }
	 * 
	 * private static HttpEntity<?> getHeaders(User user) throws Exception {
	 * HttpHeaders headers = new HttpHeaders(); headers.set("Accept",
	 * MediaType.APPLICATION_JSON_VALUE); return new HttpEntity<User>(user,headers);
	 * }
	 */


}

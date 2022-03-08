package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.controller.CompanyController;
import com.example.demo.model.Company;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {

	@Mock
	private CompanyRepository companyRepo;
	
	@InjectMocks
	private CompanyController companyController;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CompanyServiceImpl companyService;
	
	
	@Mock
	private Company company;

	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
	}
	
	

	private List<Company> comapnyList = new ArrayList<>();
	
	@Test
	public void getAllCompanySuccess() throws Exception {
		
		  Company c= new Company(); c.setCompanyCode("C1011");
		  c.setCompanyName("JoyAlukkas");
		  
		  comapnyList.add(c);
		  
		  when(companyService.getAllCompanyDetails()).thenReturn(comapnyList);
		  
		  List<Company> expectedList = companyService.getAllCompanyDetails();
		 
		  assertEquals(1, expectedList.size());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll").
				contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
				
	}
	
	
	@Test
	public void getAllCompanyFailure() throws Exception {
		
		List<Company> expectedList = companyService.getAllCompanyDetails();
		
		when(companyService.getAllCompanyDetails()).thenReturn(null);

		 assertEquals(0, expectedList.size());
				
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll").
						contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
						
		
	}
	
	
	@Test
	public void addCompanySuccess() throws Exception {
		Company c= new Company();
		List<Company> cList = new ArrayList<>();
		c.setCompanyCode("C1011");
		c.setCompanyName("JoyAlukkas");
		
		cList.add(c);
		when(companyService.addCompanyDetails(any())).thenReturn(c);
		
		
		
		assertEquals(cList.size(),1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").
				contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(c)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	
		
	}
	
	
	@Test
	public void addCompanyFailure() throws Exception {
		Company c= new Company();
		List<Company> cList = new ArrayList<>();
		c.setCompanyCode("C1011");
		c.setCompanyName("JoyAlukkas");
		
		cList.add(c);
		when(companyService.addCompanyDetails(any())).thenReturn(null);		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").
				contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(c)))
		.andExpect(MockMvcResultMatchers.status().isConflict());
	
		
	}
	
}

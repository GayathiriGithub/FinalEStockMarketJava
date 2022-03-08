package com.example.demo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.any;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;

import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.CompanyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepo;
	
	@InjectMocks
	private CompanyServiceImpl companyService = new CompanyServiceImpl();
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(companyService).build();
	}
	
	
	private List<Company> comapnyList = new ArrayList<>();
	
	@Test
	public void getAllCompanySuccess() throws Exception {
		Company c= new Company();
		c.setCompanyCode("C1011");
		c.setCompanyName("JoyAlukkas");
		
		comapnyList.add(c);
		
		when(companyRepo.findAll()).thenReturn(comapnyList);
		
		List<Company> expectedList = companyService.getAllCompanyDetails();
		
		assertEquals(comapnyList,expectedList);
		
	}
	
	
	@Test
	public void getAllCompanyFailure() throws Exception {
		when(companyRepo.findAll()).thenReturn(null);
		
		List<Company> expectedList = companyService.getAllCompanyDetails();
				assertNull(expectedList);
		
	}
	
	
	@Test
	public void addCompanySuccess() throws Exception {
		Company c= new Company();
		c.setCompanyCode("C1011");
		c.setCompanyName("JoyAlukkas");
		
		
		when(companyRepo.save(any())).thenReturn(c);
		
		
	  Company expected = companyService.addCompanyDetails(c);
		
		assertEquals(c,expected);
		
	}
	
	
	@Test
	public void addCompanyFailure() throws Exception {
		Company c= new Company();
		c.setCompanyCode("C1011");
		c.setCompanyName("JoyAlukkas");
		
		comapnyList.add(c);
		
		when(companyRepo.save(any())).thenReturn(null);
		
		
	  Company expected = companyService.addCompanyDetails(c);
		
		assertNull(expected);
		
	}
}

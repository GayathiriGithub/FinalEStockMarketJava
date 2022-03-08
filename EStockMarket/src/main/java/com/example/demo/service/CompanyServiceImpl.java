package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exception.CompanyCodeAlreadyExistsException;
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company addCompanyDetails(Company company) throws CompanyCodeAlreadyExistsException{
		if(company!=null) {
			Optional<Company> comp = companyRepository.findById(company.getCompanyCode());
			if(comp.isEmpty()){
				return companyRepository.save(company);
			}else {
				throw new CompanyCodeAlreadyExistsException();//"Company code Already Exists",HttpStatus.NOT_ACCEPTABLE);
			}
		}
		
		return null;
		
	}

	@Override
	public Company getCompanyDetails(String companyCode) {
		Optional<Company> company = companyRepository.findById(companyCode);
		if(company.isPresent()) {
			return company.get();
		}
		
		return null;
	}

	@Override
	public Boolean deleteCompany(String companyCode) {
		companyRepository.deleteById(companyCode);
		return true;
	}

	@Override
	public List<Company> getAllCompanyDetails() {
		List<Company> companyList = companyRepository.findAll();
		if( !(companyList==null)) {
			return companyList;
		}
		
		return null;
	}

	@Override
	public Boolean updateStockPrice(String companyCode, Company company) {
		Company c1 = companyRepository.getById(companyCode);
		if(company.getPrice()!=null) {
			c1.setStockDate(new Date());
			c1.setPrice(company.getPrice());
		}
		if(company.getCompanyCEO()!=null) {
			c1.setCompanyCEO(company.getCompanyCEO());

		}
		if(company.getCompanyTurnOver()!=null) {
			c1.setCompanyTurnOver(company.getCompanyTurnOver());

		}
		
		companyRepository.saveAndFlush(c1);
		return true;
	}

	@Override
	public Company addStockPrice(String companyCode, Company company) {
		if(company!=null) {
			Company c1 = companyRepository.getById(company.getCompanyCode());
			c1.setPrice(company.getPrice());
			c1.setStockDate(new Date());
			return companyRepository.save(c1);
		}
		return null;
	}
}

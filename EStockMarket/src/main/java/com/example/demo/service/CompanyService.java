package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.CompanyCodeAlreadyExistsException;
import com.example.demo.model.Company;

public interface CompanyService {

	public Company addCompanyDetails(Company company) throws CompanyCodeAlreadyExistsException;

	public Company getCompanyDetails(String companyCode);

	public Boolean deleteCompany(String companyCode);

	public List<Company> getAllCompanyDetails();

	public Boolean updateStockPrice(String companyCode, Company company);

	public Company addStockPrice(String companyCode, Company company);

}

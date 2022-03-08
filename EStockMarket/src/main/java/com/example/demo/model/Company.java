package com.example.demo.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

@Validated
@Entity
public class Company {

	@Id
	private String companyCode;
	
	@NotNull
	private String companyName;
	@NotNull
	private String companyCEO;
	@NotNull
	private BigInteger companyTurnOver;
	@NotNull
	private String website;
	@NotNull
	private String stockExchange;
	
	private Date stockDate;
	private Double price;  
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	
	public BigInteger getCompanyTurnOver() {
		return companyTurnOver;
	}
	public void setCompanyTurnOver(BigInteger companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	
	public Company() {
		super();
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Company(String companyCode, String companyName, String companyCEO, BigInteger companyTurnOver,
			String website, String stockExchange, Date stockDate, Double price) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.website = website;
		this.stockExchange = stockExchange;
		this.stockDate = stockDate;
		this.price = price;
	}
	

	
	
}

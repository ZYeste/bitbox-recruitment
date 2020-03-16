package com.zebenyesterodriguez.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "priceReduction")
public class PriceReduction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "startDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "endDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "priceReductions")
	private Set<Product> products;

	public PriceReduction() {
		super();
	}
	
	public PriceReduction(float price, Date startDate, Date endDate) {
		super();
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}

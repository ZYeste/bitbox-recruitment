package com.zebenyesterodriguez.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@ManyToOne
	private Status status;
	
	@ManyToOne
	private User user;
		
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
    	name = "productSupplier",
	    joinColumns = @JoinColumn(name = "idProduct", nullable = false),
	    inverseJoinColumns = @JoinColumn(name="idSupplier", nullable = false)	
	)
    private List<Supplier> suppliers;    
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
    	name = "productPriceReduction",
	    joinColumns = @JoinColumn(name = "idProduct", nullable = false),
	    inverseJoinColumns = @JoinColumn(name="idPriceReduction", nullable = false)	
	)
    private List<PriceReduction> priceReductions; 
    
	@Column(name = "description", length = 250)
	private String description;    	
	
	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	@Column(name = "price")
	private float price;
	
	@Column(name = "creationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	public Product() {
		super();
	}
	
	public Product(Status status, User user, String description, float price, Date creationDate) {
		super();
		this.status = status;
		this.user = user;
		this.description = description;
		this.price = price;
		this.creationDate = creationDate;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<PriceReduction> getPriceReductions() {
		return priceReductions;
	}

	public void setPriceReductions(List<PriceReduction> priceReductions) {
		this.priceReductions = priceReductions;
	}
}

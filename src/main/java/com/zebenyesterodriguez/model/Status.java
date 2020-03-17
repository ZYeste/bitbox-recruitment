package com.zebenyesterodriguez.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

	public final static Long ACTIVE = 1l;
	public final static Long DISCONTINUED = 2l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "description", length = 250)
	private String description;

	public Status() {
		super();
	}
	
	public Status(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	public Status(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

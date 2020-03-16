package com.zebenyesterodriguez.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Role idRole;
		
	@Column(name = "name", length = 250)
	private String name;
	
	@Column(name = "username", length = 250, unique = true)
	private String username;
	
	@Column(name = "email", length = 250)
	private String email;
	
	@Column(name = "password", length = 500)
	private String password;
	
	@Column(name = "creationDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "token", length = 256)
	private String token;
	
	public User() {
		super();
	}
	
	public User(Role idRole, String name, String username, String email, String password, Date creationDate) {
		super();
		this.idRole = idRole;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getIdRole() {
		return idRole;
	}

	public void setIdRole(Role idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
}

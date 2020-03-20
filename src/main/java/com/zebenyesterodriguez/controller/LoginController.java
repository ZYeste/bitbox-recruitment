package com.zebenyesterodriguez.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zebenyesterodriguez.model.User;
import com.zebenyesterodriguez.service.UserService;
import com.zebenyesterodriguez.utils.TokenUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
		
	@Autowired
	private UserService userService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("login")
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
	public ResponseEntity<String> login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		User user = null;
		ResponseEntity<String> rEToken = null;
		String error = "Incorrect user or password";
		
		// Comprobar que el usuario existe en bd 
		Optional<User> u = userService.findByUsername(username);
		if(u.isPresent()) {

			user = u.get();

			// Comprobar que la contraseña del usuario es correcta
			if(bCryptPasswordEncoder.matches(pwd, user.getPassword())) {	
				String authorization = getJWTToken(username);
				String token = TokenUtils.authToToken(authorization);
				
				user.setToken(token);
				userService.save(user);
				
				rEToken = new ResponseEntity<>(authorization, HttpStatus.OK);
				
			} else {
				rEToken = new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
			}
		} else {
			rEToken = new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
		
		return rEToken;
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities", grantedAuthorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
}

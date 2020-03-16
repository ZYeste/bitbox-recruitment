package com.zebenyesterodriguez.utils;

public class TokenUtils {
	
	/**
	 * Este método se encarga de convertir un string de cabecera autorización en token
	 * @author zebenyesterodriguez
	 * @param authorization 
	 * @return El mensaje usado para el saludo
	*/
	public static String authToToken(String authorization) {
		String token = authorization.replaceAll("Bearer", "").trim();
		return token;
	}

}

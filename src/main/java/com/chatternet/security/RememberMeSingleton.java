package com.chatternet.security;

import java.util.HashMap;

public class RememberMeSingleton {
	
	private static RememberMeSingleton token;
	
	private RememberMeSingleton() {
		
	}
	
	public static RememberMeSingleton getToken() {
		if(token == null) {
			token = new RememberMeSingleton();
		}
		return token;
	}
	
	private HashMap<String, Object> tokenDatas;

	public HashMap<String, Object> getTokenDatas() {
		if(tokenDatas == null) {
			tokenDatas = new HashMap<String, Object>();
		}
		return tokenDatas;
	}	

}

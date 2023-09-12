package com.udongsari.configure.jwt;

public interface JwtProperties {
	String SECRET = "pi3141592";
	int EXPIRATION_TIME = 864000000;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}

package com.andrelleitao.app.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Classe responsável por realizar as operações referentes ao token. 
 */
@Component
public class JwtService {	
	
	private final Logger LOGGER = LogManager.getLogger(JwtService.class);

	private final String SECRET = "lEAnEZSLi8qbVhnHe3Cixkq7Ty2wMs6VVu8pjPZsNfrymC5pwvxTFMQmaGIKXCwGXsV47ImKLB9W42bqoy7R8L49fAH233yOkiIhQVSqmPceHduTzrzy0tv6R2YKUlm4tVb7ERnOwnT2qdJOt8cjpvd7n2F5bSXsDXp5CBJZOsP3uoHLlHUA2YvdO17NHU58Fi38JCVmVyddMKOVfwk7iD8Ws5xZOsXhBQd8hYi0ml3XTHGY3QTckQWCLkqnAdtZ";

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}

	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		try {
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		} catch (ExpiredJwtException | MalformedJwtException e) {
			LOGGER.warn(e.getMessage());
			return null;
		}
	}

	private Claims extractAllClaims(String token) throws ExpiredJwtException {
		// Remove aspas, caso existam.
		token = token.replace("\"", "");
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}

package com.warriorsfly.col.component;
 
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	@Value("${jwt.secret}")
	private String secret;

	public String getSubject(String token) {
		return getUser(token, Claims::getSubject);
	}

	public Date getIssuedAt(String token) {
		return getUser(token, Claims::getIssuedAt);
	}

	public Date getExpiration(String token) {
		return getUser(token, Claims::getExpiration);
	}

	public <T> T getUser(String token, Function<Claims, T> resolver) {
		final Claims claims = getClaims(token);
		return resolver.apply(claims);
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean expired(String token) {
		final Date expiration = getExpiration(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generate(claims, userDetails.getUsername());
	}

	private String generate(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean refreshable(String token) {
		return (!expired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validate(String token) {
		return !expired(token);
	}
}

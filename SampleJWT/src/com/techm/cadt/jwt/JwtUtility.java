package com.techm.cadt.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtility {

	public static void main(String[] args) {
		String username = "testUser";
    	String password = "password";
    	String SECRET_KEY="my_jwt_token_secret";
		String token = generateToken(username,password,SECRET_KEY);
		System.out.println(token);
		verifyJwtToken(token);
		
	}
	public static String generateToken(String username,String password,String SECRET_KEY) {
	
		String jwtToken = null;
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		   jwtToken = JWT.create().withIssuer(SECRET_KEY).sign(algorithm);
		    /*JwtBuilder builder = Jwts.builder().setSubject(String.valueOf(username)).setIssuer(SECRET_KEY)
						.claim("password", password)
						.signWith(SignatureAlgorithm.HS256, SECRET_KEY);
				jwtToken = builder.compact();*/
		} catch (Exception exception){
		   System.out.println("Exception Occured..."+exception);
		}
		return jwtToken; 
	}
	
	public static void verifyJwtToken(String token){
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    System.out.println("result ::"+jwt);
		} catch (UnsupportedEncodingException exception){
			 System.out.println("Exception Occured...");
		} catch (Exception exception){
			 System.out.println("Exception Occured...");
		}
	}
	
	

	
}

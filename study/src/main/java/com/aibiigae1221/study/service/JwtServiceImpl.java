package com.aibiigae1221.study.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService{

	@Autowired
	private JwtEncoder encoder;
	
	@Override
	public String generateJwt() {
		
		Instant now = Instant.now();
		long expiry = 36000L;
		
		JwtClaimsSet claims = JwtClaimsSet.builder()
								.issuer("self")
								.issuedAt(now)
								.subject("iamkkh")
								.expiresAt(now.plusSeconds(expiry))
								.claim("nickname", "갱훈")
								.build();
		
		return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}

package com.aibiigae1221.study.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aibiigae1221.study.service.JwtService;

@RestController
public class AuthenticationController {

	
	
	@Autowired
	private JwtService jwtService;
	
		
	@PostMapping("/token")
	public Map<String, Object> token() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		String jwt = jwtService.generateJwt();
		map.put("jwt", jwt);
		return map;
	}
	
	
	
}

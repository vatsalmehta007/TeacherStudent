package com.example.TeacherStudent.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.TeacherStudent.UserRepository.UserRepository;
import com.example.TeacherStudent.service.RefreshTokenService;



@RestController
public class JwtAuthenticationController 
{
	@Autowired
	private Authentication authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	
	
}

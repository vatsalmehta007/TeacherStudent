package com.example.TeacherStudent.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable
{
	//private static final long serialVersionUID = -8091879091924046844L;
	private  String jwttoken;

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
}

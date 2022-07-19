package com.example.TeacherStudent.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private	Integer user_id;
	private String user_name;
	private String user_spec;
	private String role;
	private String email;
	private String documents;
	private LocalDateTime created_on;
	private LocalDateTime modified_on;
	
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_spec() {
		return user_spec;
	}
	public void setUser_spec(String user_spec) {
		this.user_spec = user_spec;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocuments() {
		return documents;
	}
	public void setDocuments(String documents) {
		this.documents = documents;
	}
	public LocalDateTime getCreated_on() {
		return created_on;
	}
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}
	public LocalDateTime getModified_on() {
		return modified_on;
	}
	public void setModified_on(LocalDateTime modified_on) {
		this.modified_on = modified_on;
	}
}

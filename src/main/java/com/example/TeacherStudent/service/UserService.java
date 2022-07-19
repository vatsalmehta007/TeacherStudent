package com.example.TeacherStudent.service;

import java.util.Date;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.TeacherStudent.entity.User;



public interface  UserService 
{
	public Map<String, Object>SaveUser(User user);

	public Map<String, Object> updateName(Integer user_id, String name);

	public Map<String, Object>  getstudent(String role);

	public Map<String, Object>  getalluser();
	
	public Map<String, Object> getalluserbyEmail(String searchText);

	public Map<String, Object> deletestudent(Integer user_id);

	public Map<String, Object> getUserList(String searchText);

	public Map<String, Object> getuserspec(String user_spec);

	public Map<String, Object>  getuserafterdate(Date created_on);

	public Map<String, Object> uploadDocument(Integer user_id, MultipartFile multipartFile);






}

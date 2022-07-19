package com.example.TeacherStudent.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Multipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.TeacherStudent.entity.User;
import com.example.TeacherStudent.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userservice;

	@PostMapping("/addUser")
	public ResponseEntity<Map<String, Object>> AddUser(@RequestBody User user) {
		try {
			logger.info("Inside addUser : ");
			return new ResponseEntity<>(userservice.SaveUser(user), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while registering user {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping("/users")
	public ResponseEntity<Map<String, Object>> updateUserName(@RequestParam("userid") Integer user_id,
			@RequestParam("name") String name) {
		try {
			logger.info("update user : " + user_id);
			return new ResponseEntity<>(userservice.updateName(user_id, name), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while registering user {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/student")
	public ResponseEntity<Map<String, Object>> getstudent(@RequestParam(value = "role") String role) {
		try {
			Map<String, Object> map = new HashMap<>(userservice.getstudent(role));
			logger.info("getStudent : ", map);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while getStudent {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/alluser")
	public ResponseEntity<Map<String, Object>> getAllUser() {
		try {
			logger.info("get all user : ");
			return new ResponseEntity<>(userservice.getalluser(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while getalluser {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/getEmailList")
	public ResponseEntity<Map<String, Object>> getalluserbyEmail(@RequestParam String searchText) {
		try {
			logger.info("Inside getuserBysearchtext : " + searchText);
			return new ResponseEntity<>(userservice.getalluserbyEmail(searchText), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/deletestudent")
	public ResponseEntity<Map<String, Object>> deleteStudent(@RequestParam(value = "userid") Integer user_id) {
		try {

			logger.info("deleteStudent : ", user_id);
			return new ResponseEntity<>(userservice.deletestudent(user_id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while DeletStudent {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/getuserlist")
	public ResponseEntity<Map<String, Object>> GetUserList(@RequestParam String searchText) {
		try {
			logger.info("Inside getUserListBySearchText :" + searchText);
			return new ResponseEntity<>(userservice.getUserList(searchText), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/specification")
	public ResponseEntity<Map<String, Object>> getuserspecification(@RequestParam("spec") String user_spec) {
		try {
			logger.info("getuserspec : ", user_spec);
			return new ResponseEntity<>(userservice.getuserspec(user_spec), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occured while getuserspec {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	@GetMapping("/date")
	public ResponseEntity<Map<String ,Object>> getUserByBeforDate(@RequestParam("createdate")@DateTimeFormat(pattern="yyyy-MM-dd") Date created_on)
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	{
		try 
		{
			logger.info("getUserByBeforDate : ",created_on);
			return new ResponseEntity<>(userservice.getuserafterdate(created_on),HttpStatus.OK);
		} 
		catch (Exception e) 
		{
				logger.info("Error occured while getuserspec {} :Reason :{}", e.getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	
	}
	@PostMapping("/uploadDocument")
	public ResponseEntity<Map<String,Object>>uploadDocument(@RequestParam(value = "userid") Integer user_id,
			@RequestParam("file") MultipartFile multipartFile)
	{
		try 
		{
			logger.info("uploadDocument (: ");
			return new ResponseEntity<>(userservice.uploadDocument(user_id,multipartFile),HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			logger.info("Error occured while uploadDocument {} :Reason :{}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}

	}


}
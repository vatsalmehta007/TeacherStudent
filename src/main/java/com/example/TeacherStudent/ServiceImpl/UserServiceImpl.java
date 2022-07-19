package com.example.TeacherStudent.ServiceImpl;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.TeacherStudent.Constant.ApplicationConstant;
import com.example.TeacherStudent.UserRepository.UserRepository;
import com.example.TeacherStudent.entity.User;
import com.example.TeacherStudent.service.MailService;
import com.example.TeacherStudent.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	MailService mailService;

	@Override
	public Map<String, Object> SaveUser(final User user) {
		Map<String, Object> map = new HashMap<String, Object>();

		Optional<User> user2 = userRepository.findOneByEmailIgnoreCase(user.getEmail());
		System.out.println(user2);
		User user1 = userRepository.save(populateUser(user));
		mailService.sendWelcomeMailToUser(user);

		map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
		map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.REGISTRATION_SUCCESS);
		map.put(ApplicationConstant.RESPONSE_DATA, user1);
		return map;
	}

	private User populateUser(final User user) {
		User user1 = new User();
		user1.setUser_name(user.getUser_name());
		user1.setUser_spec(user.getUser_spec());
		user1.setRole(user.getRole());
		user1.setEmail(user.getEmail());
		user1.setCreated_on(LocalDateTime.now());
		user1.setModified_on(LocalDateTime.now());
		return user1;
	}

	@Override
	public Map<String, Object> updateName(Integer user_id, String name) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(user_id);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (user != null) {
				User user1 = user.get();
				user1.setUser_name(name);
				userRepository.save(user1);
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_EDIT_SUCCESS);
				map.put(ApplicationConstant.RESPONSE_DATA, new ArrayList<>());
			}
		} catch (Exception e) {
			logger.error("Problem occured while editUserName , Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);
			map.put(ApplicationConstant.RESPONSE_DATA, new ArrayList<>());
		}
		return map;
	}

	@Override
	public Map<String, Object> getstudent(String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> user = userRepository.findByrole(role);
		try {
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA, user);

		} catch (Exception e) {
			logger.error("Problem occured while getStudent , Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);

		}
		return map;
	}

	@Override
	public Map<String, Object> getalluser() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> user = userRepository.findAll();
		try {
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA, user);
		} catch (Exception e) {

			logger.error("Problem occured while getalluser , Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);

		}
		return map;
	}

	@Override
	public Map<String, Object> getalluserbyEmail(String searchText) {
		List<String> email = userRepository.findByEmail(searchText);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
		map.put(ApplicationConstant.RESPONSE_DATA, email);
		return map;
	}

	@Override
	public Map<String, Object> deletestudent(Integer user_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Optional<User> user = userRepository.findById(user_id);
		userRepository.deleteFromUser(user.get().getUser_id());
		try {
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA, user);
		} catch (Exception e) {
			logger.error("Problem occured while deleteRole, Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserList(String searchText) {
		// TODO Auto-generated method stub
		List<String> user = userRepository.findByUserName(searchText);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
		map.put(ApplicationConstant.RESPONSE_DATA, user);
		return map;

	}

	@Override
	public Map<String, Object> getuserspec(String user_spec) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> user = userRepository.getUserSpec(user_spec);
		System.out.println(user);
		try {
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA, user);
		} catch (Exception e) {
			logger.error("Problem occured while getuserspec , Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);

		}
		return map;
	}

	@Override
	public Map<String, Object> getuserafterdate(Date created_on) {
		Map<String, Object> map = new HashMap<String, Object>();
		LocalDateTime date = new java.sql.Timestamp(created_on.getTime()).toLocalDateTime();
		System.out.println("i am here " + date);
		List<User> user = userRepository.getuserafterdate(date);
		// TODO Auto-generated method stud
		try {
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA, user);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Problem occured while getuserspec , Please check logs : " + e.getMessage());
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);
		}
		return map;
	}

	@Override
	public Map<String, Object> uploadDocument(Integer user_id, MultipartFile multipartFile) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userRepository.getById(user_id);
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name",ApplicationConstant.CLOUD_NAME,
																 "api_key",ApplicationConstant.API_KEY,
																 "api_secret",ApplicationConstant.API_SECRET));
		
		try 
		{
			
			Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
					ObjectUtils.asMap("public_id","user_profile/" + user_id));
			

			//System.out.println(uploadResult.toString());
			String url = uploadResult.get("url").toString();
			user.setDocuments(url);
		
		
			userRepository.save(user);
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
			map.put(ApplicationConstant.RESPONSE_DATA,url);
			map.put(ApplicationConstant.RESPONSE_MESSAGE,ApplicationConstant.GET_DOCUMENT_SUCESSFULLY);

		} catch (IOException  e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
			map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.SOMETING_WENT_WRONG);
		}
		return map;
	}
	   
}	   
	   
	   
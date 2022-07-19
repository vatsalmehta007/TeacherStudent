package com.example.TeacherStudent.ServiceImpl;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.TeacherStudent.UserRepository.UserRepository;
import com.example.TeacherStudent.entity.User;
import com.example.TeacherStudent.service.MailService;

@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService
{


	public static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	Session session;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendWelcomeMailToUser(User user) 
	{
		// TODO Auto-generated method stub
		try 
		{
			MimeMessage message = new MimeMessage(session);
			MimeMessageHelper helper = new MimeMessageHelper(message);
	         
		        helper.setTo(user.getEmail());
		        helper.setText("hello !"+user.getUser_name()+" Welcome to TeacherStudent project");
		        helper.setSubject("TeacherStudent small project first try");
		        sender.send(message);
		        
			logger.info("Mail sent successfully to newly created user {} - verify ", user.getEmail());
			
		}
		catch (Exception e) 
		{
			
			logger.error("Problem occured while sending mail , Please check logs : " + e.getMessage());
			
		}
			
	}

}

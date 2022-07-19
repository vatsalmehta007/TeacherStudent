package com.example.TeacherStudent.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.example.TeacherStudent.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>  
{
	
	@Query
	Optional<User> findOneByEmailIgnoreCase(String email);
	
	@Query("SELECT m FROM User m WHERE m.role= :role")
	List<User> findByrole(String role);
	
	@Query("SELECT email FROM User m WHERE m.email LIKE %:searchText%")
	List<String> findByEmail(String searchText);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM User m WHERE m.user_id= :user_id")
	void deleteFromUser(Integer user_id);

	@Query("SELECT  user_name  FROM User m WHERE m.user_name LIKE %:searchText%")
	List<String> findByUserName(String searchText);

	@Query("SELECT m.user_name FROM User m WHERE m.user_spec= :user_spec")	
	List<String> getUserSpec(String user_spec);

	@Query( "SELECT m FROM User m WHERE m.created_on <= :date")//@Query( "SELECT m FROM User m WHERE m.created_on BETWEEN date")
	List<User> getuserafterdate(LocalDateTime date);


	
//	@Transactional
//	@Modifying
//	@Query("DELETE  FROM User m WHERE m.user_id= :user_id")//@Query("DELETE FROM MeetingEntity m WHERE m.meetingId = :id")
//	Optional<User> deleteByid(Integer user_id);
		
}

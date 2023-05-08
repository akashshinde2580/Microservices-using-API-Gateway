package com.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.dto.DepartmentDto;
import com.userservice.dto.ResponseDto;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import com.userservice.util.SequenceGenaratorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private SequenceGenaratorService sequenceGenaratorService;

	public User addUser(User user) {
		user.setId(sequenceGenaratorService.generateSequence(User.SEQUENCE_NAME));
		return userRepository.save(user);
	}

	public ResponseDto getUser(Long userId) {
		ResponseDto responseDto = new ResponseDto();
		User user = userRepository.findById(userId).get();
		responseDto.setUser(user);

		DepartmentDto department = restTemplate
				.getForObject("http://department-service/api/department/byId/" + user.getDepartmentId(), DepartmentDto.class);

		responseDto.setDepartment(department);
		return responseDto;
	}
	
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	public User updateUser(long userId, User user) {
		User getUser = userRepository.findById(userId).get();
		getUser.setFirstName(user.getFirstName());
		getUser.setLastName(user.getLastName());
		getUser.setEmail(user.getEmail());
		getUser.setDepartmentId(user.getDepartmentId());
		return userRepository.save(getUser);
	}

	public void deleteUser(long userId) {
		userRepository.deleteById(userId);
	}


	
}

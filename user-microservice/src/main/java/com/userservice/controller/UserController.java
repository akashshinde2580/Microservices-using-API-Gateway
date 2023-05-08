package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.ResponseDto;
import com.userservice.model.User;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("get-all-users")
	public ResponseEntity<List<User>> getAllDepartment() {
		List<User> userResponses = userService.getAllUsers();
		return new ResponseEntity<List<User>>(userResponses, HttpStatus.CREATED);
	}

	@PostMapping("/add-user")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User savedUser = userService.addUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/update-user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
		User UpdatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<User>(UpdatedUser, HttpStatus.CREATED);
	}

	@GetMapping("get-user-by-id/{id}")
	public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
		ResponseDto responseDto = userService.getUser(userId);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("delete--user/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable("id") long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User delete successfully", HttpStatus.CREATED);

	}

}

package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exceptions.HandleExceptions;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleUserExceptions(MethodArgumentNotValidException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HandleExceptions.handleUserValidationException(ex));
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/user")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
		
		try{
			String email = user.getEmail();
			if(userService.isEmailExist(email)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id already exists");
			}else {
				String id = UUID.randomUUID().toString();
				user.setId(id);
				userService.saveUser(user);
				userService.addEmailToSet(email);
				return ResponseEntity.status(HttpStatus.CREATED).body("successfully inserted a user");
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("could not insert user");
			}
		}

	@RequestMapping(value="/users")
	public ResponseEntity<List<User>> getUsers() {
		try{
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
		}
		catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
	
}

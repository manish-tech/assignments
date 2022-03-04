package com.example.demo.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.Setter;

@Service
public class UserService {
		@Autowired
		private UserRepository userRepository;
		
		private HashSet<String> emails = new HashSet<>();
		final Integer EMAILSSIZELIMITS = 10000;
		
		public void addEmailToSet(String email){
			emails.add(email);
			clearAllEmailsWhenLimitReached();
		}
		
		public void clearAllEmailsWhenLimitReached(){
			if(emails.size() >= EMAILSSIZELIMITS) {
				emails.clear();
			}
		}
		
		public Boolean isEmailExist(String email) {
			
			if(emails.contains(email)) {
				return true;
			}
			else if(isEmailExsitInDatabase(email)) {
				return true;
			}	
			return false;
			
		}
		
		public Boolean isEmailExsitInDatabase(String email) {
			try {
				if(email.equals(userRepository.findByEmail(email).get(0).getEmail()))
					return true;
				else
					return false;
			}catch (IndexOutOfBoundsException ex) {
				// TODO: handle exception
				return false;
			}
		}
		public User saveUser(User user) {
			return userRepository.save(user);
		}
		
		public List<User> getAll(){
			return userRepository.findAll();
		}
}		

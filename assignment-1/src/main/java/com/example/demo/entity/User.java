package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user1",uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User {

	@Id
	private String id;
	
	@NotEmpty
	@Email(message = "invalid Email Id")
	private String email;
	
	@NotEmpty
	@Length(min = 2,max = 40,message = "name size should be between 2 to 40")
	@Pattern(regexp = "^[A-Za-z]*$",message = "invalid name : name should contain only English Alphabets")
	private String name;
	
	
	
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public void setEmail(String email) {
		this.email = email.trim();
	}
	
	
}

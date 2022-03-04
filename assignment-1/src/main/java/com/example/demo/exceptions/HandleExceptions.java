package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


public class HandleExceptions {
	
	public static Map<String, String> handleUserValidationException( MethodArgumentNotValidException ex) {
			    Map<String, String> errors = new HashMap<>();
			    ex.getBindingResult().getAllErrors().forEach((error) -> {
			        String fieldName = ((FieldError) error).getField();
			        String errorMessage = error.getDefaultMessage();
			        errors.put(fieldName, errorMessage);
			    });
			    return errors;
		}

}

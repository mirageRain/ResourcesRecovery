package com.danfeng.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.danfeng.service.impl.UserServiceImpl;

public class UserNameConstraintValidator implements ConstraintValidator<UserNameConstraint, Object>{

	@Autowired
	private UserServiceImpl UserServiceImpl;
	
	@Override
	public void initialize(UserNameConstraint userNameConstraint) {
		
		
	}

	@Override
	public boolean isValid(Object username, ConstraintValidatorContext context) {
		
		return true;
	}

}

package com.ben.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.ben.bookclub.models.LoginUser;
import com.ben.bookclub.models.User;
import com.ben.bookclub.repositories.UserRepository;
    

    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
// 		register method
    
    public User register(User newUser, BindingResult results) {
    
//    	check to see if password and confirm password match
    if (!newUser.getPassword().equals(newUser.getConfirm())) {
    	results.rejectValue("confirm", "ConfirmPass", "Password and confirm password must match.");
    }
//    	if email already exists in the database
    if (checkEmail(newUser.getEmail())) {
    	results.rejectValue("email", "Email", "Email already exists in the Database");
    }
    
    if(results.hasErrors()) {
    	return null;
    }
//    	Hash password
    String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    newUser.setPassword(hashed);
    	return userRepo.save(newUser);

}

//    	Method for logging in the user
    public User login(LoginUser newLoginObject, BindingResult results) {
//    	additional validation

//		Find the user in the DB
    	if (!this.checkEmail(newLoginObject.getEmail())) {
    		results.rejectValue("email", "Non-existant", "Invalid Credentials");
    	}
        if(results.hasErrors()) {
        	return null;
        }
//      Check to see if Password matches Password in DB
        User user = userRepo.findByEmail(newLoginObject.getEmail()).orElse(null);
    	if(!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
    	    results.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	if(results.hasErrors()) {
    		return null;
    	}

    	return user;
    }


//    	Check if email already in DB
	public boolean checkEmail(String email) {
		Optional<User> user = userRepo.findByEmail(email);
		
		if(user.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public User getOneUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}

}


    

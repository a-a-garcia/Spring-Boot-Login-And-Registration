package com.anthonygarcia.loginandregistration.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.anthonygarcia.loginandregistration.models.LoginUser;
import com.anthonygarcia.loginandregistration.models.User;
import com.anthonygarcia.loginandregistration.repositories.UserRepository;
	    
	@Service
	public class UserService {
	    
	    @Autowired
	    private UserRepository userRepo;
	    
	 // This method will be called from the controller
	    // whenever a user submits a registration form.
	    // TO-DO: Write register and login methods!
	    public boolean register(User newUser, BindingResult result) {
	        // TO-DO: Additional validations!
	    	
	    	// TO-DO - Reject values or register if no errors:
	    	
	        // Reject if email is taken (present in database)
	        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
	        if(potentialUser.isPresent()  == true) {
	        	result.rejectValue("email", "emailError", "Acccount using this email already exists.");
	        }
	        // Reject if password doesn't match confirmation
	        if(!newUser.getPassword().equals(newUser.getConfirm())) {
	        	result.rejectValue("confirm", "confirmError", "Password fields do not match.");
	        }
	        
	        // Return null if result has errors
	        if(result.hasErrors()) {
	        	return false;
	        }
	        // Hash and set password, save user to database
	        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
	        newUser.setPassword(hashed);
	        userRepo.save(newUser);
	        return true;
	    }
	    
	    // This method will be called from the controller
	    // whenever a user submits a login form.
	    public User login(LoginUser newLoginObject, BindingResult result) {
	        // TO-DO: Additional validations!
	    	// TO-DO - Reject values:
	    	if (result.hasErrors()) {
	    		return null;
	    	}
	        
	    	// Find user in the DB by email
	    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
	        // Reject if NOT present
	    	if (!potentialUser.isPresent()) {
	    		result.rejectValue("email", "emailError", "Account with this email does not exist.");
	    		return null;
	    	}
	    	
	    	//User userInDb = userRepo.findByEmail(user.getEmail().orElse(null);
	    	//if (userInDb == null) {
//	    			result.rejectValue("email","invalid","Invalid login.");
//	    			return false;
//	    }
	    	
//	    	Boolean matches = BCrypt.checkpw(user.getPassword(), userInDb.getPassword());
	    	
	    	//if(!matches) {
		//	    	result.rejectValue("password","invalid","Invalid Login");
		//	    	return false
//	    }
	    	
//	    	return true
//	    }
	        
	    	User user = potentialUser.get();
	        // Reject if BCrypt password match fails 
	    	if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
	    		result.rejectValue("password", "Matches", "Invalid Password!");
	    		return null;
	    	}
	    
	        // Return null if result has errors
	        // Otherwise, return the user object
	        return user;
	    }
	    
	    public Optional<User> getUserById(Long id) {
	    	return userRepo.findById(id);
	    }
	}

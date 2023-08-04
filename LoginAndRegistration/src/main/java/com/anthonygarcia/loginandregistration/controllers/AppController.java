package com.anthonygarcia.loginandregistration.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonygarcia.loginandregistration.models.LoginUser;
import com.anthonygarcia.loginandregistration.models.User;
import com.anthonygarcia.loginandregistration.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AppController {
    
    // Add once service is implemented:
     @Autowired
     private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(!userServ.register(newUser, result)) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        } else {
        	userServ.register(newUser, result);
        	session.setAttribute("user", newUser.getId());
        
        
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
    
        return "redirect:/welcome";
        }
    };
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
    	User user = userServ.login(newLogin, result);
    
        if (user == null) {
        	model.addAttribute("newUser", new User());
        	return "index.jsp";
        }
        
       session.setAttribute("user", user.getId());
       
//       used to print what's in session
       
       return "redirect:/welcome";
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
    }
    
    @GetMapping("/welcome")
    public String dashboard(Model model, HttpSession session, RedirectAttributes flashAttributes) {
    	//retrieve uer's id from session, must cast it from object form (session is an object) into a long.
    	Long userId = (Long) session.getAttribute("user");
    	
    	if (userId == null) {
    		//if the user's ID is not found in the session, redirect to login page
    		flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
    		return "redirect:/";
    	}
    	
    	//fetch the other details of said user
    	Optional<User> loggedInUser = userServ.getUserById(userId);
    	
    	if (loggedInUser.isPresent()) {
    		//if user is found in db, retrieve them
    		User user = loggedInUser.get();
    		
    		//Now add user to model to be displayed on jsp
    		
    		model.addAttribute("user", user);
    		
    		return "welcome.jsp";
    	} else {
    		return "redirect:/login";
    	}
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}

//YOU CAN USE THIS TO SEE WHAT IS IN SESSION, IF THERES NOTHING THEN IT WILL SAY SESSION IS EMPTY.
//if (session.getAttributeNames().hasMoreElements()) {
//    for (String attributeName : Collections.list(session.getAttributeNames())) {
//        Object attributeValue = session.getAttribute(attributeName);
//        System.out.println(attributeName + " : " + attributeValue);
//    }
//} else {
//    System.out.println("Session is empty.");
//}

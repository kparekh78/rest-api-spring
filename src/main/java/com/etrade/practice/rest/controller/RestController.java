package com.etrade.practice.rest.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.etrade.practice.rest.model.User;

@Controller
@RequestMapping (value="/rest")
public class RestController {

	@RequestMapping(value="/users", produces="application/json", method=RequestMethod.GET)
	@ResponseBody
	public List getUsers(HttpServletRequest request) throws IOException{
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathTranslated());		
		try {
			String loginurl = "sit.etrade.com/login";
			boolean generateTarget = true;
			String overrideTargertUrl = null;
		            
		            if (generateTarget) {
		                if (overrideTargertUrl != null) {
		                    loginurl = loginurl + "?TARGET="
		                            + URLEncoder.encode(overrideTargertUrl);
		                    System.out.println("1 - " + URLEncoder.encode(overrideTargertUrl));
		                }
		                else {
		                    loginurl = loginurl + "?TARGET="
		                            + URLEncoder.encode(request.getRequestURL().toString());
		                    System.out.println("2 - " + URLEncoder.encode(request.getRequestURL().toString()));
		                    //TODO: add constant for GET
		                    if (request.getMethod().equals("GET")) {
		                        String querry = request.getQueryString();
		                        if (querry != null) {
		                            loginurl = loginurl + URLEncoder.encode("?" + querry);
		                            System.out.println("3 - "+ URLEncoder.encode("?" + querry));
		                        }
		                        
		                    }
		                }
		            }
		            System.out.println("url - " + loginurl);
		        }
		        catch (Exception e) {
		            System.out.println("Error generating redirect url" + e.getMessage());
		        }
		        
				
				
				
			
		
		List usersList = new ArrayList();
		usersList.add("kparekh");
		usersList.add("kparekh2");
		
		return usersList;
		
	}
	
	@RequestMapping (value="/users/{userId}", method = RequestMethod.GET, produces="application/json" )
	public @ResponseBody User getUserDetails(@PathVariable ("userId") String user){
		
		if(user.equals("kparekh")){
			throw new NullPointerException();
		}
		User u = new User();
		u.setFirstName(user);
		u.setLastName("parekh");
		
		
		
		
		return u;
	}
	
	@ExceptionHandler (NullPointerException.class)
	@ResponseStatus (value = HttpStatus.BAD_REQUEST)
	public void handleException(){
		
	}
	
	@RequestMapping (value="/users/test", method = RequestMethod.GET )
	
	public void checkRequestParams (@Valid @ModelAttribute User user, BindingResult results){
		
		if(results.hasErrors()){
			throw new NullPointerException();
		}
		
		
	}
	
	@RequestMapping (value="/checkURL", method = RequestMethod.GET)
	public void checkURL(HttpServletRequest request){
		
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(request.getPathTranslated());	
		System.out.println(URLEncoder.encode("http://localhost.etrade.com:8080/rest/checkURL?test=test"));
		System.out.println(URLEncoder.encode("http://localhost.etrade.com:8080/rest/checkURL#/viewname"));
		System.out.println(URLEncoder.encode("http://localhost.etrade.com:8080/rest/checkURL#/viewname?test=test"));


try {
	String loginurl = "sit.etrade.com/login";
	boolean generateTarget = true;
	String overrideTargertUrl = null;
            
            if (generateTarget) {
                if (overrideTargertUrl != null) {
                    loginurl = loginurl + "?TARGET="
                            + URLEncoder.encode(overrideTargertUrl);
                    System.out.println("1 - " + URLEncoder.encode(overrideTargertUrl));
                }
                else {
                    loginurl = loginurl + "?TARGET="
                            + URLEncoder.encode(request.getRequestURL().toString());
                    System.out.println("2 - " + URLEncoder.encode(request.getRequestURL().toString()));
                    //TODO: add constant for GET
                    if (request.getMethod().equals("GET")) {
                        String querry = request.getQueryString();
                        if (querry != null) {
                            loginurl = loginurl + URLEncoder.encode("?" + querry);
                            System.out.println("3 - "+ URLEncoder.encode("?" + querry));
                        }
                        
                    }
                }
            }
            System.out.println("url - " + loginurl);
        }
        catch (Exception e) {
            System.out.println("Error generating redirect url" + e.getMessage());
        }
        
		
		
		
		
	}
}

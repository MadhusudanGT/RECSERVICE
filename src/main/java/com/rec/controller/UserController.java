package com.rec.controller;

import java.sql.Timestamp; 
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rec.exception.ResourceNotFoundException;
import com.rec.dao.Contactdao;
import com.rec.dao.Userdao;
import com.rec.dao.service.UserService;
import com.rec.model.ContactModel;
import com.rec.model.RoleModel;
import com.rec.model.UserModel;
import com.rec.repository.ContactRepo;
import com.rec.repository.UserRepository;
import com.rec.util.UserValidation;
//import com.rec.util.UserValidation;
import com.rec.util.UserValidation;


@CrossOrigin 
@RestController
 
@RequestMapping("/usr")
public class UserController {
	
	@Autowired
	Userdao userservice;
	@Autowired
	UserRepository repo;
	@Autowired
	UserValidation userval;
	@GetMapping("/")
	public String get() {
		return "home";
	}
	
	@PutMapping("/update/{id}")
	public UserModel updateuser(@PathVariable(value="id") Long id,@Valid @RequestBody UserModel data) throws ResourceNotFoundException {
	System.out.println(id+" "+data);
//	userval.validate(UniqueConstraint., bindingResult);
	return userservice.updateuser(id,data);
	

	}
//	@PostMapping(path="/create",consumes = "application/json", produces = "application/json")
//	public ResponseEntity<UserModel> createuser(@RequestBody UserModel data) {
//
//	ResponseEntity<UserModel> response =null;
//	UserModel status = null;
//    
//
//	try {
//	status =userservice.addUser(data);
//	response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
//	}
//	catch(Exception e) {
//	response = new ResponseEntity<UserModel>(status,HttpStatus.BAD_REQUEST);
//
//		ResponseEntity<UserModel> response1 =null;
//		UserModel status1 = null;
//		 try {
//			 status1 =userservice.addUser(data);
//			 response1= new ResponseEntity<UserModel>(status1, HttpStatus.OK);
//		 }
//		 catch(Exception e1) {
//			 response1 = new ResponseEntity<UserModel>(status1,HttpStatus.BAD_REQUEST);
//		 }
//		
//		
//		
//		
//		 return  response1;
//
//	}
//
//
//
//
//	return response;
//	}

	 @PostMapping(path="/create",consumes="application/json", produces="application/json")
	    public ResponseEntity<Object> create(@RequestBody UserModel user) {
		 System.out.println("i amincreate");
	        return userservice.createUser(user);
	    }

	@GetMapping(path="/user/{UserId}" , produces="application/json")
	public ResponseEntity<UserModel> getById(@PathVariable(value="UserId") long UserId) throws ResourceNotFoundException {

		

	return userservice.getUserById(UserId);
	} 
	
	@GetMapping("/users")
	public List<UserModel> getAll(){
	return repo.findAll();
	}
	 @PostMapping(path="/createuser",consumes="application/json", produces="application/json")
	    public ResponseEntity<Object> createUser() {
		 System.out.println("i amincreate");
	        return null;
	    }

	 @GetMapping(path="/search")
		public List<UserModel> search(@Param("keyword") String keyword) {
			ResponseEntity<UserModel> response =null;
			UserModel status = null;
			try {

			if (userval.isWord(keyword)) {
			System.out.print("string");
			List<UserModel> result=userservice.search(keyword);
			response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
			return result;
			}
			
			else if(userval.isNumber(keyword)) {
			System.out.print("number");
			Long i=Long.parseLong(keyword);
			// System.out.print(i);
			List<UserModel> result=userservice.searchnumber(i);
			response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
			System.out.print(result);
			return result;
			}
			
			else if(userval.isDate(keyword)) {
			System.out.print("find by date"+keyword+"find by date");
			List<UserModel> result= userservice.finduserbydate(new SimpleDateFormat("yyyy-MM-dd").parse(keyword));
			response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
			return result;

			}
			else if(userval.isPhoneNo(keyword)) {
			System.out.print("it is phone no"+keyword);
			List<UserModel> result=userservice.findbyphoneno(keyword);
			response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
			System.out.print(result);
			return result;
			}

			else{
			System.out.println("combination of string and number");
			List<UserModel> result=userservice.findByEmail(keyword);
			response= new ResponseEntity<UserModel>(status, HttpStatus.OK);
			return result;

			}			
				
			}catch(Exception e) {
				
				response= new ResponseEntity<UserModel>(status, HttpStatus.BAD_REQUEST);

			}
			return null;
		}


	
	
	 @DeleteMapping("/users/{id}")
		public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
			
			return this.userservice.deleteuser(id);
		}
	
	@GetMapping("/activeUsers")
	public List<UserModel> activeusers () {
		System.out.println("iam in active");
		return repo.findBystatus("Active");
		
		
	}
	
	@GetMapping("/inActiveUsers")
	public List<UserModel> inactiveusers () {
		System.out.println("iam in active");
		return repo.findBystatus("inActive");
		
	}
	
	
	

	}
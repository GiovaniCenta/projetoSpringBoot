package com.example.demo.recursos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.User;

@RestController
@RequestMapping(value = "/users",method=RequestMethod.GET)
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1,"JHose","jose@gmail.com","998998","12345");
		return ResponseEntity.ok().body(u);
	}

	

}

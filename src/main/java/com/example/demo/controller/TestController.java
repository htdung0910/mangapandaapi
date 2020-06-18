package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.TestRepo;
import com.example.demo.repo.UserRepo;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestRepo repo;
	
	@Autowired
	UserRepo userRepo;

	@GetMapping("/hello")
	public Object asd() {
		return userRepo.getAllUserName();
	}
}

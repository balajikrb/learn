package com.example.mymicro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mymicro.beans.MyApp;

@RestController
@RequestMapping("/myapp")
public class MyController {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<MyApp> getAll() {
		MyApp response = new MyApp();
		response.setName("Balaji");
		response.setAddress("Address1");
		List<MyApp> listOfMyApp = new ArrayList<>();
		listOfMyApp.add(response);
		return listOfMyApp;
	}
}

package com.kan.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.myschool.bean.LoginBean;
import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/loginID/{loginID}/password/{password}", method = RequestMethod.POST)
	@ResponseBody
	public String login(@PathVariable String loginID , @PathVariable String password) throws IOException {
		LoginBean login = amazonDataService.login(loginID , password);
		if(login == null) {
			return "FAILED";
		} else {
			return "SUCCESS";
		}
	}

}

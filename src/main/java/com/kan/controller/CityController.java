package com.kan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.myschool.bean.CityBean;
import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/getAllCity", method = RequestMethod.GET)
	@ResponseBody
	public List<CityBean> getAllCity() throws IOException {
		return amazonDataService.getAllCity();
	}
}

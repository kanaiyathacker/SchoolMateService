package com.kan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.myschool.bean.CityBean;
import com.kan.myschool.bean.CitySchoolBean;
import com.kan.service.AmazonDataService;


@Controller
@RequestMapping("/citySchool")
public class CitySchoolController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/cityId/{cityID}", method = RequestMethod.GET)
	@ResponseBody
	public List<CitySchoolBean> getSchools(@PathVariable String cityID) throws IOException {
		return amazonDataService.getSchools(cityID);
	}

}

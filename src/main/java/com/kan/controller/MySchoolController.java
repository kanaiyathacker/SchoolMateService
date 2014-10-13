package com.kan.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.bean.RightAutoCalulatedRatingBean;
import com.kan.myschool.bean.MySchoolBean;
import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/myschool")
public class MySchoolController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/myschoolID/{myschoolID}/modelID/{modelID}", method = RequestMethod.GET)
	@ResponseBody
	public MySchoolBean getMySchoolModelDesc(@PathVariable String myschoolID , @PathVariable String modelID) throws IOException {
		System.out.println("localhost_access_log.2014-10-06.txt -- " + myschoolID + "--- " + modelID);

		MySchoolBean mySchoolModelDesc = amazonDataService.getMySchoolModelDesc(myschoolID , modelID);
		System.out.println("localhost_access_log.2014-10-06.txt -- " + mySchoolModelDesc.getDescription());
		return mySchoolModelDesc;
	}
}

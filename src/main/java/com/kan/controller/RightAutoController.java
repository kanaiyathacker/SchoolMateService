package com.kan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.bean.RightAutoBean;
import com.kan.bean.RightAutoCalulatedRatingBean;
import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/rightAuto")
public class RightAutoController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/saveDetails/{autoNumber}/{cpm}/{recd}/{extraCharged}", method = RequestMethod.POST)
	@ResponseBody
	public String save(@PathVariable String autoNumber , @PathVariable String cpm , @PathVariable String recd , @PathVariable String extraCharged) throws IOException {
		System.out.println(autoNumber  + cpm);
		amazonDataService.saveAutoDetails(autoNumber, cpm, recd, extraCharged);
 		return autoNumber  + cpm;
	}
	
	@RequestMapping(value = "/getDetails/{autoNumber}", method = RequestMethod.GET)
	@ResponseBody
	public List<RightAutoBean> getDetails(@PathVariable String autoNumber) throws IOException {
		System.out.println(autoNumber);
		return amazonDataService.getAutoDetails(autoNumber);
	}
	
	@RequestMapping(value = "/getRightAutoCalulatedRating/{autoNumber}", method = RequestMethod.GET)
	@ResponseBody
	public RightAutoCalulatedRatingBean getRightAutoCalulatedRating(@PathVariable String autoNumber) throws IOException {
		System.out.println(autoNumber);
		return amazonDataService.getRightAutoCalulatedRating(autoNumber);
	}
}

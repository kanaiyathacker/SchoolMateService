package com.kan.controller;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/device")
public class DeviceController {
	
	ResourceBundle bundle = ResourceBundle.getBundle("AwsCredentials") ;
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/{uuid}/{device}", method = RequestMethod.POST)
	@ResponseBody
	public String save(@PathVariable String uuid , @PathVariable String device ) throws IOException {
		System.out.println(uuid  + device);
		amazonDataService.save(uuid, device);
 		return uuid + device;
 
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getDevice(@RequestParam(value = "accessKey") String accessKey , 
			@RequestParam(value = "secretKey") String secretKey) throws IOException {
		System.out.println("getDevice");
		if(bundle.getString("accessKey").equals(accessKey) && bundle.getString("secretKey").equals(secretKey)) {
			return amazonDataService.list();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		ResourceBundle bundle = ResourceBundle.getBundle("AwsCredentials") ;
		System.out.println(bundle.getString("accessKey"));
		System.out.println(bundle.getString("secretKey"));		
	}
}

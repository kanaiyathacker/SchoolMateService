package com.kan.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kan.bean.DayTimeTable;
import com.kan.myschool.bean.WeeklyTimeTableBean;
import com.kan.service.AmazonDataService;

@Controller
@RequestMapping("/myschoolTimeTable")
public class TimeTableController {
	
	@Autowired
	private AmazonDataService amazonDataService;
	
	@RequestMapping(value = "/weekly/schoolID/{schoolID}/className/{className}/section/{section}", method = RequestMethod.GET)
	@ResponseBody
	public List<WeeklyTimeTableBean> getWeeklyTT(@PathVariable String schoolID , @PathVariable String className , @PathVariable String section) throws IOException {
		return amazonDataService.getWeeklyTT(schoolID, className, section);
	}
	
	@RequestMapping(value = "/day/{day}schoolID/{schoolID}/className/{className}/section/{section}", method = RequestMethod.GET)
	@ResponseBody
	public List<DayTimeTable> getWeeklyTT(@PathVariable String day , @PathVariable String schoolID , @PathVariable String className , @PathVariable String section) throws IOException {
		return amazonDataService.getDayTT(schoolID, className, section);
	}

}

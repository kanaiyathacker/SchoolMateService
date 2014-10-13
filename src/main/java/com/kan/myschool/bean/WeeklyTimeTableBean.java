package com.kan.myschool.bean;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="MySchoolTable")
public class WeeklyTimeTableBean {
	String timetableId;
	String schoolId;
	String className;
	String day;
	String startTime;
	String endTime;
	String profName;
	String profID;
	String section;
	
	@DynamoDBHashKey(attributeName="schoolId")
	public String getTimetableId() {
		return timetableId;
	}
	public void setTimetableId(String timetableId) {
		this.timetableId = timetableId;
	}
	
	@DynamoDBRangeKey(attributeName="modelId")
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	@DynamoDBAttribute(attributeName="className")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	@DynamoDBAttribute(attributeName="day")
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	@DynamoDBAttribute(attributeName="startTime")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@DynamoDBAttribute(attributeName="endTime")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@DynamoDBAttribute(attributeName="profName")
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	
	@DynamoDBAttribute(attributeName="profId")
	public String getProfID() {
		return profID;
	}
	public void setProfID(String profID) {
		this.profID = profID;
	}
	
	@DynamoDBAttribute(attributeName="section")
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
}

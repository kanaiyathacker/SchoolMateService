package com.kan.bean;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="RightAutoCalulatedRating")
public class RightAutoCalulatedRatingBean {
	String autoNumber;
	String id;
	String averageRating;
	String lastRating;
	String lastRatingDate;
	String createdDate;
	
	@DynamoDBHashKey(attributeName="autoNumber")
	public String getAutoNumber() {
		return autoNumber;
	}
	public void setAutoNumber(String autoNumber) {
		this.autoNumber = autoNumber;
	}
	
	@DynamoDBRangeKey(attributeName="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName="averageRating")
	public String getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(String averageRating) {
		this.averageRating = averageRating;
	}
	
	@DynamoDBAttribute(attributeName="lastRating")
	public String getLastRating() {
		return lastRating;
	}
	public void setLastRating(String lastRating) {
		this.lastRating = lastRating;
	}
	
	@DynamoDBAttribute(attributeName="lastRatingDate")
	public String getLastRatingDate() {
		return lastRatingDate;
	}
	public void setLastRatingDate(String lastRatingDate) {
		this.lastRatingDate = lastRatingDate;
	}
	
	@DynamoDBAttribute(attributeName="createdDate")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}

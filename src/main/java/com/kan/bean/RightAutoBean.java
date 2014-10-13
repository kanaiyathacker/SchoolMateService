package com.kan.bean;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@DynamoDBTable(tableName="RightAutoDetailsTable")
public class RightAutoBean {
	private String id;
	private String autoNumber;
    private String cpm;
    private String recd;
    private String chargedExtra;
    private String createdDate;
    
    @DynamoDBHashKey(attributeName="id")
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@DynamoDBAttribute(attributeName="cpm")
	public String getCpm() {
		return cpm;
	}
	public void setCpm(String cpm) {
		this.cpm = cpm;
	}
	
	@DynamoDBAttribute(attributeName="recd")
	public String getRecd() {
		return recd;
	}
	public void setRecd(String recd) {
		this.recd = recd;
	}
	
	@DynamoDBAttribute(attributeName="chargedExtra")
	public String getChargedExtra() {
		return chargedExtra;
	}
	public void setChargedExtra(String chargedExtra) {
		this.chargedExtra = chargedExtra;
	}
	
	@DynamoDBAttribute(attributeName="createdDate")
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@DynamoDBRangeKey(attributeName="autoNumber")
	public String getAutoNumber() {
		return autoNumber;
	}
	public void setAutoNumber(String autoNumber) {
		this.autoNumber = autoNumber;
	}
}

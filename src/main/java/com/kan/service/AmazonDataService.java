package com.kan.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.kan.bean.DayTimeTable;
import com.kan.bean.RightAutoBean;
import com.kan.bean.RightAutoCalulatedRatingBean;
import com.kan.myschool.bean.CityBean;
import com.kan.myschool.bean.CitySchoolBean;
import com.kan.myschool.bean.LoginBean;
import com.kan.myschool.bean.MySchoolBean;
import com.kan.myschool.bean.WeeklyTimeTableBean;
import com.kan.util.Utility;


@Service
public class AmazonDataService {
	
	private AmazonDynamoDBClient client1;
	
	public AmazonDataService() throws Exception {
//		createClient();	
	}
	
	public static void save(final String uuid , String deviceUser) throws IOException {
		
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);
       
        IOSAppDeviceTokenTable t = new IOSAppDeviceTokenTable();
        t.setUuid(uuid);
        t.setDeviceName(deviceUser);
        mapper.save(t);
	}
	
	public void saveAutoDetails(String autoNumber , String cpm , String recomonded , String extraCharged) throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);
        RightAutoBean t = new RightAutoBean();
        t.setId(""+Calendar.getInstance().getTimeInMillis());
        t.setAutoNumber(autoNumber);
        t.setCpm(cpm);
        t.setRecd(recomonded);
        t.setChargedExtra(extraCharged);
        t.setCreatedDate(Utility.getCurrentDateAsString());
        mapper.save(t);
        List<RightAutoBean> autoDetails = getAutoDetails(autoNumber);
        System.out.println("List ..." + autoDetails.size());
        if(autoDetails != null && !autoDetails.isEmpty()) {
        	RightAutoCalulatedRatingBean rightAutoCalulatedRatingBean = calculateRating(autoDetails);
        	rightAutoCalulatedRatingBean.setAutoNumber(autoNumber);
        	rightAutoCalulatedRatingBean.setCreatedDate(Utility.getCurrentDateAsString());
        	rightAutoCalulatedRatingBean.setLastRatingDate(Utility.getCurrentDateAsString());
        	rightAutoCalulatedRatingBean.setLastRating(""+getSumOfSelection(t));
        	rightAutoCalulatedRatingBean.setId("0");
        	saveRightAutoCalulatedRating(rightAutoCalulatedRatingBean);
        }
        
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);
        
        AmazonDataService s = new AmazonDataService();
        LoginBean login = s.login("kanaiya", "kanaiya");
        System.out.println(login);
        
       
//        MySchoolBean load = mapper.load(MySchoolBean.class, "1012", "001");
//        System.out.println(load.getDescription());
		
	
//		AWSCredentials credentials = new PropertiesCredentials(
//        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
//		AmazonDynamoDBClient amazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
//		
//
//		Condition hashKeyCondition = new Condition()
//		    .withComparisonOperator(ComparisonOperator.EQ)
//		    .withAttributeValueList(new AttributeValue().withS("101"));
//
//		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
//		keyConditions.put("schoolId", hashKeyCondition);
//
//		Condition hashKeyCondition1 = new Condition()
//	    .withComparisonOperator(ComparisonOperator.EQ)
//	    .withAttributeValueList(new AttributeValue().withS("001"));
//
//		Map<String, Condition> keyConditions1 = new HashMap<String, Condition>();
//		keyConditions1.put("modelId", hashKeyCondition1);
//	
//		QueryRequest queryRequest = new QueryRequest()
//		    .withTableName("MySchoolTable")
//		    .withKeyConditions(keyConditions)
//		    .withKeyConditions(keyConditions1)
//		    ;
//		
//		
//		QueryResult result = amazonDynamoDBClient.query(queryRequest);
//		for (Map<String, AttributeValue> item : result.getItems()) {
//			System.out.println(item);
//		}
		
//		QueryResult result = client.query(queryRequest);
//		for (Map<String, AttributeValue> item : result.getItems()) {
//		    printItem(item);
//		}
		
		
		
		
//		List<RightAutoBean> autoDetails = new ArrayList<RightAutoBean>();
//		RightAutoBean bean = new RightAutoBean();
//		bean.setChargedExtra("N");
//		bean.setCpm("Y");
//		bean.setRecd("Y");
//		autoDetails.add(bean);
//		
//		bean = new RightAutoBean();
//		bean.setChargedExtra("Y");
//		bean.setCpm("Y");
//		bean.setRecd("Y");
//		autoDetails.add(bean);
//		
//		AmazonDataService amazonDataService = new AmazonDataService();
//		RightAutoCalulatedRatingBean calculateRating = amazonDataService.calculateRating(autoDetails );
//		System.out.println(calculateRating.getAverageRating());
		RightAutoCalulatedRatingBean bean = new AmazonDataService().getRightAutoCalulatedRating("9900");
//		System.out.println(bean.getAverageRating());
		//save("1", "IOS");
//		ClientConfiguration clientConf = new ClientConfiguration();
//		ClientConfiguration awsCredentials = new ClientConfiguration(clientConf);
//		AmazonDynamoDBClient client = new AmazonDynamoDBClient(awsCredentials);
		
//		 Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
//         item.put("uuid", new AttributeValue().withS("101"));
//         item.put("deviceName", new AttributeValue().withS("iosApp"));
//         PutItemRequest itemRequest = new PutItemRequest().withTableName("EdinburghHinduTableDeviceTokenTable").withItem(item);
//         client.putItem(itemRequest);
//         item.clear();
		//ProfileCredentialsProvider client = new ProfileCredentialsProvider();
	    //ProfileCredentialsProvider provider = new ProfileCredentialsProvider();
//		Map<String, Condition>  = new HashMap<String, Condition>();
//		AWSCredentials credentials = new PropertiesCredentials(
//        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
//		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
//		
//        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//
//        ScanRequest arg0 = new ScanRequest();
//        arg0.withTableName("IOSAppDeviceTokensTable");
//		ScanResult scan = client.scan(arg0);
//		List<Map<String,AttributeValue>> items = scan.getItems();
		
//		System.out.println(items);
       
//        IOSAppDeviceTokenTable t = new IOSAppDeviceTokenTable();
//        t.setUuid("99899909111");
//        t.setDeviceName("ios");
//        mapper.save(t);
       
        
	}
	
	public void saveRightAutoCalulatedRating(RightAutoCalulatedRatingBean rightAutoCalulatedRatingBean) throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);
		 mapper.save(rightAutoCalulatedRatingBean);
	}
	
	private int getSumOfSelection(RightAutoBean rightAutoBean) {
		int sum = 0;
		if(rightAutoBean.getCpm().equals("Y")) {
			sum++;
		}
		if(rightAutoBean.getChargedExtra().equals("N")) {
			sum++;
		}
		if(rightAutoBean.getRecd().equals("Y")) {
			sum++;
		}
		return sum;
	}
	
	
	
	private RightAutoCalulatedRatingBean calculateRating(final List<RightAutoBean> autoDetails) {
		RightAutoCalulatedRatingBean retVal = new RightAutoCalulatedRatingBean();
		float sum = 0;
		int totalCount=0;
		for(RightAutoBean currVal : autoDetails) {
			sum = sum + getSumOfSelection(currVal);
			totalCount++;
		}
		float avg = (sum/totalCount);
		retVal.setAverageRating(""+avg);
		return retVal;
	}

	public static List<RightAutoBean> getAutoDetails(String autoNumber) throws IOException {
		
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		 ScanRequest arg0 = new ScanRequest();
	        arg0.withTableName("RightAutoDetailsTable");
			ScanResult scan = AmazonDynamoDBClient.scan(arg0);
			List<Map<String,AttributeValue>> items = scan.getItems();
			List<RightAutoBean> beanList = new ArrayList<RightAutoBean>();
			for(Map map : items) {
				RightAutoBean bean = new RightAutoBean();
				AttributeValue object = (AttributeValue) map.get("autoNumber");
				if(autoNumber.equalsIgnoreCase(object.getS())) {
					AttributeValue id = (AttributeValue) map.get("id");
					AttributeValue chargedExtra = (AttributeValue) map.get("chargedExtra");
					AttributeValue cpm = (AttributeValue) map.get("cpm");
					AttributeValue recd = (AttributeValue) map.get("recd");
					AttributeValue createdDate = (AttributeValue) map.get("createdDate");
					bean.setId(id.getS());
					bean.setAutoNumber(autoNumber);
					bean.setChargedExtra(chargedExtra.getS());
					bean.setCpm(cpm.getS());
					bean.setRecd(recd.getS());
					bean.setCreatedDate(createdDate.getS());
					beanList.add(bean);
				}
			}
			return beanList;
	}
	
	public RightAutoCalulatedRatingBean getRightAutoCalulatedRating(String autoNumber) throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		Condition hashKeyCondition = new Condition()
	    .withComparisonOperator(ComparisonOperator.EQ)
	    .withAttributeValueList(new AttributeValue().withS(autoNumber));

	Map<String, Condition> keyConditions = new HashMap<String, Condition>();
	keyConditions.put("autoNumber", hashKeyCondition);

	QueryRequest queryRequest = new QueryRequest()
	    .withTableName("RightAutoCalulatedRating")
	    .withKeyConditions(keyConditions);

	QueryResult result = AmazonDynamoDBClient.query(queryRequest);
	RightAutoCalulatedRatingBean bean = new RightAutoCalulatedRatingBean();
		for (Map<String, AttributeValue> item : result.getItems()) {
			AttributeValue object = (AttributeValue) item.get("autoNumber");
			bean.setAutoNumber(object.getS());
			
			AttributeValue averageRating = (AttributeValue) item.get("averageRating");
			bean.setAverageRating(averageRating.getS());
			
			AttributeValue lastRating = (AttributeValue) item.get("lastRating");
			bean.setLastRating(lastRating.getS());
			
			AttributeValue lastRatingDate = (AttributeValue) item.get("lastRatingDate");
			bean.setLastRatingDate(lastRatingDate.getS());
			
			AttributeValue createdDate = (AttributeValue) item.get("createdDate");
			bean.setCreatedDate(createdDate.getS());
			break;
		}
    return bean;
	}
	
	
	
	public List<String> list() throws IOException {
		
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		
        ScanRequest arg0 = new ScanRequest();
        arg0.withTableName("IOSAppDeviceTokensTable");
		ScanResult scan = AmazonDynamoDBClient.scan(arg0);
		List<Map<String,AttributeValue>> items = scan.getItems();
		List<String> retVal = new ArrayList<String>();
		for(Map map : items) {
			AttributeValue object = (AttributeValue) map.get("uuid");
			System.out.println(object.getS());
			retVal.add(object.getS());
		}
		return retVal;
	}
	
	
	
    @DynamoDBTable(tableName="IOSAppDeviceTokensTable")
    public static class  IOSAppDeviceTokenTable
	{
    	private String uuid;
        private String deviceName;
        
        @DynamoDBHashKey(attributeName="uuid")
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		
		@DynamoDBRangeKey(attributeName="deviceName")
		public String getDeviceName() {
			return deviceName;
		}
		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}
	}

	private void createClient() throws Exception {
		
		System.out.println(AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
        AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
//        client = new AmazonDynamoDBClient(credentials);
    }
	
	public MySchoolBean getMySchoolModelDesc(String myschoolID , String modelID) throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);   
        MySchoolBean load = mapper.load(MySchoolBean.class, myschoolID, modelID);
		return load;
	}

	public LoginBean login(final String loginID , final String password) throws IOException {
		// TODO Auto-generated method stub
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
        DynamoDBMapper mapper = new DynamoDBMapper(AmazonDynamoDBClient);   
		LoginBean load = mapper.load(LoginBean.class, loginID, password);
		return load;
	}

	public List<CityBean> getAllCity() throws IOException {
		// TODO Auto-generated method stub
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		ScanRequest arg0 = new ScanRequest();
        arg0.withTableName("City");
		ScanResult scan = AmazonDynamoDBClient.scan(arg0);
		List<Map<String,AttributeValue>> items = scan.getItems();
		List<CityBean> retVal = new ArrayList<CityBean>();
		for(Map map : items) {
			CityBean b = new CityBean();
			AttributeValue object = (AttributeValue) map.get("cityId");
			b.setCityID(object.getS());
			AttributeValue cityName = (AttributeValue) map.get("cityName");
			b.setCityName(cityName.getS());
			retVal.add(b);
		}
		return retVal;
	}

	public List<CitySchoolBean> getSchools(String cityID) throws IOException {
		List<CitySchoolBean> retVal = new ArrayList<CitySchoolBean>();
		// TODO Auto-generated method stub
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		Condition hashKeyCondition = new Condition()
	    .withComparisonOperator(ComparisonOperator.EQ)
	    .withAttributeValueList(new AttributeValue().withS(cityID));

	Map<String, Condition> keyConditions = new HashMap<String, Condition>();
	keyConditions.put("cityID", hashKeyCondition);

	QueryRequest queryRequest = new QueryRequest()
	    .withTableName("CitySchool")
	    .withKeyConditions(keyConditions);

	QueryResult result = AmazonDynamoDBClient.query(queryRequest);
		for (Map<String, AttributeValue> item : result.getItems()) {
			CitySchoolBean b = new CitySchoolBean();
			AttributeValue object = (AttributeValue) item.get("cityID");
			b.setCityID(object.getS());
			
			AttributeValue averageRating = (AttributeValue) item.get("schoolID");
			b.setSchoolID(averageRating.getS());
			
			AttributeValue lastRating = (AttributeValue) item.get("schoolName");
			b.setSchoolName(lastRating.getS());
			retVal.add(b);
		}
		return retVal;
	}

	public List<WeeklyTimeTableBean> getWeeklyTT(String schoolID, String className,
			String section) throws IOException {
		List<WeeklyTimeTableBean> retVal = new ArrayList<WeeklyTimeTableBean>();
		// TODO Auto-generated method stub
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		
		return null;
	}

	public List<DayTimeTable> getDayTT(String schoolID, String className,
			String section) throws IOException {
		List<DayTimeTable> retVal = new ArrayList<DayTimeTable>();
		// TODO Auto-generated method stub
		AWSCredentials credentials = new PropertiesCredentials(
        		AmazonDataService.class.getResourceAsStream("AwsCredentials.properties"));
		AmazonDynamoDBClient AmazonDynamoDBClient = new AmazonDynamoDBClient(credentials);
		
		return null;
	}
}

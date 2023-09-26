package schemepost;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SchemeDetails {
	
     @Test
	public void schemePostDetails() {
    	 
    	 RestAssured.baseURI = "http://hqlinuxtest5:8181/RayMedi_HQ/rest/";
 		
 		
    	 //creation of Request Object using RequestSpecification class 
    		//given is a static method of RestAssured class which returns RequestSpecification object 
    		RequestSpecification reqspec = RestAssured.given();
    		
    		//Creation of JSON Payloader which has to be passed along with the POST Request 
    		JSONObject js = new JSONObject();
    		js.put("status" , "A");
    		js.put("schemeName", "totalenergies");
    		js.put("fromDate", "29/03/2023");
    		js.put("toDate", "30/03/2023");
    		
    		
    		reqspec.header("Content-Type" ,"application/json");
    	    reqspec.sessionId("JSESSIONID","265700E6A40FE21B8AFEC0F4A1132E10.jvm1" );
    	
    		reqspec.body(js.toJSONString());
    		
    		Response response = reqspec.request(Method.POST , "scheme-master/save-scheme");
    				
    		String responsebody = response.getBody().asString();
    		System.out.println("Response Body is :" +responsebody);
    		
    		 
    		//status Code validation 
    		int statuscoderesponse = response.getStatusCode();
    		System.out.println("Status Code is :" + statuscoderesponse);
    		Assert.assertEquals(200, statuscoderesponse);
    		
    		String successcode =response.jsonPath().get("status");
    		Assert.assertEquals("success", successcode);
    	}
    	
    				
    				
    	
	}
		
	



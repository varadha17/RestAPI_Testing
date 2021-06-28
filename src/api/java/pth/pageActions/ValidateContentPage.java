package pth.pageActions;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.parsing.Parser;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;
import pth.dbconnect.dbConnect;
import pth.pojos.parentObject;

public class ValidateContentPage {
	
	public RequestSpecification spec;
	
	public dbConnect db = new dbConnect();
	
	public void setURI() {
		spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://api.rest.pthapp.co.in").build();
	}
	
	public void getAPICalls() { 
		parentObject ps = given().spec(spec).expect().defaultParser(Parser.JSON).when().get("/api/get_posts/").as(parentObject.class,getMapper());
		int count = ps.getPosts().size();
		
		String value = ps.getPosts().get(0).getSlug();
		System.out.println(value);
		assertEquals(value,db.connectDB());
		assertEquals(3,count);
		System.out.println("passed");
		
//		String value  = ps.getPosts().get(0).getAuthor().getName();
//		System.out.println(value);
	} 
	
	public static Jackson2Mapper getMapper() {
		return new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
			public ObjectMapper create(java.lang.reflect.Type cls, String charset) {
				ObjectMapper om  = new ObjectMapper().findAndRegisterModules();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				return om;
			}
			
		});
	}

}

package RESTASSURED1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DynamicJson {

	
	@Test
	
	public static void addBook() 
	{
		RestAssured.baseURI = "http://216.102.245.166";
	}
}

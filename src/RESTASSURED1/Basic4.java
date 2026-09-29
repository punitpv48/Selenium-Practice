package RESTASSURED1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import Files.ReUsableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basic4 {

	public static void main(String[] args) {
		
//Add Place -> Update Place with new address-> Get Place to validate if the New Address is present in the response
// in this example we are extracting the values from the response body 		
		RestAssured.baseURI="https://rahulshettyacademy.com";
String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString()
		;
System.out.println(response);

JsonPath js = new JsonPath(response);         //There is a class called JsonPath, which take string as an input and convert that as Json. I will pass the Json
String placeId = js.getString("place_id");
System.out.println(placeId);


//Update Place...............................................................................
String newAddress = "Summer Walk, Africa, Globe";

given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body("{\r\n"
		+ "\"place_id\":\""+placeId+"\",\r\n"
		+ "\"address\":\""+newAddress+"\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}\r\n"
		+ "")
.when().put("maps/api/place/update/json")
.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//Get Place...............................................................................
String getPlaceResponse =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
.when().get("/maps/api/place/get/json")
.then().assertThat().log().all().statusCode(200).extract().response().asString();

System.out.println(getPlaceResponse);

JsonPath js1= ReUsableMethods.rawToJson(getPlaceResponse);
String actualAddress = js1.getString("address");
System.out.println(actualAddress);

Assert.assertEquals(actualAddress, newAddress);    //this method comes from testNg jar file



	}

}

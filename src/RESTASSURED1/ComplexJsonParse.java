package RESTASSURED1;

import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
	
		JsonPath js=new JsonPath(payload.CoursePrice());
		
		//Print number of course returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print title of the first course
		String TitleFirstCourse = js.get("courses[0].title");
		System.out.println(TitleFirstCourse);
		
		//Print all courses titles and their respective Prices
		for (int i=0; i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
		}
		
		//Print number of copies sold by RPA
		System.out.println("Print number of copies sold by RPA");
		for (int i=0; i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			if (courseTitles.equalsIgnoreCase("RPA"))
			{
				System.out.println(js.get("courses["+i+"].copies").toString());
				System.out.println(courseTitles);
				break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

package Inn.SerializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.testng.annotations.Test;
import Pojo.Data;
import Pojo.PojoClass;;

public class Example1 {

	@Test
	public void main() throws IOException {
		// https://reqres.in/api/users?page=2
		RestAssured.baseURI = "https://reqres.in";
		PojoClass response = given().expect().defaultParser(Parser.JSON).when().get("/api/users?page=2")
				.as(PojoClass.class);

		Assert.assertEquals("2", response.getPage());
		Assert.assertEquals("6", response.getPer_page());
		Assert.assertEquals("2", response.getTotal_pages());
		Assert.assertEquals("12", response.getTotal());

		int dataSize = response.getData().size();
		for (int i = 0; i < dataSize; i++) {
			System.out.println("Actual First Name: " + response.getData().get(i).getFirst_name());
		}
	}
}

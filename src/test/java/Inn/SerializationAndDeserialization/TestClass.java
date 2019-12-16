package Inn.SerializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import Pojo.Data;
import Pojo.PojoClass;;

public class TestClass {

	@Test
	 public void TC1() throws IOException {
		// https://reqres.in/api/users?page=2
		String[] firstNames= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		List<String> expectedFirstNames = Arrays.asList(firstNames);
		RestAssured.baseURI = "https://reqres.in";
		PojoClass response = given().expect().defaultParser(Parser.JSON).when().get("/api/users?page=2")
				.as(PojoClass.class);

		Assert.assertEquals(2, response.getPage());
		Assert.assertEquals(6, response.getPer_page());
		Assert.assertEquals(2, response.getTotal_pages());
		Assert.assertEquals(12, response.getTotal());

		ArrayList actualFirstNames= new ArrayList();
		int dataSize = response.getData().size();
		for (int i = 0; i < dataSize; i++) {
			actualFirstNames.add(response.getData().get(i).getFirst_name());
		}
		Assert.assertEquals(expectedFirstNames, actualFirstNames);
	}
}

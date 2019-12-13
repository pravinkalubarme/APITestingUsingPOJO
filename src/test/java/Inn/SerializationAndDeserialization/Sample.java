package Inn.SerializationAndDeserialization;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.annotations.Test;
import Pojo.Data;
import Pojo.PojoClass;;

public class Sample {

	@Test
	public void main() {
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
			System.out.println(response.getData().get(i).getFirst_name());
		}
	}
}

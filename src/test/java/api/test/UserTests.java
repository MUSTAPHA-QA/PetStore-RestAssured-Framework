package api.test;


import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(4, 16));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
			
		logger = org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void createUser() {
		
		logger.info("************************ Create User ************************ ");
		
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userModelPostJsonSchema.json"));
		
		logger.info("************************ User is created ************************ ");
	}
	
	@Test(priority = 2)
	public void getUserByUserName() {
		
		logger.info("************************ Getting User Info ************************ ");
		
		Response response = UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("************************ User info is displayed ************************ ");
	}
	
	@Test(priority = 3)
	public void updateUserByUserName() {
		
		logger.info("************************ Update User ************************ ");
		
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("************************ User is updated ************************ ");
		
		// Checking the data after update
		Response responseAfterUpdate = UserEndpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUserByUserName() {
		
		logger.info("************************ Delete User ************************ ");
		
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("************************ User is deleted ************************ ");	
	}	
}

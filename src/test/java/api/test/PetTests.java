package api.test;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.PetEndpoints;
import api.payload.Pet;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PetTests {
	
	Faker faker;
	Pet petPayload;
	public Logger logger;
	
	@BeforeClass	
	public void setup() {
		
		faker = new Faker();
		petPayload = new Pet();
		logger = LogManager.getLogger(this.getClass());
		
		petPayload.setId(faker.number().randomDigitNotZero());
		petPayload.setName(faker.name().name());
		petPayload.setStatus(faker.options().option("available", "unavailable"));
		
		// Setting values for Categorie class variables:
		Pet.Categorie categorie = new Pet.Categorie();
		categorie.setId(faker.number().randomDigitNotZero());
		categorie.setName(faker.animal().name());
		petPayload.setCategorie(categorie);
		
		// Setting values for photoUrls List:		
		List<String> photoUrls = new ArrayList<>();
		int numberOfUrls = faker.number().numberBetween(1, 4);
		for(int i = 1; i<numberOfUrls ; i++) {
			photoUrls.add(faker.internet().url());
		}
		petPayload.setPhotoUrls(photoUrls);
			
		// Setting values for Tag class variables:
		List<Pet.Tag> tags = new ArrayList<>();
		int numberOfTags = faker.number().numberBetween(1, 4);
		for(int i = 1; i<numberOfTags; i++) {
			Pet.Tag tag = new Pet.Tag();
			tag.setId(faker.number().randomDigitNotZero());
			tag.setName(faker.name().name());
			tags.add(tag);			
		}
		petPayload.setTags(tags);		
	}
	
	@Test(priority = 1)
	public void createPet() {
		
		logger.info("*************** Create User ***************");
		
		Response response = PetEndpoints.createPet(petPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("petModelPostJsonSchema.json"));
		
		logger.info("*************** User created ***************");

	}
	
	@Test(priority = 2)
	public void getPetById() {
		
		logger.info("*************** Get User ***************");
		
		Response response = PetEndpoints.getPet(this.petPayload.getId());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("*************** User info displayed ***************");

	}
	
	@Test(priority = 3)
	public void updatePet() {
		
		logger.info("*************** Update User ***************");
		
		// Setting new data
		petPayload.setName(faker.name().name());
		petPayload.setStatus(faker.options().option("available", "unavailable"));
		
		Response response = PetEndpoints.updatePet(petPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("*************** User updated ***************");
		
		// Checking the data after update
		Response responseAfterUpdate = PetEndpoints.getPet(this.petPayload.getId());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deletePetById() {
		
		logger.info("*************** Delete User ***************");
		
		Response response = PetEndpoints.deletePet(this.petPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		
		logger.info("*************** User deleted ***************");
	}
}

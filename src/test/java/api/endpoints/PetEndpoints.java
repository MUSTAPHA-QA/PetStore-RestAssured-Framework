package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import api.payload.Pet;
import io.restassured.http.ContentType;

public class PetEndpoints {
	
	public static Response createPet(Pet payload) {
		
		Response response =
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.pet_post_url);
		
		return response;		
	}
	
	public static Response getPet(int petId) {
		
		Response response = 
		
		given()
			.accept(ContentType.JSON)
			.pathParam("petId", petId)
		
		.when()
			.get(Routes.pet_get_url);
		
		return response;		
	}
	
	public static Response updatePet(Pet payload) {
		
		Response response =
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.pet_put_url);
		
		return response;		
	}

	public static Response deletePet(int petId) {
		
		Response response = 
				
		given()
			.accept(ContentType.JSON)
			.pathParam("petId", petId)
			
		.when()
			.delete(Routes.pet_delete_url);
		
		return response;		
	}	
	
}

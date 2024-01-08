package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {
	
	public static Response createOrder(Order payload) {
		
		Response response =
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.body(payload)
		
		
		.when()
			.post(Routes.store_post_url);
		
		return response;
	}
	
	public static Response getOrder(int orderId) {
		
		Response response = 
				
		given()
			.accept(ContentType.JSON)
			.pathParam("orderId", orderId)
			
		
		.when()
			.get(Routes.store_get_url);
		
		return response;			
	}
	
	public static Response deleteOrder(int orderId) {
		
		Response response = 
				
		given()
			.accept(ContentType.JSON)
			.pathParam("orderId", orderId)
		
		.when()
			.delete(Routes.store_delete_url);
		
		return response;	
	}
}

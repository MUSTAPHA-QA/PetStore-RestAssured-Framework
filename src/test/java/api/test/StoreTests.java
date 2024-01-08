package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoints;
import api.payload.Order;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class StoreTests {
	
	Faker faker;
	Order orderPayload;
	Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		orderPayload = new Order();
		logger = LogManager.getLogger(this.getClass());
		
		orderPayload.setId(faker.number().numberBetween(1, 10));
		orderPayload.setPetId(faker.number().numberBetween(1, 10));
		orderPayload.setQuantity(faker.number().numberBetween(1, 4));
		orderPayload.setShipDate("2018-11-05T13:49:18.760+0000");
		orderPayload.setStatus("placed");
		orderPayload.setComplete(true);		
	}
	
	@Test(priority = 1)	
	public void createOrder() {
		
		logger.info("*************** Create Order ***************");

		
		Response response = StoreEndpoints.createOrder(orderPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		Assert.assertEquals(response.jsonPath().getString("status"), "placed");
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeModelPostJsonSchema.json"));
		
		logger.info("*************** Order Created ***************");

	}
	
	@Test(priority = 2)
	public void getOrder() {
		
		logger.info("*************** Get Order ***************");

		
		Response response = StoreEndpoints.getOrder(this.orderPayload.getId());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		
		logger.info("*************** Order Displayed ***************");

	}
	
	@Test(priority = 3)
	public void delelteOrder() {
		
		logger.info("*************** Delete Order ***************");

		
		Response response = StoreEndpoints.deleteOrder(this.orderPayload.getId());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.header("Content-Type"), "application/json");
		
		logger.info("*************** Order Deleted ***************");

	}

}

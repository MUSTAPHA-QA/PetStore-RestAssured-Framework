package api.endpoints;

public class Routes {
	
	// PetStore base URI
	public static String base_uri = "https://petstore.swagger.io/v2";
	
	// Pet model URLs	
	public static String pet_post_url = base_uri + "/pet";
	public static String pet_get_url = base_uri + "/pet/{petId}";
	public static String pet_put_url = base_uri + "/pet";
	public static String pet_delete_url = base_uri + "/pet/{petId}";
	
	// Store model URLs
	public static String store_post_url = base_uri + "/store/order";
	public static String store_get_url = base_uri + "/store/order/{orderId}";
	public static String store_delete_url = base_uri + "/store/order/{orderId}";
	
	// User model URLs
	public static String user_post_url = base_uri + "/user";
	public static String user_get_url = base_uri + "/user/{username}";
	public static String user_update_url = base_uri + "/user/{username}";
	public static String user_delete_url = base_uri + "/user/{username}";
}

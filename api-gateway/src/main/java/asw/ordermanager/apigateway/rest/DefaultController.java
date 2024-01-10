package asw.ordermanager.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*; 

@RestController
public class DefaultController {

	@GetMapping("/")
	public Collection<String> index() {
		return Arrays.asList(
			"http://localhost:8080/order-service/swagger-ui/index.html",
			"http://localhost:8080/product-service/swagger-ui/index.html",
			"http://localhost:8080/order-validation-service/swagger-ui/index.html",
			"http://localhost:8080/actuator" 
		); 
	}
	
}

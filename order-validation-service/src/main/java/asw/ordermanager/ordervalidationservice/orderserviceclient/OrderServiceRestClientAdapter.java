package asw.ordermanager.ordervalidationservice.orderserviceclient;

import asw.ordermanager.ordervalidationservice.domain.*; 

import asw.ordermanager.orderservice.api.rest.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@Service("order-service-client-rest")
public class OrderServiceRestClientAdapter implements OrderServiceClientPort {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;	

    private final Logger logger = Logger.getLogger(this.getClass().toString());
	
	public Order getOrder(Long id) {
		GetOrderResponse or = null; 
        Mono<GetOrderResponse> response = loadBalancedWebClient
                .get()
				.uri("http://order-service/orders/{id}", id)
                .retrieve()
                .bodyToMono(GetOrderResponse.class);
        try {
            or = response.block();
        } catch (WebClientException e) {
			logger.info("GETORDER " + id + ": " + e.getMessage());
        }
		return toOrder(or); 
	}

	private Order toOrder(GetOrderResponse or) {
		if (or == null) {
			return null; 
		}
		return new Order(
			or.getId(),
			or.getCustomer(),
			toOrderItems(or.getOrderItems())
		);
	}

	/* Converte un OrderItemElement in un OrderItem. */ 
	private OrderItem toOrderItem(OrderItemElement item) {
		return new OrderItem(
			item.getProduct(), 
			item.getQuantity()); 
	}

	/* Converte una collezione di OrderItemElement in una collezione di OrderItem. */ 
	private List<OrderItem> toOrderItems(List<OrderItemElement> items) {
        return items
            .stream()
            .map(this::toOrderItem)
            .collect(Collectors.toList());
	}

}

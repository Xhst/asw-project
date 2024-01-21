package asw.ordermanager.orderservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import asw.ordermanager.orderservice.api.event.OrderItemElement;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMessagePublisherPort orderMessagePublisher;

 	public Order createOrder(String customer, String address, List<OrderItem> orderItems, double total) {
		Order order = new Order(customer, address, orderItems, total);

		order = orderRepository.save(order);

		DomainEvent event = new OrderCreatedEvent(
				order.getId(),
				order.getCustomer(),
				order.getAddress(),
				order.getOrderItems().stream().map(x -> new OrderItemElement(x.getProduct(), x.getQuantity())).toList(),
				order.getTotal());

		orderMessagePublisher.publish(event);

		return order;
	}

 	public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
	}

	public Collection<Order> getOrders() {
        return orderRepository.findAll();
	}

	public Collection<Order> getOrdersByCustomer(String customer) {
        return orderRepository.findByCustomer(customer);
	}

	public Collection<Order> getOrdersByProduct(String product) {
        return orderRepository.findByOrderItems_Product(product);
	}
	
}

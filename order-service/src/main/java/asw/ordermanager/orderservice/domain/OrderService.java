package asw.ordermanager.orderservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

 	public Order createOrder(String customer, String address, List<OrderItem> orderItems, double total) {
		Order order = new Order(customer, address, orderItems, total);

		order = orderRepository.save(order);

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

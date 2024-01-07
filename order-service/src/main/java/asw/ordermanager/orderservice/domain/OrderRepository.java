package asw.ordermanager.orderservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface OrderRepository extends CrudRepository<Order, Long> {

	Collection<Order> findAll();

	Collection<Order> findByCustomer(String customer);

	Collection<Order> findByOrderItems_Product(String product);

}


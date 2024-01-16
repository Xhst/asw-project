package asw.ordermanager.ordervalidationservice.orderserviceclient;

import asw.ordermanager.ordervalidationservice.domain.Order;
import asw.ordermanager.ordervalidationservice.domain.OrderRepository;
import asw.ordermanager.ordervalidationservice.domain.OrderServiceClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("order-service-client-jpa")
public class OrderServiceJpaClientAdapter implements OrderServiceClientPort {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}

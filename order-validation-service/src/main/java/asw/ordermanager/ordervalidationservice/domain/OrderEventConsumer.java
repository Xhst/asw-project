package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class OrderEventConsumer {
    private final Logger logger = Logger.getLogger(OrderEventConsumer.class.toString());

    @Autowired
    private OrderRepository orderRepository;

    public void onEvent(DomainEvent event) {
        if (event instanceof OrderCreatedEvent e) {
            onOrderCreated(e);
            return;
        }

        logger.info("UNKNOWN EVENT: " + event);
    }

    private void onOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(
                event.getId(),
                event.getCustomer(),
                event.getOrderItems().stream().map(x -> new OrderItem(x.getProduct(), x.getQuantity())).toList()
        );

        orderRepository.save(order);

        logger.info("CREATED ORDER: " + order);
    }

}
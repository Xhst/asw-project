package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import asw.ordermanager.orderservice.api.event.OrderItemElement;
import org.springframework.stereotype.Service;

import java.util.logging.*;

@Service
public class OrderEventsConsumer {
    private final Logger logger = Logger.getLogger(OrderEventsConsumer.class.toString());

    public void onEvent(DomainEvent event) {
        if (event instanceof OrderCreatedEvent evt) {
            onRestaurantCreated(evt);
        } else {
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

    private void onRestaurantCreated(OrderCreatedEvent event) {
        Order order = new Order(
                event.getId(),
                event.getCustomer(),
                event.getOrderItems().stream().map(x -> new OrderItem(x.getProduct(), x.getQuantity())).toList(),
                event.getTotal()
        );

        //metti l'ordine nel db

        logger.info("CREATED ORDER: " + order);
    }

}
package asw.ordermanager.ordervalidationservice.eventlistener.kafka;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.ordervalidationservice.domain.OrderEventConsumer;
import asw.ordermanager.ordervalidationservice.domain.ProductEventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaEventListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private OrderEventConsumer orderEventConsumer;

    @Autowired
    private ProductEventConsumer productEventConsumer;

    @KafkaListener(topics = "${asw.kafka.channel.order}", groupId = "${asw.kafka.order.group-id}")
    public void listenOrderTopic(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("ORDER EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        orderEventConsumer.onEvent(event);
    }

    @KafkaListener(topics = "${asw.kafka.channel.product}", groupId = "${asw.kafka.product.group-id}")
    public void listenProductTopic(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("PRODUCT EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        productEventConsumer.onEvent(event);
    }

}
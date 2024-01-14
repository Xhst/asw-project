package asw.ordermanager.ordervalidationservice.eventlistener.kafka;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.ordervalidationservice.domain.OrderEventsConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderEventsKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private OrderEventsConsumer orderEventConsumer;

    @KafkaListener(topics = "${asw.kafka.channel.order}")
    public void listen(ConsumerRecord<String, DomainEvent> record) throws Exception {
        logger.info("ORDER EVENT LISTENER: " + record.toString());
        DomainEvent event = record.value();
        orderEventConsumer.onEvent(event);
    }

}
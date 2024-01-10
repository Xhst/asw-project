package asw.ordermanager.orderservice.messagepublisher.kafka;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.orderservice.domain.OrderMessagePublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaMessagePublisherAdapter implements OrderMessagePublisherPort {

    @Value("${asw.kafka.channel.order}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Override
    public void publish(DomainEvent event) {
        template.send(channel, event);
    }
}

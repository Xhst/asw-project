package asw.ordermanager.productservice.messagepublisher.kafka;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.productservice.domain.ProductMessagePublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductKafkaMessagePublisherAdapter implements ProductMessagePublisherPort {

    @Value("${asw.kafka.channel.product}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, DomainEvent> template;

    @Override
    public void publish(DomainEvent event) {
        template.send(channel, event);
    }
}

package asw.ordermanager.productservice.messagepublisher.kafka;

import asw.ordermanager.productservice.domain.ProductMessagePublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedKafkaMessagePublisherAdapter implements ProductMessagePublisherPort {

    @Value("${asw.kafka.channel.product-created}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(String message) {
        template.send(channel, message);
    }
}

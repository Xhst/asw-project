package asw.ordermanager.orderservice.messagepublisher.kafka;

import asw.ordermanager.orderservice.domain.OrderMessagePublisherPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaMessagePublisherAdapter implements OrderMessagePublisherPort {

    @Value("${asw.kafka.channel.out}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(String message) {
        template.send(channel, message);
        // template.flush(); è da togliere???
    }
}

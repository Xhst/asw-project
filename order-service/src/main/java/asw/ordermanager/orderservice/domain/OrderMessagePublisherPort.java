package asw.ordermanager.orderservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;

public interface OrderMessagePublisherPort {

    void publish(DomainEvent event);

}

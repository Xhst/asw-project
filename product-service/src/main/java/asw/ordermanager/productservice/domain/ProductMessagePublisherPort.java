package asw.ordermanager.productservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;

public interface ProductMessagePublisherPort {

    void publish(DomainEvent event);
}

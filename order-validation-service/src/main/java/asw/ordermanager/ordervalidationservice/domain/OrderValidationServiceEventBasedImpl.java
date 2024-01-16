package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("order-validation-service-event")
public class OrderValidationServiceEventBasedImpl implements OrderValidationService {

    @Autowired
    @Qualifier("order-service-client-jpa")
    private OrderServiceClientPort orderServiceClient;

    @Autowired
    @Qualifier("product-service-client-jpa")
    private ProductServiceClientPort productServiceClient;

    @Override
    public OrderValidation validateOrder(Long id) {
        return null;
    }
    //to implement
}

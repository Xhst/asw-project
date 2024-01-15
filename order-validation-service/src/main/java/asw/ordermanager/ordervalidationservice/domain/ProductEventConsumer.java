package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.common.api.event.DomainEvent;
import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductStockLevelUpdatedEvent;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProductEventConsumer {
    private final Logger logger = Logger.getLogger(ProductEventConsumer.class.toString());

    public void onEvent(DomainEvent event) {
        if (event instanceof ProductCreatedEvent e) {
            onProductCreated(e);
            return;
        }
        if (event instanceof ProductStockLevelUpdatedEvent e) {
            onProductStockLevelUpdated(e);
            return;
        }

        logger.info("UNKNOWN EVENT: " + event);
    }

    private void onProductCreated(ProductCreatedEvent event) {
        Product product = new Product(
                event.getName(),
                event.getStockLevel(),
                event.getPrice()
        );

        //metti l'ordine nel db

        logger.info("CREATED PRODUCT: " + product);
    }

    private void onProductStockLevelUpdated(ProductStockLevelUpdatedEvent e) {

        //aggiorna il prodotto nel db

        logger.info("UPDATED PRODUCT: " + e.getName() + " " + e.getNewStockLevel());
    }

}
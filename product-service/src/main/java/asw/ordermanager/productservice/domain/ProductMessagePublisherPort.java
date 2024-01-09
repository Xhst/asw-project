package asw.ordermanager.productservice.domain;

public interface ProductMessagePublisherPort {

    void publish(String message);
}

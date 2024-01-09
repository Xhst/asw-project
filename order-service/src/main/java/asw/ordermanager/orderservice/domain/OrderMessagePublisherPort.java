package asw.ordermanager.orderservice.domain;

public interface OrderMessagePublisherPort {

    void publish(String message);

}

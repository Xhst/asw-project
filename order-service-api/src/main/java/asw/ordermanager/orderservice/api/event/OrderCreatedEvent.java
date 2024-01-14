package asw.ordermanager.orderservice.api.event;

import asw.ordermanager.common.api.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent implements DomainEvent {

    private Long id;
    private String customer;
    private String address;
    private List<OrderItemElement> orderItems;
    private double total;
}

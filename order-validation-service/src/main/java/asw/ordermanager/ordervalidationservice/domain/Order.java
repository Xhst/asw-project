package asw.ordermanager.ordervalidationservice.domain;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

/* Ordine. */
@Entity
@Table(name="orders")
@Data 
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Comparable<Order> {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue
	private Long id;
	private String customer;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;

	@Override
	public int compareTo(Order other) {
		return this.id.compareTo(other.id); 
	}
	
}

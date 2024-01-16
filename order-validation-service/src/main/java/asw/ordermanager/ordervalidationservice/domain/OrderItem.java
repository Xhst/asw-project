package asw.ordermanager.ordervalidationservice.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

/* Ordine. */ 
@Data
@Embeddable
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	private String product; 
	private int quantity; 
	
}

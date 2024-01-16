package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByNameIn(List<String> names);
}

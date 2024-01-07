package asw.ordermanager.productservice.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ProductRepository extends CrudRepository<Product, String> {

	Collection<Product> findAll();

	Collection<Product> findByCategory(String category);

	Collection<Product> findByNameIn(List<String> names);

}


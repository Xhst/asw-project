package asw.ordermanager.productservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

 	public Product createProduct(String name, String category, int stockLevel, double price) {
		Product product = new Product(name, category, stockLevel, price);

		product = productRepository.save(product);

		return product;
	}

 	public Product getProduct(String name) {
        return productRepository.findById(name).orElse(null);
	}

	public Collection<Product> getProducts() {
        return productRepository.findAll();
	}

	public Collection<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
	}	

	public Collection<Product> getProductsByNames(List<String> names) {
        return productRepository.findByNameIn(names);
	}	

 	public Product updateProductStockLevel(String name, int stockLevelVariation) {
		Product product = getProduct(name);

		int newStockLevel = Math.max((product.getStockLevel() + stockLevelVariation), 0);
		product.setStockLevel(newStockLevel);

		product = productRepository.save(product);

		return product;
	}

}

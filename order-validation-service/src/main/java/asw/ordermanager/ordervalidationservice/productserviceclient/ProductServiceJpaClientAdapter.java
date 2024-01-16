package asw.ordermanager.ordervalidationservice.productserviceclient;

import asw.ordermanager.ordervalidationservice.domain.Product;
import asw.ordermanager.ordervalidationservice.domain.ProductRepository;
import asw.ordermanager.ordervalidationservice.domain.ProductServiceClientPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("product-service-client-jpa")
public class ProductServiceJpaClientAdapter implements ProductServiceClientPort {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProduct(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByNames(List<String> names) {
        return productRepository.findByNameIn(names);
    }
}

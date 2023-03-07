package personal.project.domain.product;

import java.util.List;

public interface ProductRepository {

    Product addProduct(Product product);

    Product findProduct(Long id);

    List<Product> findAll();

    void update(Long product_Id,Product product);
}

package personal.project.domain.product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryProductRepository implements ProductRepository{

    private static Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private static long sequence=0L;
    @Override
    public void addProduct(Product product) {
        product.setId(++sequence);
        productStore.put(product.getId(), product);
    }

    @Override
    public Product findProduct(Long id) {
        return productStore.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productStore.values());
    }

    @Override
    public void update(Long product_Id, Product updateProduct) {
        Product findProduct = findProduct(product_Id);
        findProduct.setPrice(updateProduct.getPrice());
        findProduct.setQuantity(updateProduct.getQuantity());
        findProduct.setSpecification(updateProduct.getSpecification());
        findProduct.setProductName(updateProduct.getProductName());
    }

    void clear(){
        productStore.clear();
    }
}

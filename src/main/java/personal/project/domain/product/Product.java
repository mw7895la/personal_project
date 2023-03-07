package personal.project.domain.product;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String productName;
    private Integer price;
    private Integer quantity;
    private String specification;

    public Product(){

    }

    public Product(String productName, Integer price, Integer quantity, String specification) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.specification = specification;
    }
}

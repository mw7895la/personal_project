package personal.project.web.product.form;

import lombok.Data;

@Data
public class ProductSaveForm {
    private String productName;

    private Integer price;

    private Integer quantity;

    private String specification;
}

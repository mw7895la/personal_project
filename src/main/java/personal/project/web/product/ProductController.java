package personal.project.web.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personal.project.domain.product.Product;
import personal.project.domain.product.ProductRepository;


import java.util.List;

@Slf4j
@Controller
@RequestMapping("/items")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listItem(Model model) {
        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);

        return "product/items";
    }

    @GetMapping("/{itemId}")
    public String detailItem(@PathVariable("itemId") long productId, Model model) {
        Product findProduct = productRepository.findProduct(productId);

        model.addAttribute("product", findProduct);

        return "product/item";

    }

    @GetMapping("/add")
    public String addFormItem(Model model) {
        model.addAttribute("product", new Product());
        return "product/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("product") Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!StringUtils.hasText(product.getProductName())) {
            bindingResult.rejectValue("productName", "required");
        }
        if (!StringUtils.hasText(product.getSpecification())) {

            bindingResult.rejectValue("specification", "content", null, null);
        }

        if (product.getPrice() == null || product.getPrice() < 1000 || product.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if (product.getQuantity() == null || product.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }


        if (product.getPrice() != null && product.getQuantity() != null) {
            int resultPrice = product.getPrice() * product.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "product/addForm";
        }
        log.info("addProduct = {}", product);
        Product addProduct = productRepository.addProduct(product);
        log.info("addProduct = {}", addProduct);

        //post상태에서 새로고침하면 상품 계속 등록되니까. redirect 사용
        redirectAttributes.addAttribute("id", addProduct.getId());
        redirectAttributes.addAttribute("status", true);


        return "redirect:/items/{id}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        Product findProduct = productRepository.findProduct(itemId);

        model.addAttribute("product", findProduct);


        return "product/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable long itemId, @ModelAttribute Product product, BindingResult bindingResult,RedirectAttributes redirectAttributes) {

        FieldError priceError = bindingResult.getFieldError("price");

        if (!StringUtils.hasText(product.getProductName())) {
            bindingResult.rejectValue("productName", "required", null, null);
        }

        if (!StringUtils.hasText(product.getSpecification())) {
            bindingResult.rejectValue("specification", "content", null, null);
        }

        if (priceError==null &&(product.getPrice() == null || product.getPrice() < 1000 || product.getPrice() > 1000000)) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "product/editForm";
        }

        productRepository.update(itemId, product);

        return "redirect:/items/{itemId}";

    }
}

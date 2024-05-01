package id.ac.ui.cs.advprog.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogueController {

    @GetMapping("/product/catalogue")
    public String productCataloguePage() {
        return "Catalogue";
    }

    @GetMapping("/product/details/")
    public String productDetailPage() { return "Details"; }
}
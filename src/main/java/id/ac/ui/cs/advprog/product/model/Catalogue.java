package id.ac.ui.cs.advprog.product.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Catalogue {
    private List<Product> products;
    private String filterType;

    public Catalogue(List<Product> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public Catalogue(List<Product> products, String filterType) {

    }
}

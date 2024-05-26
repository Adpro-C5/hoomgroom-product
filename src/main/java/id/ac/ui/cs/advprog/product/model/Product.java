package id.ac.ui.cs.advprog.product.model;

import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name = "product")
@Entity
@Getter
@Setter
public class Product {
  @Id
  private UUID id;
  private String productName;
  private ArrayList<String> categories;
  private String description;
  private String imagePath;
  private Double price;
  private Double discountedPrice;
  private Integer sales;

  public Product() {}

  public Product(String productName, ArrayList<String> categories, String description,
    String imagePath, Double price, Double discountedPrice) {
      this.id = UUID.randomUUID();
      this.productName = productName;
      this.categories = categories;
      this.description = description;
      this.imagePath = imagePath;
      this.price = price;
      this.discountedPrice = discountedPrice;
      this.sales = Integer.valueOf(0);
  }
}

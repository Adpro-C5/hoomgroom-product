package id.ac.ui.cs.advprog.product.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

import id.ac.ui.cs.advprog.product.enums.AlphaNumeric;

import java.time.LocalDate;

@Getter
@Setter
public class PromoCode {
  private UUID id;
  private String name;
  private String description;
  private LocalDate expiredDate;
  private Double minimumPurchase;

  public void setName(String name) throws Exception {
    for(char letter : name.toCharArray()) {
      if (!AlphaNumeric.contains(letter)) {
        throw new Exception(
          "Name contain non-alphanumeric characters"
        );
      }
    }
    this.name = name;
  }
}


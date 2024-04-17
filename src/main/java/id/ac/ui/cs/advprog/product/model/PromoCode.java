package id.ac.ui.cs.advprog.product.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.time.LocalDate;

@Getter
@Setter
public class PromoCode {
  UUID id;
  String name;
  String description;
  LocalDate expiredDate;
  Double minimumPurchase;

  public void setName(String name) throws Exception {
    String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for(char letter : name.toCharArray()) {
      if (!alphanumeric.contains(String.valueOf(letter))) {
        throw new Exception(
          "Name contain non-alphanumeric characters"
        );
      }
    }
    this.name = name;
  }
}


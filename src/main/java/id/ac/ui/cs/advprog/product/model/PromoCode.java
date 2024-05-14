package id.ac.ui.cs.advprog.product.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import id.ac.ui.cs.advprog.product.enums.AlphaNumeric;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name = "promo_code")
@Entity
@Getter
@Setter
public class PromoCode {
  @Id
  private UUID id;
  private String name;
  private String description;
  @JsonFormat(pattern = "yyyy-MM-dd")
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


package id.ac.ui.cs.advprog.product.enums;

import lombok.Getter;

@Getter
public enum AlphaNumeric {
  ALPHANUMERIC("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");

  private final String value;
  private AlphaNumeric(String value) {
    this.value = value;
  }

  public static boolean contains(char character) {
    if (AlphaNumeric.ALPHANUMERIC.getValue()
          .contains(String.valueOf(character))) {
      return true;
    } else {
      return false;
    }
  }
}

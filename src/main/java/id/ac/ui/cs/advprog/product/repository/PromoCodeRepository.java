package id.ac.ui.cs.advprog.product.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.product.model.PromoCode;

@Repository
@Qualifier("promoCodeRepository")
public class PromoCodeRepository implements ManageRepository<PromoCode> {
  private List<PromoCode> promoCodeData = new ArrayList<PromoCode>();

  @Override
  public PromoCode save(PromoCode promoCode) {
    int index = 0;
    for (PromoCode savedPromo : promoCodeData) {
      if (savedPromo.getId().equals(promoCode.getId())) {
        promoCodeData.remove(index);
        promoCodeData.add(index, promoCode);
        return promoCode;
      }
      index += 1;
    }
    promoCodeData.add(promoCode);
    return promoCode;    
  }

  @Override
  public PromoCode findById(String id) {
    for (PromoCode savedPromo : promoCodeData) {
      if (id.equals(savedPromo.getId().toString())) {
        return savedPromo;
      }
    }

    return null;
  }

  @Override
  public PromoCode deleteById(String id) {
    int index = 0;
    for (PromoCode savedPromo : promoCodeData) {
      if (id.equals(savedPromo.getId().toString())) {
        promoCodeData.remove(index);
        return savedPromo;
      }

      index += 1;
    }

    return null;
  }

  @Override
  public Iterator<PromoCode> findAll() {
    return promoCodeData.iterator();
  }
}

package id.ac.ui.cs.advprog.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.model.PromoCode;
import id.ac.ui.cs.advprog.product.service.ProductServiceInterface;
import id.ac.ui.cs.advprog.product.service.PromoServiceInterface;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@Controller
public class ManageController {

  @Autowired
  @Qualifier("productService")
  ProductServiceInterface productService;

  @Autowired
  @Qualifier("promoCodeService")
  PromoServiceInterface promoService;

  @GetMapping("/product/list")
  public ResponseEntity<List<Product>> getProductPage() {
    try {
      List<Product> products = productService.findAll().get();
      return ResponseEntity.status(HttpStatus.OK).body(products);      
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
    try {
      Product product = productService.findById(id).get();
      return ResponseEntity.status(HttpStatus.OK).body(product);        
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @PostMapping("/product/add")
  public ResponseEntity<Product> createProductPost(@RequestBody Product product) {
    try {
      Product createdProduct = productService.create(product).get();
      return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
  }

  @PutMapping("/product/edit/{id}")
  public ResponseEntity<Product> editProductPost(@PathVariable("id") String id, @RequestBody Product product) {
    try {
      Product editedProduct = productService.edit(id, product).get();  
      return ResponseEntity.status(HttpStatus.OK).body(editedProduct);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @DeleteMapping("/product/delete/{id}")
  public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id) {
    try {
      Product deletedProduct = productService.delete(id).get();  
      return ResponseEntity.status(HttpStatus.OK).body(deletedProduct);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @GetMapping("/promo_code/list")
  public ResponseEntity<List<PromoCode>> getPromoCodePage(Model model) {
    try {
      List<PromoCode> promos = promoService.findAll().get();
      return ResponseEntity.status(HttpStatus.OK).body(promos);    
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @GetMapping("/promo_code/{id}")
  public ResponseEntity<PromoCode> getPromoById(Model model, @PathVariable("id") String id) {
    try {
      PromoCode promoCode = promoService.findById(id).get();
      return ResponseEntity.status(HttpStatus.OK).body(promoCode);    
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @GetMapping("/promo_code/name/{name}")
  public ResponseEntity<PromoCode> getPromoByName(Model model, @PathVariable("name") String name) {
    try {
      PromoCode promoCode = promoService.findByName(name).get();
      return ResponseEntity.status(HttpStatus.OK).body(promoCode);    
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @PostMapping("/promo_code/add")
  public ResponseEntity<PromoCode> createPromoCodePost(@RequestBody PromoCode promoCode) {
    System.out.println(promoCode);
    try {
      PromoCode createdPromo = promoService.create(promoCode).get();
      return ResponseEntity.status(HttpStatus.CREATED).body(createdPromo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
  }

  @PutMapping("/promo_code/edit/{id}")
  public ResponseEntity<PromoCode> editPromoCodePost(@PathVariable("id") String id, @RequestBody PromoCode promoCode) {
    try {
      PromoCode editedPromo = promoService.edit(id, promoCode).get();
      return ResponseEntity.status(HttpStatus.OK).body(editedPromo);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @DeleteMapping("/promo_code/delete/{id}")
  public ResponseEntity<PromoCode> deletePromoCode(@PathVariable("id") String id) {
    try {
      PromoCode deletedPromo = promoService.delete(id).get();
      return ResponseEntity.status(HttpStatus.OK).body(deletedPromo);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    } 
  }

  @GetMapping("/product/top10")
  public ResponseEntity<List<Product>> GetTopTenPage(Model model) {
    try {
      List<Product> products = productService.findBestTen().get();
      return ResponseEntity.status(HttpStatus.OK).body(products);  
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }

  @GetMapping("/product/worst10")
  public ResponseEntity<List<Product>> GetWorstTenPage(Model model) {
    try {
      List<Product> products = productService.findWorstTen().get();
      return ResponseEntity.status(HttpStatus.OK).body(products);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(null);
    }
  }
}

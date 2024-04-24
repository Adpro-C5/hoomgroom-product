package id.ac.ui.cs.advprog.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.product.model.Product;
import id.ac.ui.cs.advprog.product.model.PromoCode;
import id.ac.ui.cs.advprog.product.service.ManageService;
import id.ac.ui.cs.advprog.product.service.StatsisticService;

@Controller
public class ManageController {

  @Autowired
  @Qualifier("productService")
  ManageService<Product> productService;

  @Autowired
  @Qualifier("promoCodeService")
  ManageService<PromoCode> promoService;

  @Autowired
  StatsisticService statisticService;
  
  @GetMapping("/product/list")
  public String getProductPage(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<Product> getProductById(Model model, @PathVariable("id") String id) {
    Product product = productService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

  @GetMapping("/product/add")
  public String createProductPage(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/product/add")
  public String createProductPost(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/product/edit")
  public String editProductPage(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/product/edit")
  public String editProductPost(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/product/delete")
  public String deleteProduct(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/promo_code/list")
  public String getPromoCodePage(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/promo_code/{id}")
  public ResponseEntity<PromoCode> getPromoById(Model model, @PathVariable("id") String id) {
    PromoCode promoCode = promoService.findById(id);
    return ResponseEntity.status(HttpStatus.OK).body(promoCode);
  }

  @GetMapping("/promo_code/add")
  public String createPromoCodePage(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/promo_code/add")
  public String createPromoCodePost(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/promo_code/edit")
  public String editPromoCodePage(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/promo_code/edit")
  public String editPromoCodePost(Model model) {
    return "PlaceHolder";
  }

  @PostMapping("/promo_code/delete")
  public String deletePromoCode(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/product/top10")
  public String GetTopTenPage(Model model) {
    return "PlaceHolder";
  }

  @GetMapping("/product/worst10")
  public String GetWorstTenPage(Model model) {
    return "PlaceHolder";
  }
}

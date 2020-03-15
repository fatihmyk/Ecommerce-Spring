package com.eteration.site.Controller;

import com.eteration.site.Model.Basket;
import com.eteration.site.Model.Product;
import com.eteration.site.Services.BasketService;
import com.eteration.site.Services.ProductService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/baskets")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductService productService;

    private Long BASKET_ID=46L;

    @PostMapping("/{productId}")
    public ResponseEntity<String> addBasket(@PathVariable("productId") Long id){
        Product product = productService.findById(id).get();
        Basket basket=basketService.findById(BASKET_ID).get();
        basket.addProduct(product);
        product.addBasket(basket);
        basket.setTotalPrice(basket.getTotalPrice()+product.getPrice());

        basketService.save(basket);
        productService.save(product);
        return new ResponseEntity<>(product.getName()+" sepete eklenmiştir",HttpStatus.ACCEPTED);
    }

    @GetMapping
    public MappingJacksonValue getBasket(){
        SimpleBeanPropertyFilter productFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price","desc","imageURL");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("productFilter",productFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(basketService.findById(BASKET_ID).get());
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteBasket(@PathVariable("productId") Long id){
        Product product = productService.findById(id).get();
        Basket basket=basketService.findById(BASKET_ID).get();
        basket.getProducts().remove(product);
        basket.setTotalPrice(basket.getTotalPrice()-product.getPrice());
        product.getBaskets().remove(basket);

        basketService.save(basket);
        productService.save(product);
        return new ResponseEntity<>(product.getName()+" sepetten silinmiştir",HttpStatus.ACCEPTED);
    }
}

package com.eteration.site.Controller;

import com.eteration.site.Model.Comment;
import com.eteration.site.Model.Product;
import com.eteration.site.Repository.CategoryRepository;
import com.eteration.site.Services.CategoryService;
import com.eteration.site.Services.CommentService;
import com.eteration.site.Services.ProductService;

import com.eteration.site.Services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin
public class ProductController {

    private ProductService productService;
    private UserService userService;
    private CategoryService categoryService;
    private CommentService commentService;

    public ProductController(ProductService productService, UserService userService,
                             CategoryService categoryService, CommentService commentService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.commentService = commentService;
    }

    @GetMapping("/products")
    public MappingJacksonValue getAll(@RequestParam Optional<Integer> page){
        SimpleBeanPropertyFilter productFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price","desc","imageURL","user");
        SimpleBeanPropertyFilter userFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","fullName");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("productFilter",productFilter).addFilter("userFilter",userFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(productService.findAll(PageRequest.of(page.orElse(0),8)));
        mappingJacksonValue.setFilters(filterProvider);
       return mappingJacksonValue;
    }

    @GetMapping("/products/{id}")
    public MappingJacksonValue getProdcut(@PathVariable("id") Long id){
        SimpleBeanPropertyFilter productFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price","desc","imageURL","user","comments");
        SimpleBeanPropertyFilter userFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","fullName");
        SimpleBeanPropertyFilter commentFilter= SimpleBeanPropertyFilter.filterOutAllExcept("name","comment");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("productFilter",productFilter).addFilter("userFilter",userFilter).addFilter("commentFilter",commentFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(productService.findById(id).get());
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @GetMapping("/categories/{categoryId}/products")
    public MappingJacksonValue getAllByCategoryId(@PathVariable("categoryId") Long id){
        SimpleBeanPropertyFilter productFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price","desc","imageURL","user");
        SimpleBeanPropertyFilter userFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","fullName");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("productFilter",productFilter).addFilter("userFilter",userFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(productService.findByCategoryId(id));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PostMapping("/products")
    public ResponseEntity<Object> addProduct(@RequestBody Map<String, String> response){
        Product product = new Product();

        product.setPrice(Double.parseDouble(response.get("price")));
        product.setUser(userService.findByEmail("user@gmail.com").get());
        product.setDesc(response.get("desc"));
        product.setImageURL(response.get("imageURL"));
        product.setName(response.get("name"));

        if(response.get("category").equalsIgnoreCase("Telefon")){
            product.addCategory(categoryService.findByName("Telefon"));
        }
        else if(response.get("category").equalsIgnoreCase("Bilgisayar")){
            product.addCategory(categoryService.findByName("Bilgisayar"));
        }
        else if(response.get("category").equalsIgnoreCase("Ders Kitapları")){
            product.addCategory(categoryService.findByName("Ders Kitapları"));
        }

        else if(response.get("category").equalsIgnoreCase("Çizgi Roman")){
            product.addCategory(categoryService.findByName("Çizgi Roman"));
        }

        else if(response.get("category").equalsIgnoreCase("Roman")){
            product.addCategory(categoryService.findByName("Roman"));
        }

        productService.save(product);
        return new ResponseEntity<>("Ürününüz başarıyla eklendi",HttpStatus.CREATED);
    }

    @PostMapping("/products/{id}/comments")
    public ResponseEntity<Object> addComment(@RequestBody Map<String,String> request,@PathVariable("id") Long id){
        Comment comment = new Comment();
        comment.setName(request.get("name"));
        comment.setComment(request.get("comment"));
        comment.setProduct(productService.findById(id).get());
        commentService.save(comment);
        return new ResponseEntity<>("Yorum başarıyla eklendi",HttpStatus.CREATED);
    }

    @GetMapping("/search/{title}")
    public MappingJacksonValue search(@PathVariable("title") Optional<String> name){
        SimpleBeanPropertyFilter productFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","name","price","desc","imageURL","user");
        SimpleBeanPropertyFilter userFilter= SimpleBeanPropertyFilter.filterOutAllExcept("id","fullName");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("productFilter",productFilter).addFilter("userFilter",userFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(productService.findByName(name.orElse("").toLowerCase()));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}





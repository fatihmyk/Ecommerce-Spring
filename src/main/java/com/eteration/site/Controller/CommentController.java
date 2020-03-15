package com.eteration.site.Controller;

import com.eteration.site.Model.Comment;
import com.eteration.site.Model.Product;
import com.eteration.site.Services.CommentService;
import com.eteration.site.Services.ProductService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/comments")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public MappingJacksonValue getCommentById(@PathVariable("id") Long id){

        SimpleBeanPropertyFilter commentFilter= SimpleBeanPropertyFilter.filterOutAllExcept("name","comment");
        FilterProvider filterProvider= new SimpleFilterProvider().addFilter("commentFilter",commentFilter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(commentService.findByProductId(id));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PostMapping("/{id}")
    public void addComment(@PathVariable("id") Long id,@RequestBody Map<String,String> request){
        Product product = productService.findById(id).get();
        Comment comment= new Comment();
        comment.setProduct(product);
        comment.setName(request.get("name"));
        comment.setComment(request.get("comment"));
        product.addComment(comment);
        productService.save(product);
        commentService.save(comment);
    }
}

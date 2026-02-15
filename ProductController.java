package com.lt.controller100;

import com.lt.model100.Product;
import com.lt.service100.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/operators100")
    List<Product> list(){
        return productService.selectAll100();
    }
    @GetMapping("/operators100/{id}")
    Product getOne(@PathVariable("id") int id){
        return productService.selectOne100(id);
    }
    @PostMapping("/operators100")
    int insert(@RequestBody Product product){
        return productService.insert100(product);
    }
    @PutMapping("/operators100")
    int update(@RequestBody Product product){
        return productService.update100(product);
    }
    @DeleteMapping("/operators100/{id}")
    int delete(@PathVariable("id") int id){
        return productService.delete100(id);
    }

}

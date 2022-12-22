package com.example.EcommerceWithSpringDataJpa.controller;

import com.example.EcommerceWithSpringDataJpa.dtos.ProductDto;
import com.example.EcommerceWithSpringDataJpa.entity.Product;
import com.example.EcommerceWithSpringDataJpa.model.Response;
import com.example.EcommerceWithSpringDataJpa.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public Response<List<Product>> getAllProducts(){
        List<Product> products=productService.getProducts();
        System.out.println(products.toString());
        return new Response<>("Ok",200,products);
    }
    @GetMapping("/getByCategory/{id}")
    public Response<List<Product>> getProductsByCategory(@PathVariable Long id){
        List<Product> products;
        try {
           products= productService.getProductsByCategory(id);
        }
        catch (IllegalArgumentException e){
            return new Response("illegal category",400,null);
        }

        return new Response("Ok",200,products);
    }
    @GetMapping("/search")
    public Response<List<Product>> searchProductByName(@QueryParam("name") String name){
        List<Product> products;
        try {
            products = productService.searchByProductName(name);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        System.out.println(products.toString());
        return new Response<>("Ok",200,products);

    }

    @PutMapping("/quantity")
    public Response updateProductQuantity(@RequestBody ProductDto productDto){
        try {
            productService.updateProductQuantity(productDto.getProductId(),productDto.getQuantity());
        }
        catch (NoSuchElementException e){
            return new Response(e.getMessage(),404,null);
        }
        catch (IllegalArgumentException e){
           return new Response(e.getMessage(),400,null);
        }

        return new Response<>("Ok",200,null);
    }
    @GetMapping(path="/{id}")
    public Response<Product> getProductById(@PathVariable Long id){
        Product product;
        try {
            product = productService.getProductById(id);
        }
        catch (NoSuchElementException e){
            return new Response(e.getMessage(),404,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        System.out.println(product.toString());
        return new Response<>("Ok",200,product);

    }
    @PostMapping
    public Response addProduct(@RequestBody Product product){
        try {
            productService.addProduct(product);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        return new Response("Ok",200,null);
    }
    @PutMapping
    public Response updateProduct(@RequestBody Product product){
        try {
            productService.updateProduct(product);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }
        catch (NoSuchElementException e){
            return new Response(e.getMessage(),404,null);
        }
        return new Response("Ok",200,null);
    }
    @DeleteMapping("/{id}")
    public Response deleteProduct(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }

        return new Response("Ok",200,null);
    }
}

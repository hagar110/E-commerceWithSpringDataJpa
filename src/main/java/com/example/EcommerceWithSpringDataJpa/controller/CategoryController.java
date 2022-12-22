package com.example.EcommerceWithSpringDataJpa.controller;


import com.example.EcommerceWithSpringDataJpa.entity.Category;
import com.example.EcommerceWithSpringDataJpa.model.Response;
import com.example.EcommerceWithSpringDataJpa.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        System.out.println(categories.toString());
         return categories;

    }
    @GetMapping(path="/{id}")
    public Response<Category> getCategoryById(@PathVariable Long id){
        Category category;
        try {
            category = categoryService.getCategoryByID(id);
        }
        catch (NoSuchElementException e){
            return new Response(e.getMessage(),404,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        System.out.println(category.toString());
        return new Response<>("Ok",200,category);

    }
    @GetMapping(path="/names")
    public List<String> getCategoriesNames(){
        return categoryService.getCategoriesNames();
    }
    @GetMapping("/search")
    public Response<List<Category>> searchCategoryByName(@QueryParam("name") String name){
        List<Category> categories;
        try {
            categories = categoryService.searchByCategoryName(name);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        System.out.println(categories.toString());
        return new Response<>("Ok",200,categories);

    }
    @GetMapping(path="/name/{name}")
    public Response<Category> getCategoryByName(@PathVariable("name") String name){
        Category category;
        try {
            category = categoryService.getCategoryByName(name);
        }
        catch (NullPointerException e){
            return new Response("Bad request",400,null);
        }
        catch (IllegalArgumentException e){
            return new Response(e.getMessage(),400,null);
        }
        System.out.println(category.toString());
        return new Response<>("Ok",200,category);

    }
    @PostMapping
    public Response addCategory(@RequestBody Category category){
        try {
            categoryService.addCategory(category);
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
    public Response updateCategory(@RequestBody Category category){
        try {
            categoryService.updateCategory(category);
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
    public Response deleteCategory(@PathVariable Long id){
        try {
            categoryService.removeCategoryById(id);
        }
        catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), 400, null);
        }
        catch (NoSuchElementException e){
            return new Response(e.getMessage(),404,null);
        }

        return new Response("Ok",200,null);
    }

}
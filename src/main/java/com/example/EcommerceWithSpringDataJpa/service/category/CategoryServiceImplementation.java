package com.example.EcommerceWithSpringDataJpa.service.category;

import com.example.EcommerceWithSpringDataJpa.entity.Category;
import com.example.EcommerceWithSpringDataJpa.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository repository) {
        this.repository = repository;
    }


    /**
     * @InheritedDoc
     */
    @Override
    public void addCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        category.setName(category.getName().toLowerCase());
        if(repository.getCategoryByName(category.getName())!=null)
           throw new IllegalArgumentException("name already exists");
        repository.save(category);
   }

    /**
     * @InheritedDoc
     */
    @Override
    public void updateCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        if(repository.findById(category.getId())==null) {
            throw new NoSuchElementException("category doesn't exist");
        }
        repository.save(category);

    }

    /**
     * @InheritedDoc
     */
    @Override
    public void removeCategoryById(Long categoryId) {
        if(categoryId <= -1)
            throw new IllegalArgumentException();
        Optional<Category> category=repository.findById(categoryId);
        if(!category.isPresent())
            throw new NoSuchElementException("no category with the given id");
        deleteCategory(category.get());
        //  return repository.removeCategory(category);
    }

    private void deleteCategory(Category category) {
        if(category == null)
            throw new NullPointerException();
        repository.delete(category);
      //  return repository.removeCategory(category);
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Category getCategoryByName(String name) {
        if(name.isBlank())
            throw new IllegalArgumentException();
        if(name == null)
            throw new NullPointerException();

        return repository.getCategoryByName(name.toLowerCase());
    }

    /**
     * @InheritedDoc
     */
    @Override
    public Category getCategoryByID(Long id) {
        if(id <= -1)
            throw new IllegalArgumentException();
        Optional<Category> category=repository.findById(id);
        if(category.isPresent())
            return category.get();
        else{
            throw new NoSuchElementException("no category with the given id");
        }
    }

    /**
     * @InheritedDoc
     */
    @Override
    public List<Category> searchByCategoryName(String categoryName) {
        if(categoryName.isBlank())
            throw new IllegalArgumentException();
        if(categoryName == null)
            throw new NullPointerException();
        String categoryNameToLowerCase=categoryName.toLowerCase();
        String searchKey1="% " + categoryNameToLowerCase + " %";
        String searchKey2=categoryNameToLowerCase+" %";
        String searchKey3="% "+categoryNameToLowerCase;
        return repository.searchByCategoryName(searchKey1,searchKey2,searchKey3,categoryNameToLowerCase);
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getCategoriesNames() {
        return repository.getCategoriesNames();
    }
}

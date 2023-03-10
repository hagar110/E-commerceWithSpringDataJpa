package com.example.EcommerceWithSpringDataJpa.repository.category;

import com.example.EcommerceWithSpringDataJpa.entity.Category;
import com.example.EcommerceWithSpringDataJpa.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * This method is used by admin to add a new category to database.
     * @param category This is the admin object to be added.
     * @return nothing.
     */
    Response addCategory(Category category);

    /**
     * This method is used by admin to update a category's image.
     * @param category category object updated.
     * @return int number of rows affected.
     */
    Response<Boolean> updateCategory(Category category);

    /**
     * This method is used by admin to remove a category from database.
     * @param category This is the category needs to be deleted.
     * @return int number of rows affected.
     */
    Response<Boolean> removeCategory(Category category);

    /**
     * get all categories
     * retrieves all categories from database
     * @return list of categories
     */
    Response<List<Category>> getAllCategories();

    /**
     * get category by its name.
     * @param name the name of the category to get form database.
     * @return the found category.
     */
    Response<Category> getCategoryByName(String name);

    /**
     * get category by its id.
     * @param id the id of the category to get form database.
     * @return the found category.
     */
    Response<Category> getCategoryByID(int id);

    /**
     * This method is used to get the names of all categories available in database.
     * @return List a list with categories available names.
     */
    Response<List<String>> getCategoriesNames();


    /**
     * search by category name
     * takes category name and retrieves all categories matching this name
     * @param categoryName categoryName
     * @return list of matched categories
     */
    Response<List<Category>> searchByCategoryName(String categoryName);
}

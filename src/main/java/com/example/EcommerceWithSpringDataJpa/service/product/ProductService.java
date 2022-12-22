package com.example.EcommerceWithSpringDataJpa.service.product;


import com.example.EcommerceWithSpringDataJpa.entity.Product;

import java.util.List;

/**
 * responsible for Products operations
 */
public interface ProductService {

    /**
     * add product this function used by admin to add product to database
     *
     * @param product product object to be added
     */
    void addProduct(Product product);

    /**
     * update product this function is used by admin to update product from database
     *
     * @param product product object to be updated
     * @Return nothing
     */

    void updateProduct(Product product);


    /**
     * delete product function invoked by admin to delete product from database
     *
     * @param product product object to be deleted
     * @Return nothing
     */
    void deleteProductById(Long productId);


    /**
     * update product quantity
     * function takes id of the product and new quantity and apply this changes to database
     *
     * @param productId productId id of the product to be updated
     * @param quantity  quantity new quantity of the product to be updated
     * @return true if a row is updated in the database
     */

    void updateProductQuantity(Long productId, int quantity);


    /**
     * get products
     * function used to return all products from the database
     *
     * @return products
     */
   List<Product> getProducts();


    /**
     * get products by category
     * retrieves all products related to a category
     *
     * @param categoryId id of category
     * @return list of products
     */
   List<Product> getProductsByCategory(Long categoryId);



    /**
     * get products by id
     * retrieves product with the given id
     *
     * @param productId id of product
     * @return product
     */
    Product getProductById(Long productId);

    /**
     * search by product name
     * takes a product name and retrieve all products matching this name
     *
     * @param productName productName
     * @return list of products
     */

    List<Product> searchByProductName(String productName);


}

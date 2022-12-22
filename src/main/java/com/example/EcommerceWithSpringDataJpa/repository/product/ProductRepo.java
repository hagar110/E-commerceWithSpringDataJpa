package com.example.EcommerceWithSpringDataJpa.repository.product;

import com.example.EcommerceWithSpringDataJpa.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {

   String getByName(String name);
   @Modifying
   @Query("UPDATE Product set availableQuantity = :quantity WHERE id = :productId")
   void updateProductQuantity(@Param("productId")Long productId,@Param("quantity") int quantity);
   @Query("select p from Product as p inner join p.category as c where c.id=:categoryId")
   List<Product> getProductsByCategory(@Param("categoryId")Long categoryId) ;
   @Query("from Product where name like :searchKey or name= :productName")
   List<Product> searchByProductName(@Param("productName")String productName,@Param("searchKey") String searchKey) ;

}

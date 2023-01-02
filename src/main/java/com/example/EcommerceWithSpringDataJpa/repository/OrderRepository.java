package com.example.EcommerceWithSpringDataJpa.repository;

import com.example.EcommerceWithSpringDataJpa.entity.Order;
import com.example.EcommerceWithSpringDataJpa.entity.OrderDetails;
import com.example.EcommerceWithSpringDataJpa.entity.Product;
import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("FROM Order where customer_id=:userId")
    List<Order> findByUserId(@Param("userId") int userId);

    @Modifying
    @Query("update Order o set o.status =:status where o.id=:orderId")
    void updateStatus(@Param("orderId") int orderId ,@Param("status") OrderStatus status);
}


package com.example.EcommerceWithSpringDataJpa.repository;

import com.example.EcommerceWithSpringDataJpa.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

}

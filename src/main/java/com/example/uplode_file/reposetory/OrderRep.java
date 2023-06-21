package com.example.uplode_file.reposetory;

import com.example.uplode_file.entity.OrderTechnicalServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRep extends JpaRepository<OrderTechnicalServiceEntity, Integer> {
}

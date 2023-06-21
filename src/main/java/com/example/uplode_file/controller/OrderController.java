package com.example.uplode_file.controller;

import com.example.uplode_file.entity.OrderTechnicalServiceEntity;
import com.example.uplode_file.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/add/{id}")
    public ResponseEntity<Boolean> add(HttpServletRequest request, @PathVariable Integer id){
        boolean add = service.add(request, id);
        return ResponseEntity.ok(add);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){
        OrderTechnicalServiceEntity order = service.getUserId(id);
        return ResponseEntity.ok(order);
    }

}

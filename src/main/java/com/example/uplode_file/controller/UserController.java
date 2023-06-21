package com.example.uplode_file.controller;

import com.example.uplode_file.dto.UserDto;
import com.example.uplode_file.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.add(userDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(service.getUserId(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}

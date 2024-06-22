package com.example.wigellssushi.controller;

import com.example.wigellssushi.model.Dish;
import com.example.wigellssushi.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/sushis")
    @PreAuthorize("hasRole('USER')")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/sushis/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<Dish> getDish(@PathVariable Long id) {
        return dishService.getDishById(id);
    }

    @PostMapping("/add-dish")
    @PreAuthorize("hasRole('ADMIN')")
    public Dish addDish(@RequestBody Dish dish) {
        return dishService.addDish(dish);
    }

    @DeleteMapping("/deletedish/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDish(@PathVariable Long id) {
        return dishService.deleteDishById(id);
    }
}

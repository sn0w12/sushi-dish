package com.example.wigellssushi.repository;

import com.example.wigellssushi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByName(String name);
}

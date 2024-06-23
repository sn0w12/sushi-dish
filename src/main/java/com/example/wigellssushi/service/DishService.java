package com.example.wigellssushi.service;

import com.example.wigellssushi.VO.CustomerOrder;
import com.example.wigellssushi.model.Dish;
import com.example.wigellssushi.exceptions.ResourceNotFoundException;
import com.example.wigellssushi.repository.DishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private static final Logger logger = LoggerFactory.getLogger(DishService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> getDishById(Long id) { return dishRepository.findById(id); }

    public Dish addDish(Dish dish) {
        Dish savedDish = dishRepository.save(dish);
        logger.info("Dish with id: " + savedDish.getId() + " successfully added.");
        return savedDish;
    }

    public String deleteDishById(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // Remove the dish from associated customer orders
        if (dish.getCustomerOrders() != null) {
            for (CustomerOrder order : dish.getCustomerOrders()) {
                order.getDishes().remove(dish);
            }
        }

        dishRepository.delete(dish);
        logger.info("Dish with id: " + dish.getId() + " successfully deleted.");
        return "Dish " + dish.getId() + " deleted.";
    }
}

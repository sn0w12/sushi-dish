package com.example.wigellssushi.VO;

import com.example.wigellssushi.model.Dish;
import com.example.wigellssushi.util.CurrencyConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"customer"})
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "total_price_sek")
    private double totalPriceSEK;
    @Column(name = "total_price_euro")
    private double totalPriceEuro;

    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    boolean takeaway;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPriceSEK() {
        return totalPriceSEK;
    }

    public void setTotalPriceSEK(double totalPriceSEK) {
        this.totalPriceSEK = totalPriceSEK;
        this.totalPriceEuro = CurrencyConverter.convertSEKToEuro(totalPriceSEK);
    }

    public double getTotalPriceEuro() {
        return totalPriceEuro;
    }

    public void setTotalPriceEuro(double totalPriceEuro) {
        this.totalPriceEuro = totalPriceEuro;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public boolean isTakeaway() {
        return takeaway;
    }

    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }
}

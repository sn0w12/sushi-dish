package com.example.wigellssushi.model;

import com.example.wigellssushi.VO.CustomerOrder;
import com.example.wigellssushi.util.CurrencyConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sushi_dishes")
@JsonIgnoreProperties({"customerOrders"})
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "price_sek")
    private double priceSEK;

    @Column(name = "price_euro")
    private double priceEuro;

    @ManyToMany(mappedBy = "dishes", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<CustomerOrder> customerOrders;

    public Dish() {
    }

    public Dish(String name, Double priceSEK) {
        this.name = name;
        this.priceSEK = priceSEK;
        this.priceEuro = CurrencyConverter.convertSEKToEuro(priceSEK);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceSEK() {
        return priceSEK;
    }

    public void setPriceSEK(double priceSEK) {
        this.priceSEK = priceSEK;
        this.priceEuro = CurrencyConverter.convertSEKToEuro(priceSEK);
    }

    public double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}

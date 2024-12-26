package com.api.foodservice.entity;

import java.time.LocalDateTime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="orderId")
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @Column(name = "order_id")
    private int orderId;
    private LocalDateTime orderDate;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;   
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    private Restaurants restaurant;
  
    @ManyToOne
    @JoinColumn(name = "delivery_driver_id")
    @JsonBackReference
    private DeliveryDrivers deliveryDriver;

    @Column(name = "order_status", length = 50)
    private String orderStatus;
    
    
	public Orders() {}
	public Orders(int orderId, LocalDateTime orderDate, Customer customer, Restaurants restaurant,
			DeliveryDrivers deliveryDriver, String orderStatus) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customer = customer;
		this.restaurant = restaurant;
		this.deliveryDriver = deliveryDriver;
		this.orderStatus = orderStatus;
	}



	public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public DeliveryDrivers getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(DeliveryDrivers deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
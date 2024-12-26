package com.api.foodservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="orderItemId")
@Entity
@Table(name="orderitems")
public class OrderItems 
{
	@Id
    @Column(name = "order_item_id")
    private int orderItemId;
	
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference
    private MenuItems menuItems;

    @Column(name = "quantity")
    private int quantity;

    public OrderItems() {}

	public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public MenuItems getMenuItem() {
        return menuItems;
    }

    public void setMenuItem(MenuItems menuItems) {
        this.menuItems = menuItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

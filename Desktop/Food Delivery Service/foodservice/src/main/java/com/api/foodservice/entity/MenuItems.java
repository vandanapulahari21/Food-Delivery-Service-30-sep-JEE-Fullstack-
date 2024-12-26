package com.api.foodservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="itemId")
@Entity
@Table(name = "menuitems")
public class MenuItems 
{
	    @Id
	    @Column(name = "item_id")
	    private int itemId;
	 
	    @Column(name = "item_name")
	    private String itemName;
	 
	    @Column(name = "item_description")
	    private String itemDescription;
	 
	    @Column(name = "item_price")
	    private double itemPrice;
	    
		@ManyToOne
	    @JoinColumn(name = "restaurant_id")
		@JsonBackReference
	    private Restaurants restaurant;
		
	    @OneToMany(mappedBy="menuItems",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	    @JsonManagedReference
		@JsonIgnore
	    List<OrderItems> orderItems;
	 
	    public MenuItems() {}
	    public int getItemId() {
	        return itemId;
	    }
	 
	    public void setItemId(int itemId) {
	        this.itemId = itemId;
	    }
	 
	    public String getItemName() {
	        return itemName;
	    }
	 
	    public void setItemName(String itemName) {
	        this.itemName = itemName;
	    }
	 
	    public String getItemDescription() {
	        return itemDescription;
	    }
	 
	    public void setItemDescription(String itemDescription) {
	        this.itemDescription = itemDescription;
	    }
	 
	    public double getItemPrice() {
	        return itemPrice;
	    }
	 
	    public void setItemPrice(double itemPrice) {
	        this.itemPrice = itemPrice;
	    }
		public Restaurants getRestaurant() {
			return restaurant;
		}
		public void setRestaurant(Restaurants restaurant) {
			this.restaurant = restaurant;
		}
	    public List<OrderItems> getOrderItems() {
			return orderItems;
		}
		public void setOrderItems(List<OrderItems> orderItems) {
			this.orderItems = orderItems;
		}
}

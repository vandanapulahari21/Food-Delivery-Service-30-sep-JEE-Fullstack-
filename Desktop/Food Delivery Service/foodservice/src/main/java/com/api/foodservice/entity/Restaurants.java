package com.api.foodservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="restaurantId")
//@JsonIdentityReference(alwaysAsId = true)
@Entity
@Table(name = "Restaurants")
public class Restaurants {
    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;

	@Column(name = "restaurant_name", length = 255)
    private String restaurantName;

    @Column(name = "restaurant_address", length = 255)
    private String restaurantAddress;

    @Column(name = "restaurant_phone", length = 20)
    private String restaurantPhone;
    @Column(name="image_url")
    private String imageUrl;
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@OneToMany(mappedBy = "restaurant",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
	@JsonIgnore
    private List<MenuItems> menuItems;
 
    @OneToMany(mappedBy = "restaurant",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
	@JsonIgnore
    private List<Ratings> ratings;
    
    @OneToMany(mappedBy = "restaurant",fetch=FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
	@JsonIgnore
    private List<Orders> orders;
    
    public Restaurants() {}

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public List<MenuItems> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItems> menuItems) {
		this.menuItems = menuItems;
	}

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

   
    
}
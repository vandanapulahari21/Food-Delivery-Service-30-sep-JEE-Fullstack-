package com.api.foodservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="ratingId")
@Entity
@Table(name = "Ratings")
public class Ratings {

    @Id
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonBackReference
    private Restaurants restaurant;
    
    public Ratings() {}

    public Ratings(int ratingId, Orders order, Restaurants restaurant, int rating, String review) {
		this.ratingId = ratingId;
		this.order = order;
		this.restaurant = restaurant;
		this.rating = rating;
		this.review = review;
	}

	

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
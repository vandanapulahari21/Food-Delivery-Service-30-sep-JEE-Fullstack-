package com.api.foodservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="driverId")
@Entity
@Table(name="deliverydrivers")
public class DeliveryDrivers {

	@Id
	@Column(name="driver_id")
	private int driverId;
	@Column(name="driver_name")
	private String driverName;
	@Column(name="driver_phone")
	private String driverPhone;
	@Column(name="driver_vehicle")
	private String driverVehicle;  
	
	
	@OneToMany(mappedBy="deliveryDriver",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	List<Orders> orders;
	
	public DeliveryDrivers() {}
 
	public DeliveryDrivers(int driverId, String driverName, String driverPhone, String driverVehicle) {
		this.driverId = driverId;
		this.driverName = driverName;
		this.driverPhone = driverPhone;
		this.driverVehicle = driverVehicle;
	}
 
	public int getDriverId() {
		return driverId;
	}
 
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
 
	public String getDriverName() {
		return driverName;
	}
 
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
 
	public String getDriverPhone() {
		return driverPhone;
	}
 
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
 
	public String getDriverVehicle() {
		return driverVehicle;
	}
 
	public void setDriverVehicle(String driverVehicle) {
		this.driverVehicle = driverVehicle;
	}

	public List<Orders> getOrders() {
		return orders;
	}
 
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
}
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
////@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property="customerId")
@Entity
@Table(name="customers")
public class Customer 
{
	@Id
	@Column(name="customer_id")
	private int customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String password;
	

	@OneToMany(mappedBy = "customer",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private List<Orders> orders;
	
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	List<DeliveryAddresses> deliveryAddresses;
	

	public Customer() {}
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DeliveryAddresses> getDeliveryAddresses() {
		return deliveryAddresses;
	}
	public void setDeliveryAddresses(List<DeliveryAddresses> deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
}

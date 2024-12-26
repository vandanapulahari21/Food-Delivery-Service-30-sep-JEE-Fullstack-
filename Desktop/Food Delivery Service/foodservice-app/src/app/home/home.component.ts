import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RestaurantListComponent } from '../restaurant-list/restaurant-list.component';
import { RestaurantListService } from '../restaurant-list/restaurant-list.service';
import { SearchedRestaurantComponent } from '../restaurant/searched-restaurant.component';
 
interface Restaurant{
  restaurantName : string,
  restaurantAddress : string
}
 
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [FormsModule , CommonModule,RestaurantListComponent , SearchedRestaurantComponent],
  providers: [],
  schemas: [NO_ERRORS_SCHEMA]
})
export class HomeComponent implements OnInit
 {
  //adding restaurantName variable
  restaurantName:string="";
  restaurants: Restaurant[] = [];  // Array to hold all restaurants
  filteredRestaurants: Restaurant[] = [];  // Array to hold filtered restaurants based on search query
  searchQuery: string = '';  // String to hold search query
 
  constructor(private restaurantListService: RestaurantListService, private router: Router) {}
 
  ngOnInit(): void {
    this.restaurantName = "";
  }
 
  // Method to filter restaurants based on the search query
  searchRestaurant(event:any): void {
    if(event.key=='Enter')
       this.restaurantName = event.target.value;
    /*this.filteredRestaurants = this.restaurants.filter((restaurant) =>
      restaurant.restaurantName.toLowerCase().includes(this.searchQuery.toLowerCase())
    );*/
  }
 
  // Navigation methods
  navigateToRegister(): void {
    this.router.navigate(['/register']);
  }
 
  navigateToAbout(): void {
    this.router.navigate(['/about']);
  }
 
  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
 
}
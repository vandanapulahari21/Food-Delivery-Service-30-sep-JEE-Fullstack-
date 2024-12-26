import { Component, OnInit } from '@angular/core';
import { RestaurantListService } from './restaurant-list.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
@Component({
  selector: 'restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css'],
  imports: [CommonModule, FormsModule, HttpClientModule],
  providers: [RestaurantListService]
})
export class RestaurantListComponent implements OnInit {
  restaurant: any = { restaurantId: '', restaurantName: "", restaurantAddress: "", restaurantPhone: "" };
  restaurantList: any = [];
  errorMessage: string = '';
 
  constructor(private restaurantService: RestaurantListService, private router:Router) {}
 
  ngOnInit(): void {
    this.getAllRestaurants();
 
  }
 
  public getAllRestaurants() {
    this.restaurantService.getAllRestaurants().subscribe(
      response => {
        this.restaurantList = response.body;
        this.errorMessage = '';
        console.log(response.status);
      },
      (error: HttpErrorResponse) => {  
        this.errorMessage = error.message;  
        console.error(error);
      }
    );
  }
 
 
  public gotoRestaurant(restaurant:any)
  {
      // console.log(restaurant);
       this.router.navigate(['/restaurant']);
  }
}
 
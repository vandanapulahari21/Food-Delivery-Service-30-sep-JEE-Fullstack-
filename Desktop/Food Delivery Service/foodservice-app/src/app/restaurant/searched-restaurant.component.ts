import { Component, Input, OnInit } from "@angular/core";
import { RestaurantListService } from "../restaurant-list/restaurant-list.service";
import { Router } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
 
@Component({
  selector: 'searched-restaurant',
  templateUrl: 'searched-restaurant.component.html',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  styleUrls: ['searched-restaurant.component.css']
})
export class SearchedRestaurantComponent implements OnInit {
  @Input() restaurantName: string = "";
  restaurant: any = null;
  errorMessage: string = '';
 
  constructor(private restaurantListService: RestaurantListService, private router: Router) {}
 
  ngOnInit(): void {
    this.restaurantListService.getRestaurantByName(this.restaurantName)
      .subscribe(
        (response) => this.restaurant = response.body,
        (error) => this.errorMessage = error.message
      );
  }
 
  public gotoRestaurant(restaurant: any) {
    this.router.navigate(['/restaurant']);
  }
}
import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { RestaurantAdminService} from "./restaurants-admin.service";
 
@Component({
    selector: 'app-restaurants',
    templateUrl: './restaurants-admin.component.html',
    standalone: true,
    styleUrls: ['./restaurants-admin.component.css'],
    imports: [CommonModule, FormsModule, HttpClientModule],
    providers: [RestaurantAdminService]
})
export class RestaurantsAdminComponent {
    restaurant: any = { restaurantId: '', restaurantName: "", restaurantAddress: "", restaurantPhone: "",imageUrl:"" };
    restaurantList: any = [];
    errorMessage: string = '';
    menuItemsList: any = [];
    reviewsList: any = [];
    menuItem: any = { itemId: '', itemName: '', itemPrice: '', itemDescription: '' };
 
 
    showRestaurantList: boolean = false;
    showMenuItemsList: boolean = false;
    showReviewsList: boolean = false;
 
    constructor(private restaurantService: RestaurantAdminService) {}
 
    public getAllRestaurants() {
        this.restaurantService.getAllRestaurants().subscribe(
            response => {
                this.restaurantList = response.body;
                this.errorMessage = '';
                this.showRestaurantList = true;
                this.showMenuItemsList = false;
                this.showReviewsList = false;
                console.log(response.status);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public getRestaurantById() {
        this.restaurantService.getRestaurantById(this.restaurant.restaurantId).subscribe(
            response => {
                this.restaurant = response.body;
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = false;
                this.showReviewsList = false;
                console.log(response.statusText);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public addRestaurant() {
        this.restaurantService.addRestaurant(this.restaurant).subscribe(
            response => {
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = false;
                this.showReviewsList = false;
                console.log(response.statusText);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public modifyRestaurant() {
        this.restaurantService.modifyRestaurant(this.restaurant).subscribe(
            response => {
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = false;
                this.showReviewsList = false;
                console.log(response.statusText);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public deleteRestaurantById() {
        this.restaurantService.deleteRestaurantById(this.restaurant.restaurantId).subscribe(
            response => {
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = false;
                this.showReviewsList = false;
                console.log(response.statusText);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public getMenuItemsByRestaurantId() {
        this.restaurantService.getMenuItemsByRestaurantId(this.restaurant.restaurantId).subscribe(
            response => {
                this.menuItemsList = response.body;
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = true;
                this.showReviewsList = false;
                console.log(response.status);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }
 
    public getReviewsByRestaurantId() {
        this.restaurantService.getReviewsByRestaurantId(this.restaurant.restaurantId).subscribe(
            response => {
                this.reviewsList = response.body;
                this.errorMessage = '';
                this.showRestaurantList = false;
                this.showMenuItemsList = false;
                this.showReviewsList = true;
                console.log(response.status);
            },
            error => {
                this.errorMessage = error;
                console.error(error);
            }
        );
    }  
   
}
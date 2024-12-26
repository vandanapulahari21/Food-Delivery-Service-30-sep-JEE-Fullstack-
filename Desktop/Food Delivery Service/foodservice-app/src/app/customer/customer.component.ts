import { Component } from "@angular/core";
import { CustomerService } from "./customer.service";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
 
@Component({
    selector: 'profile',
    templateUrl: './customer.component.html',
    standalone: true,
    imports: [CommonModule, FormsModule],
    styleUrls: ['./customer.component.css'],
    providers: [CustomerService]
})
export class CustomersComponent {
    customer: any = { customerId: '', customerName: "", customerEmail: "", customerPhone: "" };
    customerList: any = [];
    errorMessage: string = '';
    ordersList: any = [];
    reviewsList: any = [];
    restaurantId: any = '';
    showDetailsSection: boolean = false;
    showOrdersSection: boolean = false;
    showReviewsSection: boolean = false;
    showFavoritesSection: boolean = false;
    showUpdateSectionFlag: boolean = false;
    showUpdateFormFlag: boolean = false;
 
    constructor(private customerService: CustomerService, private router: Router) {}
 
    private resetSections(): void {
        this.showDetailsSection = false;
        this.showOrdersSection = false;
        this.showReviewsSection = false;
        this.showFavoritesSection = false;
        this.showUpdateSectionFlag = false;
        this.showUpdateFormFlag = false;
    }
 
    private handleError(error: any): void {
        this.errorMessage = 'An error occurred. Please try again later.';
        console.error('Error:', error);
    }
 
    public showDetails() {
        this.resetSections();
        this.showDetailsSection = true;
        this.getCustomerById(1); // Fetch customer details
    }
   
    public showOrders() {
        this.resetSections();
        this.showOrdersSection = true;
        this.getOrdersByCustomerId(1); // Fetch orders
    }
   
    public showReviews() {
        this.resetSections();
        this.showReviewsSection = true;
        this.getReviewsByCustomerId(1); // Fetch reviews
    }
    public showFavorites() {
        this.resetSections();
        this.showFavoritesSection = true;
    }
 
    public showUpdateSection() {
        this.resetSections();
        this.showUpdateSectionFlag = true;
    }
 
    public showUpdateForm() {
        this.showUpdateFormFlag = true;
    }
 
    public getAllCustomers() {
        this.resetSections();
        this.customerService.getAllCustomers().subscribe(
            response => {
                this.customerList = response.body;
                this.errorMessage = '';
                console.log(response.status);
            },
            error => this.handleError(error)
        );
    }
 
    public getCustomerById(customerId: number) {
        this.resetSections();
        this.customerService.getCustomerById(customerId).subscribe(
            response => {
                this.customer = response.body;
                console.log(response.statusText);
                if (!response.body) {
                    alert('Customer not found.');
                }
            },
            error => this.handleError(error)
        );
    }
 
    public addCustomer() {
        this.resetSections();
        this.customerService.addCustomer(this.customer).subscribe(
            response => {
                this.errorMessage = '';
                alert('Customer details added successfully!');
                console.log(response.statusText);
            },
            error => this.handleError(error)
        );
    }
 
    public modifyCustomer(customerId: number) {
        this.resetSections();
        this.customerService.modifyCustomer(this.customer).subscribe(
            response => {
                alert('Customer details updated successfully!');
                console.log(response.statusText);
            },
            error => this.handleError(error)
        );
    }
 
    public deleteCustomerById(customerId: number) {
        this.resetSections();
        this.customerService.deleteCustomerById(customerId).subscribe(
            response => {
                this.errorMessage = '';
                alert('Deleted successfully!');
                console.log(response.statusText);
            },
            error => this.handleError(error)
        );
    }
 
    public getOrdersByCustomerId(customerId: number) {
        this.resetSections();
        this.customerService.getOrdersByCustomerId(customerId).subscribe(
            response => {
                this.ordersList = response.body;
                this.errorMessage = '';
                console.log(response.status);
            },
            error => this.handleError(error)
        );
    }
 
    public getReviewsByCustomerId(customerId: number) {
        this.resetSections();
        this.customerService.getReviewsByCustomerId(customerId).subscribe(
            response => {
                this.reviewsList = response.body || [];
                this.errorMessage = '';
                console.log(response.status);
            },
            error => this.handleError(error)
        );
    }
 
    public getReviewsText(): string {
        return this.reviewsList.join('\n\n');
    }
 
    public addFavoriteRestaurant(customerId: number, restaurantId: string) {
        this.resetSections();
        this.customerService.addFavoriteRestaurant(customerId, parseInt(restaurantId)).subscribe(
            response => {
                console.log('Response received:', response);
                this.errorMessage = '';
                alert('Customer\'s favorite restaurant added successfully!');
                console.log(response.statusText);
            },
            error => {
                console.error('Error occurred:', error);
                this.handleError(error);
            }
        );
    }
    public removeFavoriteRestaurant(customerId: number, restaurantId: string) {
        this.resetSections();
        this.customerService.removeFavoriteRestaurant(customerId, parseInt(restaurantId)).subscribe(
            response => {
                this.errorMessage = '';
                alert('Customer\'s favorite restaurant removed successfully!');
                console.log(response.statusText);
            },
            error => this.handleError(error)
        );
    }
}
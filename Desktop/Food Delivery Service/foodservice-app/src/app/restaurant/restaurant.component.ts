import { Component, OnInit } from "@angular/core";
import { RestaurantListService } from "../restaurant-list/restaurant-list.service";
import { CommonModule } from "@angular/common";
import { FormsModule } from '@angular/forms';
import { Router } from "@angular/router";
 
@Component({
  selector: 'restaurant',
  templateUrl: './restaurant.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule],
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {
  menuItems: any[] = [];
  cart: { itemId: number; quantity: number }[] = [];
  isCartVisible: boolean = false;
  couponCode: string = '';
  discount: number = 0;
 
  constructor(private router: Router,private restaurantListService: RestaurantListService) {}
 
  ngOnInit(): void {
    this.restaurantListService.getMenuItemsByRestaurantId(3).subscribe((response) => {
      this.menuItems = response.body || [];
      console.log(this.menuItems);
    });
  }
 
  addToCart(itemId: number): void {
    const existingItem = this.cart.find((item) => item.itemId === itemId);
    if (existingItem) {
      existingItem.quantity++;
    } else {
      this.cart.push({ itemId, quantity: 1 });
    }
  }
 
  removeFromCart(itemId: number): void {
    const existingItem = this.cart.find((item) => item.itemId === itemId);
    if (existingItem) {
      if (existingItem.quantity > 1) {
        existingItem.quantity--;
      } else {
        this.cart = this.cart.filter((item) => item.itemId !== itemId);
      }
    }
  }
 
  toggleCartVisibility(): void {
    this.isCartVisible = !this.isCartVisible;
  }
 
  removeCart(): void {
    this.isCartVisible = false;
  }
 
  getItemName(itemId: number): string {
    return this.menuItems.find((menu) => menu.itemId === itemId)?.itemName || '';
  }
 
  getItemPrice(itemId: number): number {
    return this.menuItems.find((menu) => menu.itemId === itemId)?.itemPrice || 0;
  }
 
  getTotalPrice(): number {
    const total = this.cart.reduce((total, item) => {
      const itemPrice = this.getItemPrice(item.itemId);
      return total + itemPrice * item.quantity;
    }, 0);
    return total - (total * this.discount / 100);
  }
 
  applyCoupon(): void {
    const totalPrice = this.getTotalPrice();
   
    if (totalPrice > 100) {
      if (this.couponCode === 'SuperWomen') {
        this.discount = 20;
      } else if (this.couponCode === 'Team9') {
        this.discount = 10;
      } else {
        this.discount = 0;
      }
    } else {
      this.discount = 0;
      alert('Total price must be above ₹100 to apply a coupon.');
    }
  }
 
  isProceedEnabled(): boolean {
    return this.getTotalPrice() > 0;
  }
 
  proceed() {
    if (this.isProceedEnabled() && localStorage.getItem('role')=='user' && localStorage.getItem('isLoggedIn')=='y')
      this.router.navigate(['/payment']);
    else
        this.router.navigate(['/login'])  
  }
}
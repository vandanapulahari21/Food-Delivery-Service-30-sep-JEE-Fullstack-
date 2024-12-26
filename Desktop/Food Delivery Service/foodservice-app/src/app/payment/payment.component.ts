import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
 
@Component({
  selector: 'payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
  imports: [FormsModule, CommonModule]
})
export class PaymentComponent {
  billing = {
    name: '',
    email: '',
    address: '',
    city: '',
    zip: ''
  };
  paymentMethod: string = 'cod';
  constructor(private router: Router) {}
  payment() {
 
    if (this.billing.name && this.billing.email && this.billing.address && this.billing.city && this.billing.zip) {
      console.log("Billing Information:", this.billing);
      console.log("Payment Method:", this.paymentMethod);
      this.router.navigate(['/app-order-success']);
   
    } else {
      alert("Please fill in all the required fields.");
    }
  }
}
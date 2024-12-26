import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-delivery-driver',
  templateUrl: './delivery-driver.component.html',
  styleUrls: ['./delivery-driver.component.css'],
  imports: [CommonModule]
})
export class DeliveryDriverComponent {
  // Flag to show delivery driver details
  showDeliveryDetails: boolean = true;
  
  // Array of multiple delivery driver details
  deliveryDrivers: any[] = [
    { name: 'John Doe', phone: '+1234567890', estimatedDeliveryTime: '30 minutes' },
    { name: 'Jane Smith', phone: '+0987654321', estimatedDeliveryTime: '45 minutes' },
    { name: 'Mike Johnson', phone: '+1122334455', estimatedDeliveryTime: '25 minutes' },
    { name: 'Emily Davis', phone: '+6677889900', estimatedDeliveryTime: '35 minutes' }
  ];

  // Variable to store the selected delivery driver
  selectedDriver: any;

  constructor(private router: Router) {
    this.assignRandomDriver();
  }

  // Method to randomly assign a delivery driver
  assignRandomDriver() {
    const randomIndex = Math.floor(Math.random() * this.deliveryDrivers.length);
    this.selectedDriver = this.deliveryDrivers[randomIndex];
  }

  // Method to navigate to the home page when "Home" button is clicked
  goHome() {
    this.router.navigate(['/login-home']);  // Replace with the correct route to your home page
  }
}

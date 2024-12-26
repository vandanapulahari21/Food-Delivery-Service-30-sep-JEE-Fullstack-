import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { CustomerService } from '../customer/customer.service';
import { RegisterService } from './register.service';
@Component({
  selector: 'app-register',
  templateUrl:'./register.component.html',
  styleUrls: ['./register.component.css'],
  imports : [ReactiveFormsModule, CommonModule,FormsModule]
}) 
export class RegisterComponent {
  registerForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
  customer: any = { customerId: '', customerName: "", customerEmail: "", customerPhone: "" };
  constructor(
    private fb: FormBuilder,
    private registerService: RegisterService,
    private customerService: CustomerService,  
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],  
      email: ['', [Validators.required, Validators.email]],  
      phone: ['', [Validators.required, Validators.pattern(/^\+[1-9]\d{1,14}$/)]],  
      password: ['',[Validators.required, Validators.minLength(8)]]
    });
  }
  navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
  public addCustomer() {
    this.customerService.addCustomer(this.customer).subscribe(
      response => {
        this.errorMessage = '';
        console.log(response.statusText);
      },
      error => {
        this.errorMessage = error;
        console.error(error);
      }
    );
  }
}
 
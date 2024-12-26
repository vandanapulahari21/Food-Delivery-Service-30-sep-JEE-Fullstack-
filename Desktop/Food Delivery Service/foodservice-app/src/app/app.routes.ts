import { Routes } from '@angular/router';

import { RestaurantComponent } from './restaurant/restaurant.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AboutComponent } from './about/about.component';
import { LoginComponent } from './login/login.component';
import { CustomersComponent } from './customer/customer.component';
import { PaymentComponent } from './payment/payment.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { DeliveryDriverComponent } from './delivery-driver/delivery-driver.component';
import { LoginHomeComponent } from './home/login-home.component';
import { RestaurantsAdminComponent } from '../restaurants-admin/restaurants-admin.component';
 
export const routes: Routes = [
    { path: '', component: HomeComponent }, // Main entry point (AppComponent)
    { path: 'login', component:LoginComponent},
    { path: 'register', component: RegisterComponent }, // Register page
    { path: 'about', component: AboutComponent},
    { path: 'restaurant',component:RestaurantComponent},
    {path:'profile',component:CustomersComponent},
    {path:'app-order-success',component:OrderSuccessComponent},
    {path:'payment',component:PaymentComponent},
    {path:'app-delivery-driver',component:DeliveryDriverComponent},
    { path: 'login-home', component:LoginHomeComponent},
    {path:'app-restaurants',component:RestaurantsAdminComponent},
    { path: '**', redirectTo: '' } // Wildcard route for unknown paths
];
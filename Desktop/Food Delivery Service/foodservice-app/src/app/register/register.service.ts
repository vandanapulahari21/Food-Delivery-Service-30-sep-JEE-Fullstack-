import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
 
export interface User {
  name: string;
  email: string;
  phone: string;
}
 
@Injectable({
  providedIn: 'root'
})
export class RegisterService {
 
  private apiUrl = 'http://localhost:8091/customers';
 
  constructor(private http: HttpClient) {}
 
  // Method to send user registration data to the backend
  registerUser(user: User): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');  // Set headers
 
    return this.http.post(this.apiUrl, user, { headers });  // Send POST request with user data
  }
}
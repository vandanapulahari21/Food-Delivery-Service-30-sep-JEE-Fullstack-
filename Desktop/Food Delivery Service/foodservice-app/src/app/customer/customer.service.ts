import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
 
@Injectable({
    providedIn: 'root'
})
export class CustomerService {
    private static url: string = "http://localhost:8091/customers";
 
    constructor(private httpClient: HttpClient) {}
 
    public getAllCustomers(): Observable<HttpResponse<any[]>> {
        return this.httpClient.get<any[]>(CustomerService.url, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
   
    public getCustomerById(customerId: number): Observable<HttpResponse<any>> {
        return this.httpClient.get<any>(`${CustomerService.url}/${customerId}`, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
   
    public addCustomer(customer: any): Observable<HttpResponse<any>> {
        return this.httpClient.post<any>(CustomerService.url, customer, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
   
    public modifyCustomer(customer: any): Observable<HttpResponse<any>> {
        return this.httpClient.put<any>(`${CustomerService.url}/${customer.customerId}`, customer, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
   
    public deleteCustomerById(customerId: number): Observable<HttpResponse<any>> {
        return this.httpClient.delete<any>(`${CustomerService.url}/${customerId}`, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
    public getOrdersByCustomerId(customerId: number): Observable<HttpResponse<any[]>> {
      return this.httpClient.get<any[]>(`${CustomerService.url}/${customerId}/orders`, { observe: 'response' })
          .pipe(catchError(this.handleError));
    }
   
    public getReviewsByCustomerId(customerId: number): Observable<HttpResponse<any[]>> {
      return this.httpClient.get<any[]>(`${CustomerService.url}/${customerId}/reviews`, { observe: 'response' })
          .pipe(catchError(this.handleError));
    }
 
    public addFavoriteRestaurant(customerId: number, restaurantId: number): Observable<HttpResponse<any>> {
      return this.httpClient.post<any>(`${CustomerService.url}/${customerId}/favorites?restaurantId=${restaurantId}`,{ observe: 'response' })
          .pipe(catchError(this.handleError));
    }
 
    public removeFavoriteRestaurant(customerId: number, restaurantId: number): Observable<HttpResponse<any>> {
      return this.httpClient.delete<any>(`${CustomerService.url}/${customerId}/favorites/${restaurantId}`, { observe: 'response' })
          .pipe(catchError(this.handleError));
    }
 
    private handleError(error: HttpErrorResponse) {
      let errorMessage = 'Unknown error!';
      if (error.error instanceof ErrorEvent) {
          // Client-side errors
          errorMessage = `Error: ${error.error.message}`;
      } else {
          // Server-side errors
          errorMessage = error.error || `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
      return throwError(errorMessage);
    }
}
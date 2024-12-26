import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
 
@Injectable({
    providedIn: 'root'
})
export class RestaurantAdminService {
    private static url: string = "http://localhost:8091/restaurants";
 
    constructor(private httpClient: HttpClient) {}
 
    // Get all restaurants
    public getAllRestaurants(): Observable<HttpResponse<any[]>> {
        return this.httpClient.get<any[]>(RestaurantAdminService.url, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Get restaurant by ID
    public getRestaurantById(restaurantId: number): Observable<HttpResponse<any>> {
        return this.httpClient.get(RestaurantAdminService.url +"/" + restaurantId, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Add a new restaurant
    public addRestaurant(restaurant: any): Observable<HttpResponse<any>> {
        return this.httpClient.post<any>(RestaurantAdminService.url, restaurant, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Modify an existing restaurant
    public modifyRestaurant(restaurant: any): Observable<HttpResponse<any>> {
        return this.httpClient.put<any>(`${RestaurantAdminService.url}/${restaurant.restaurantId}`, restaurant, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Delete restaurant by ID
    public deleteRestaurantById(restaurantId: number): Observable<HttpResponse<any>> {
        return this.httpClient.delete<any>(`${RestaurantAdminService.url}/${restaurantId}`, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Get menu items by restaurant ID
    public getMenuItemsByRestaurantId(restaurantId: number): Observable<HttpResponse<any[]>> {
        return this.httpClient.get<any[]>(`${RestaurantAdminService.url}/${restaurantId}/menu-items`, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    // Get reviews by restaurant ID
    public getReviewsByRestaurantId(restaurantId: number): Observable<HttpResponse<any[]>> {
        return this.httpClient.get<any[]>(`${RestaurantAdminService.url}/${restaurantId}/reviews`, { observe: 'response' })
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
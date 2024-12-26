import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpResponse } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
 
@Injectable({
    providedIn: 'root'
})
export class RestaurantListService {
    private static url: string = "http://localhost:8091/restaurants";
 
    constructor(private httpClient: HttpClient) {}
   
    public getAllRestaurants(): Observable<HttpResponse<any[]>> {
        return this.httpClient.get<any[]>(RestaurantListService.url, { observe: 'response' })
            .pipe(catchError(this.handleError));
    }
 
    public getMenuItemsByRestaurantId(id:number) : Observable<HttpResponse<any[]>>
    {
         return this.httpClient.get<any[]>(RestaurantListService.url+"/"+id+"/menu-items" , {observe: 'response'})
         .pipe(catchError(this.handleError));
    }
    getRestaurantByName(restaurantName: string): Observable<HttpResponse<any>> {
        return this.httpClient.get<any>(`${RestaurantListService.url}/name/${restaurantName}`, { observe: 'response' })
          .pipe(catchError(this.handleError));
      }
 
    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'Unknown error!';
        if (error.error instanceof ErrorEvent) {
            errorMessage = `Error: ${error.error.message}`;
        } else {
            errorMessage = error.error || `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        return throwError(errorMessage);
    }
}
 
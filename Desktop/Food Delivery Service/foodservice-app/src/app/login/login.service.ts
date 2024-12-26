import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient ,HttpResponse} from "@angular/common/http";
@Injectable({
    providedIn:'root'
})
export class LoginService
{
    private url = 'http://localhost:8091/users';
    constructor(private httpClient:HttpClient) {}
    public authenticate(user:any):Observable<HttpResponse<any>>
    {
        return this.httpClient.post(this.url,user,{responseType:'text',observe:'response'});
    }
}
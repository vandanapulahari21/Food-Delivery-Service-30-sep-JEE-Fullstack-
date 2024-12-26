import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { LoginService } from "./login.service";
import { Router } from "@angular/router";
 
@Component({
    selector:'login',
    templateUrl:'./login.component.html',
    styleUrl:'./login.component.css',
    standalone:true,
    imports:[FormsModule,CommonModule],
    providers:[LoginService]
 
})
export class LoginComponent
{
    isLoggedIn:any;
    role:string="";
    navigateToRegister(): void {
        this.router.navigate(['/register']);
      }
 
    constructor(private loginService:LoginService,private router:Router) {}
   public authenticate(form:any) :void{
    this.loginService.authenticate({"userId":form.value.email,"password":form.value.password})
    .subscribe(response=>{localStorage.setItem('role',response.body);
                          localStorage.setItem('isLoggedIn','y');
             if(response.body=='Admin')
                this.router.navigate(['/app-restaurants'])
             else if(response.body=='user')
                this.router.navigate(['/login-home']);
            else{
               alert(response.body);
               localStorage.setItem('isLoggedIn','n');
            }
    });
   }
}
 
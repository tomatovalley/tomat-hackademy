import { LocalAuthService } from './../../../services/local-auth.service';
import { UserInterface } from './../../../models/user-interface';
//import { AuthService } from './../../../services/auth.service'; 
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  AuthService,
  FacebookLoginProvider,
  GoogleLoginProvider
} from 'angular-6-social-login'; /*Import para usar Social Media */



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private socialAuthService: AuthService, private localAuth: LocalAuthService) { }
  private user: UserInterface = {
    email: "",
    password: "",
    user_name: ""
  };

  ngOnInit() {
  }

  onLogin(){
    return this.localAuth.loginUser(this.user.user_name, this.user.password)
    .subscribe(result=>{
      console.log(result[0]);
      this.localAuth.setUser(result[0])
      let token = result[0].id;
      this.localAuth.setToken(token);
      //this.router.navigate(["/user/profile"]);
      this.router.navigate(["all-events"]);
    },
      error => console.log(error)
    );
  }

  getTokenLogin(){
    return this.localAuth.getTokenDjango(this.user.user_name, this.user.password)
    .subscribe(result=>{
      console.log(result);
      console.log("It's working");
      /*this.localAuth.setUser(result[0])
      let token = result[0].id;
      this.localAuth.setToken(token);*/
      //this.router.navigate(["/user/profile"]);
      this.router.navigate(["all-events"]);
    },
      error => console.log(error)
    );
  }

  onLoginDjango(){
    return this.localAuth.loginUserDjango(this.user.user_name, this.user.password)
    .subscribe(result=>{
      console.log(result.token);
      console.log("It's working");
      /*this.localAuth.setUser(result[0])
      let token = result[0].id;
      this.localAuth.setToken(token);*/
      //this.router.navigate(["/user/profile"]);
      this.router.navigate(["all-events"]);
    },
      error => console.log(error)
    );
  }

  onLoginGoogle(){
    return this.localAuth.loginGoogle()
    .subscribe(result=>{
      console.log(result[0]);
      this.localAuth.setUser(result[0]);
      let token = result[0]._id;
      this.localAuth.setToken(token);
      this.router.navigate(["/user/profile"]);
    },
      error => console.log(error)
    );
  }

  onLoginTwitter(){
    return this.localAuth.loginTwitter()
    .subscribe(result=>{
      console.log(result[0]);
      this.localAuth.setUser(result[0]);
      let token = result[0]._id;
      this.localAuth.setToken(token);
      //this.router.navigate(["/user/profile"]);
      this.router.navigate(["/all-events"]);
    },
      error => console.log(error)
    );
  }
  
  //Login Social Media para Facebook y Google+, "funciona" parcialmente
  public socialSignIn(socialPlatform : string) {
    let socialPlatformProvider;
    if(socialPlatform == "facebook"){
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    }else if(socialPlatform == "google"){
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    } 
    
    this.socialAuthService.signIn(socialPlatformProvider).then(
      (userData) => {
        console.log(socialPlatform+" Datos : " , userData);
        this.router.navigate(["/user/profile"]);
        // Sign-in con Userdata
        // ...
        this.localAuth.setUser(userData);
        let token = userData.idToken;
        this.localAuth.setToken(token);     
        this.localAuth.registerUser(
          this.user.name = userData.name,
          this.user.last_name = userData.name,
          this.user.user_name,
          this.user.email = userData.email,
          this.user.password,
          this.user.birthdate,
          this.user.gender 
        )  
      }
    );
  }

}

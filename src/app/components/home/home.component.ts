import { UserInterface } from './../../models/user-interface';
import {
  AuthService,
  FacebookLoginProvider,
  GoogleLoginProvider
} from 'angular-6-social-login'; /*Import para usar Social Media */
import { Component, OnInit } from '@angular/core';
import { DataApiService } from 'src/app/services/data-api.service';
import { Router } from '@angular/router';
import { LocalAuthService } from 'src/app/services/local-auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private dataApi: DataApiService, private localAuth: LocalAuthService, private router: Router, private socialAuthService: AuthService) { }
  private user: UserInterface = {
    name: "",
    last_name: "",
    user_name: "",
    email: "",
    password: "",
    gender: "Hombre",
    birthdate: new Date()
  };

  ngOnInit() {
    this.getListUsers();
  }

  getListUsers(){
    this.dataApi.getAllUsers().subscribe(users => console.log(users));
  }

  onRegister(): void{
    this.localAuth.registerUser(
      this.user.name,
      this.user.last_name,
      this.user.user_name,
      this.user.email,
      this.user.password,
      this.user.birthdate,
      this.user.gender
    )
    .subscribe(user =>{
      console.log(user);
      this.localAuth.setUser(user);
      let token = user._id;
      this.localAuth.setToken(token);
      this.router.navigate(["/user/profile"]);
      //console.log(token);
      //console.log(this.user.birthdate);
    });
  }
  //Login Social Media para Facebook y Google+, "funciona" parcialmente
  /*public socialSignIn(socialPlatform : string) {
    let socialPlatformProvider;
    if(socialPlatform == "facebook"){
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    }else if(socialPlatform == "google"){
      socialPlatformProvider = GoogleLoginProvider.PROVIDER_ID;
    } 
    
    this.socialAuthService.signIn(socialPlatformProvider).then(
      (userData) => {
        console.log(socialPlatform+" sign in data : " , userData);
        this.router.navigate(["/user/profile"]);
        this.localAuth.registerUser(
          this.user.name = userData.name,
          this.user.last_name = userData.name,
          this.user.user_name,
          this.user.email = userData.email,
          this.user.password,
          this.user.birthdate,
          this.user.gender 

        ).subscribe(user =>{
          console.log(user);
          this.localAuth.setUser(user);
          let token = user._id;
          this.localAuth.setToken(token);
          this.router.navigate(["/user/profile"]);
          //console.log(token);
          //console.log(this.use r.birthdate);
        });
        // Now sign-in with userData
        // ...
            
      }
    );
  }*/
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
        ).subscribe(user =>{
          console.log(user);
          this.localAuth.setUser(user);
          let token = userData.idToken;
          this.localAuth.setToken(token);
          this.router.navigate(["/user/profile"]);
        })   
      }
    );
  }
}

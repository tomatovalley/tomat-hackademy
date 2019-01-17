import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { UserInterface } from 'src/app/models/user-interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) { }
  private user: UserInterface = {
    name: "",
    last_name: "",
    user_name: "",
    email: "",
    password: "",
    gender: "",
    birthdate: new Date()
  };

  ngOnInit() {
  }

  onRegister(): void{
    this.authService.registerUser(
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
      this.authService.setUser(user);
      let token = user._id;
      this.authService.setToken(token);
      this.router.navigate(["/user/profile"]);
      console.log(token);
    });
  }

}

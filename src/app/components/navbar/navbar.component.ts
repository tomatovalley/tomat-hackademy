import { DataApiService } from './../../services/data-api.service';
import { UserInterface } from 'src/app/models/user-interface';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  constructor(private authService: AuthService, private dataApiService: DataApiService) { }
  user: UserInterface;
  public app_name = "Tomato-Hackademy";
  public isLogged: boolean = false;
  ngOnInit() {
    this.onCheckUser();
    this.user = this.authService.getCurrentUser();
  }

  onLogout(): void{
    this.authService.logoutUser();
  }

  onCheckUser(){
    if(this.authService.getCurrentUser()==null){
      this.isLogged = false;
    }else{
      this.isLogged = true;
    }
  }
  
  onSelectedUser(user: UserInterface): void{
    this.dataApiService.selectedUser = Object.assign({}, user);
  }

  
  
}

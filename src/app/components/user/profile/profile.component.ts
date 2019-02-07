import { DataApiService } from './../../../services/data-api.service';
import { UserInterface } from 'src/app/models/user-interface';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(private authService: AuthService, private dataApiService: DataApiService) { }
  user: UserInterface;

  ngOnInit() {
    this.user = this.authService.getCurrentUser();
    //console.log(this.user.name);
  }

  onPreUpdateUser(user: UserInterface): void{
    this.dataApiService.selectedUser = Object.assign({}, user);
  }

}

import { UserInterface } from 'src/app/models/user-interface';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  constructor(private authService: AuthService) { }
  user: UserInterface;

  ngOnInit() {
    this.user = this.authService.getCurrentUser();
    //console.log(this.user.name);
  }

}

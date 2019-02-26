import { DataApiService } from 'src/app/services/data-api.service';
import { AuthService } from '../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { UserInterface } from 'src/app/models/user-interface';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  constructor(private dataApiService: DataApiService, private location: Location) { }

  ngOnInit() {
  }
  /*onPreUpdateUser(user: UserInterface): void{
    this.dataApiService.selectedUser = Object.assign({}, user);
  }*/

  onSaveUser(userForm: NgForm){
    if(userForm.value.userId==null){
      //New
      this.dataApiService.saveUser(userForm.value).subscribe(user => location.reload());
    }else{
      this.dataApiService.updateUser(userForm.value).subscribe(user => location.reload());
    }
  }
}

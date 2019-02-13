import { UserInterface } from './../models/user-interface';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/internal/Observable";
import { map } from "rxjs/operators";



import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DataApiService {
  constructor(private http: HttpClient, private authService: AuthService) { }
  users: Observable<any>;
  user: Observable<any>;
  
  public selectedUser: UserInterface = {
    _id: null,
    name: "",
    password: "",
    email: ""
  };
  
  headers : HttpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: this.authService.getToken()
    });
  getAllUsers(){
    const url_api = "http://localhost:3000/users";
    return this.http.get(url_api);
  }
  
  getUserById(id: string){
    const url_api = `http://localhost:3000/users/${id}`;
    return this.user = this.http.get(url_api);
  }
  saveUser(user: UserInterface){
    let token = this.authService.getToken();  
    const url_api = `http://localhost:3000/signup?access_token=${token}`;
    return this.http.post<UserInterface>(url_api, {headers: this.headers})
    .pipe(map(data => data));
  }

  updateUser(user) {
    // TODO: obtener token
    // TODO: not null
    
    const userId = user.userId;
    console.log(user);
    const id = user._id;
    const token = this.authService.getToken();
    const url_api = `http://localhost:3000/users/${userId}/?access_token=${token}`;
    return this.http
      .put<UserInterface>(url_api, user, { headers: this.headers })
      .pipe(map(data => data));
  }
  
  getEvents(){
    const url_api = "http://localhost:8000/events/get_eventos";
    return this.http.get(url_api);
  }
}

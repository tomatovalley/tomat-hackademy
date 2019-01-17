import { UserInterface } from './../models/user-interface';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/internal/Observable';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { isNullOrUndefined } from 'util';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }
  headers : HttpHeaders = new HttpHeaders({
    "Content-Type": "application/json"
  });
  

  registerUser(name: string, last_name: string, user_name: string, email: string, password: string, birthdate: Date, gender: string){
    const url_api = "http://localhost:3000/users/signup";
    //const url_api = "http://localhost:3000/users";
    return this.http.post<UserInterface>(url_api, {
      name: name, 
      last_name: last_name, 
      user_name: user_name,
      email: email, 
      password: password,
      birthdate: birthdate,
      gender: gender
    }, {headers: this.headers}
    ).pipe(map(data=>data));
  }

  loginUser(email: string, password: string): Observable<any>{
    const url_api = "http://localhost:3000/users/login";
    return this.http.post<UserInterface>(url_api, {
      email: email, 
      password: password
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }

  setUser(user: UserInterface): void {
    let user_string = JSON.stringify(user);
    localStorage.setItem("currentUser", user_string);
  }

  setToken(token): void {
    localStorage.setItem("accessToken", token);
  }

  getToken(){
    return localStorage.getItem("accessToken"); 
  }

  getCurrentUser(): UserInterface {
    let user_string = localStorage.getItem("currentUser");
    if(!isNullOrUndefined(user_string)){
      let user: UserInterface = JSON.parse(user_string );
      return user;
    }else{
      return null;
    }
  }

  logoutUser(){
    let accessToken = localStorage.getItem("accessToken")
    const url_api = `http:/localhost:3000/api/users/logout?access_token=${accessToken}`;
    localStorage.removeItem("accessToken");
    localStorage.removeItem("currentUser");
    return this.http.post<UserInterface>(url_api, { headers: this.headers})
  }

  loginFacebook(){
    const url_api = "http://localhost:3000/users/loginFacebook";
    return this.http.post<UserInterface>(url_api, {
      //email: email, 
      //password: password
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }
}

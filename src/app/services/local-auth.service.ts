import { UserInterface } from './../models/user-interface';
import { Modelo } from 'src/app/models/modelo';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/internal/Observable';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { isNullOrUndefined } from 'util';

@Injectable({
  providedIn: 'root'
})
export class LocalAuthService {

  constructor(private http: HttpClient) { }
  headers : HttpHeaders = new HttpHeaders({
    "Content-Type": "application/json"
  });
  
  registerUser(name: string, last_name: string, user_name: string, email: string, password: string, birthdate: Date, gender: string){
    const url_api = "http://157.230.182.120/nd/users/signup";
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

  /*registerUserDjango(user_name: string, password: string){
    const url_api = "http://192.168.10.65:8000/eventos/clientes/";
    //const url_api = "http://localhost:3000/users";
    return this.http.post<UserInterface>(url_api, {
      user_name: user_name,
      password: password
    }, {headers: this.headers}
    ).pipe(map(data=>data));
  }*/

  registerUserDjango(username: string, password: string){
    const url_api = "http://157.230.182.120/dj/eventos/clientes/";
    return this.http.post<Modelo>(url_api, {
      username: username,
      password: password
    }, {headers: this.headers}
    ).pipe(map(data=>data));
  }
  
  loginUser(user_name: string, password: string): Observable<any>{
    const url_api = "http://157.230.182.120/nd/users/login";
    return this.http.post<UserInterface>(url_api, {
      user_name: user_name, 
      password: password
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }

  getTokenDjango(username: string, password: string): Observable<any>{
    const url_api = "http://192.168.10.63:8000/api/token/";
    return this.http.post<UserInterface>(url_api, {
      username: username, 
      password: password
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }

  loginUserDjango(username: string, password: string): Observable<any>{
    const url_api = "http://157.230.182.120/dj/eventos/login/";
    return this.http.post<UserInterface>(url_api, {
      username: username, 
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

  loginGoogle(): Observable<any>{
    const url_api = "http://localhost:3000/users/loginGoogle";
    return this.http.post<UserInterface>(url_api, {
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }

  loginTwitter(): Observable<any>{
    const url_api = "http://localhost:3000/users/twitter/login";
    return this.http.post<UserInterface>(url_api, {
    }, { headers: this.headers}
    ).pipe(map(data=>data))
  }
}

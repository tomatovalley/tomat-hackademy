import { EmprendimientoInterface } from './../models/emprendimiento-interface';
import { EventInterface } from 'src/app/models/event-interface';
import { UserInterface } from './../models/user-interface';
import { Modelo } from 'src/app/models/modelo';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from "rxjs/internal/Observable";
import { map } from "rxjs/operators";

import { AuthService } from './auth.service';
import { Time, formatDate } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class DataApiService {
  constructor(private http: HttpClient, private authService: AuthService) { }
  users: Observable<any>;
  user: Observable<any>;
  events: Observable<any>;
  event: Observable<any>;
  modelo: Observable<any>;
  modelos: Observable<any>;
  
  public selectedUser: UserInterface = {
    _id: null,
    name: "",
    password: "",
    email: ""
  };

  public selectedEmp: EmprendimientoInterface = {
    id: null,
    name: "",
    date: new Date(),
    comment_user: "",
    comment: ""
  }

  public createEvent: EventInterface = {
    name: "hola",
    place: "hola",
    start_hour: "",
    end_hour: "",
    begin_date: null,
    final_date: null,
    image: null,
    description: "hola",
    organizer: "hola",
    facebook: "hola",
    instagram: "hola",
    twitter: "hola"
  }
  
  headers : HttpHeaders = new HttpHeaders({
      "Content-Type": "application/json",
      Authorization: this.authService.getToken()
    });
  getAllUsers(){
    const url_api = "http://157.230.182.120/nd/users";
    return this.http.get(url_api);
  }
  
  getUserById(id: string){
    const url_api = `http://157.230.182.120/nd/users/${id}`;
    return this.user = this.http.get(url_api);
  }
  saveUser(user: UserInterface){
    let token = this.authService.getToken();  
    const url_api = `http://157.230.182.120/nd/signup?access_token=${token}`;
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
    const url_api = `http://157.230.182.120/nd/users/${userId}/?access_token=${token}`;
    return this.http
      .put<UserInterface>(url_api, user, { headers: this.headers })
      .pipe(map(data => data));
  }
  
  getEvents(){
    const url_api = "http://157.230.182.120/dj/eventos/detalles_evento/";
    return this.http.get(url_api);
  }

  saveEvent(name: string, place: string, start_hour: string, image: File, end_hour: string, description: string, organizer: string, facebook: string, instagram: string, twitter: string){
    const url_api = `http://157.230.182.120/dj/eventos/crear_evento/`;
    return this.http.post<UserInterface>(url_api, {
      name: name,
      place: place,
      start_hour: start_hour,
      image: image,
      end_hour: end_hour,
      description: description,
      organizer: organizer,
      facebook: facebook,
      instagram: instagram,
      twitter: twitter
    }, {headers: this.headers})
    .pipe(map(data => data));
  }

  registerEvent(name: string, place: string, start_hour: string, end_hour: string, description: string, organizer: string, facebook: string, instagram: string, twitter: string){
    const url_api = "http://157.230.182.120/dj/eventos/crear_evento/";
    return this.http.post<UserInterface>(url_api, {
      name: name, 
      place: place, 
      //begin_date: begin_date,
      start_hour: start_hour,
      //final_date: final_date,
      //image: image,
      end_hour: end_hour,
      description: description,
      organizer: organizer,
      facebook: facebook,
      instagram: instagram,
      twitter: twitter
    }, {headers: this.headers}
    ).pipe(map(data=>data));
  }

  getEmprendimientos(){
    const url_api = "http://157.230.182.120/dj/eventos/crear_emprendimiento/";
    return this.http.get(url_api);
  }

  getEmprendimientosById(id: string){
    const url_api = `http://157.230.182.120/dj/eventos/crear_emprendimiento/${id}/`;
    return this.http.get(url_api);
  }

  registerComment(comment_user: string, name: string, comment: string){
    const url_api = `http://157.230.182.120/dj/eventos/comment_emp/`;
    return this.http.post<UserInterface>(url_api, {
      comment_user: comment_user,
      name: name,
      comment: comment
    }, {headers: this.headers}
    ).pipe(map(data=>data));
  }

  registerCommentt(comment_user: string, nameEmp: string, comment: string){
    const url_api = "http://157.230.182.120/dj/eventos/comment_emp/";
    return this.http.post<EmprendimientoInterface>(url_api, {
      comment_user: comment_user,
      nameEmp: nameEmp,
      comment: comment
    }, {headers: this.headers} 
    ).pipe(map(data=>data));
  }

  putComment(emprendimiento: EmprendimientoInterface){
    const url_api = "http://157.230.182.120/dj/eventos/comment_emp/";
    return this.http.put(url_api + `/${emprendimiento.id}`, emprendimiento);
  }

}

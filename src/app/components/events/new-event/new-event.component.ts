import { AuthService } from './../../../services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { UserInterface } from './../../../models/user-interface';
import { DataApiService } from 'src/app/services/data-api.service';
import { Component, OnInit } from '@angular/core';
import { EventInterface } from 'src/app/models/event-interface';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent implements OnInit {
  selectedFile: File = null;
  fileToUpload: File = null;
  errors = null;
  constructor(private dataApiService: DataApiService, private http: HttpClient, private router: Router, private authService: AuthService) { }
  headers : HttpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
    "Authorization": "Bearer Token"
  });
  private event: EventInterface = {
    name: "",
    place: "",
    //begin_date: new Date(),
    //image: new ImageData(3333,9),
    //image: new File(prototype,3,3),
    //start_hour: new Date().getTime(),
    //image: new ImageData(300, 300),
    start_hour: "",
    //final_date: new Date(),
    end_hour: "",
    description: "",
    organizer: ".",
    facebook: "https://www.youtube.com/watch?v=HSgyPfibrqQ",
    instagram: "https://www.youtube.com/watch?v=HSgyPfibrqQ",
    twitter: "https://www.youtube.com/watch?v=HSgyPfibrqQ"
  };

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
    this.getListEvents();
    this.user = this.authService.getCurrentUser();
  }

  getListEvents(){
    this.dataApiService.getEvents().subscribe(events => console.log(events));
  }

  onFileChanged(event) {
      this.selectedFile = event.target.files[0];
      console.log(this.selectedFile);
  }

  onFileSelected(event){
    this.selectedFile = <File>event.target.files[0];
    console.log(event);
  }

  onRegisterEvent():void{
    var reader = new FileReader();
    this.dataApiService.registerEvent(
      this.event.name,
      this.event.place,
      //this.event.begin_date,
      this.event.start_hour,
      //this.event.final_date, 
      //this.event.image,
      this.event.end_hour,
      //this.selectedFile,
      this.event.description,
      this.event.organizer,
      this.event.facebook,
      this.event.instagram,
      this.event.twitter,
    )
    .subscribe(event =>{
      console.log(event);
      this.router.navigate(["/user/profile"]);
      //console.log(token);
      //console.log(this.user.birthdate);
    });
  }

  showModal(){
    Swal.fire({
      title: '¡FELICIDADES, EVENTO CREADO!',
      text: '',
      type: 'success',
      confirmButtonText: 'Ok!'
    })
  }

  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name);
    fd.append('name', this.event.name);
    fd.append('place', this.event.place);
    fd.append('username', this.dataApiService.selectedUser.user_name);
    fd.append('start_hour', this.event.start_hour);
    fd.append('end_hour', this.event.end_hour);
    fd.append('description', this.event.description);
    fd.append('organizer', this.event.organizer);
    fd.append('facebook', this.event.facebook);
    fd.append('instagram', this.event.instagram);
    fd.append('twitter', this.event.twitter);
    this.http.post('http://157.230.182.120/dj/eventos/crear_evento/', fd)
      .subscribe(res => {
        console.log(res);
      }, 
      error => {
        console.log(error);
        this.errors = error
      }
      )
      if (this.errors==null) {
        //route to new page
        this.showModal();
      }
      
  }

  onSelectedUser(user: UserInterface): void{
    this.dataApiService.selectedUser = Object.assign({}, user);
  }

  //¡FELICIDADES EVENTO CREADO!
}

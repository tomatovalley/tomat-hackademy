import { EventInterface } from './../../../models/event-interface';
import { AuthService } from './../../../services/auth.service';
import { UserInterface } from './../../../models/user-interface';
import { DataApiService } from 'src/app/services/data-api.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-events',
  templateUrl: './all-events.component.html',
  styleUrls: ['./all-events.component.css']
})
export class AllEventsComponent implements OnInit {

  constructor(private dataApiService: DataApiService, private router: Router, private authService: AuthService) { }
  user: UserInterface;
  private events: EventInterface;
  ngOnInit() {
    this.getListEvents();
    this.getListEmp();
    this.user = this.authService.getCurrentUser();
  }

  getListEvents(){
    this.dataApiService.getEvents().subscribe(events => console.log(events));
  }

  getListEmp(){
    this.dataApiService.getEvents().subscribe((events: EventInterface )=>(this.events = events));
  }

}

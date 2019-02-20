import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-organiza-event',
  templateUrl: './organiza-event.component.html',
  styleUrls: ['./organiza-event.component.css']
})
export class OrganizaEventComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
    eventoCreado(){
      
      Swal.fire({
        title:'Felicidades evento creado',
        text:'',
        type:'success',
        confirmButtonText:'Regresar'
      });
    }
  


}

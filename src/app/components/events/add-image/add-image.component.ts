import { DataApiService } from 'src/app/services/data-api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-image',
  templateUrl: './add-image.component.html',
  styleUrls: ['./add-image.component.css']
})
export class AddImageComponent implements OnInit {

  constructor(private dataApiService: DataApiService) { }

  ngOnInit() {
    this.getListEmprendimientos();
  }

  getListEmprendimientos(){
    this.dataApiService.getEmprendimientos().subscribe(emps => console.log(emps));
  }

}

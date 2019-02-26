import { AuthService } from 'src/app/services/auth.service';
import { UserInterface } from 'src/app/models/user-interface';
import { DataApiService } from 'src/app/services/data-api.service';
import { Component, OnInit } from '@angular/core';
import { EmprendimientoInterface } from 'src/app/models/emprendimiento-interface';
@Component({
  selector: 'app-trending',
  templateUrl: './trending.component.html',
  styleUrls: ['./trending.component.css']
})
export class TrendingComponent implements OnInit {
  
  constructor(private dataApi: DataApiService, private authService: AuthService) { }
  user: UserInterface;
  private emps: EmprendimientoInterface
  ngOnInit() {
    this.getListEmp();
    this.user = this.authService.getCurrentUser();
  }

  getListEmp(){
    this.dataApi.getEmprendimientos().subscribe((emps: EmprendimientoInterface )=>(this.emps = emps));
  }

  onSelectedEmp(emps: EmprendimientoInterface): void{
    this.dataApi.selectedEmp = Object.assign({}, emps);
  }

  onSelectedUser(user: UserInterface): void{
    this.dataApi.selectedUser = Object.assign({}, user);
  }


}

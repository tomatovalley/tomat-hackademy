import { UserInterface } from 'src/app/models/user-interface';
import { EmprendimientoInterface } from './../../models/emprendimiento-interface';
import { DataApiService } from 'src/app/services/data-api.service';
import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Params } from '@angular/router'; 
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-comevent',
  templateUrl: './comevent.component.html',
  styleUrls: ['./comevent.component.css']
})
export class ComeventComponent implements OnInit {

  user: UserInterface;
  constructor(private dataApi: DataApiService, private route: ActivatedRoute, private authService: AuthService) { }
  private emp: EmprendimientoInterface = {
    username: "",
    name: "",
    comment_user: "",
    comment: ""

  }
  
  ngOnInit() {
    const emp_id = this.route.snapshot.params["id"];
    this.getEmp(emp_id);
  }

  getEmp(id: string){
    this.dataApi.getEmprendimientosById(id)
    .subscribe(emp => (this.emp = emp));
  }

  onPostComment(): void{
    this.dataApi.registerComment(
      this.dataApi.selectedUser.user_name,
      this.emp.name,
      this.emp.comment,
    ).subscribe(emp => {
      console.log(emp);
    })
  }

}

import { AddImageComponent } from './components/events/add-image/add-image.component';
import { CompletePerfilWriteComponent } from './components/user/complete-perfil-write/complete-perfil-write.component';
import { IntroduceCodConfirmacionComponent } from './components/user/introduce-cod-confirmacion/introduce-cod-confirmacion.component';
import { CompletarPerfilComponent } from './components/user/completar-perfil/completar-perfil.component';
import { CodigoConfirmacionComponent } from './components/codigo-confirmacion/codigo-confirmacion.component';
import { AuthGuard } from './guards/auth.guard';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { Page404Component } from './components/page404/page404.component';
import { UpdateProfileComponent } from './components/user/update-profile/update-profile.component';
import { AllEventsComponent } from './components/events/all-events/all-events.component';
import { NewEventComponent } from './components/events/new-event/new-event.component';
import { OrganizaEventComponent } from './components/events/organiza-event/organiza-event.component';
import { ComeventComponent } from './components/comevent/comevent.component';
import { TrendingComponent } from './components/trending/trending.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "user/login", component: LoginComponent},
  {path: "user/register", component: RegisterComponent},
  {path: "user/update", component: UpdateProfileComponent, canActivate: [AuthGuard]},
  {path: "user/completar-perfil", component: CompletarPerfilComponent, canActivate: [AuthGuard]},
  {path: "codigo-confirmacion", component: CodigoConfirmacionComponent},
  {path: "complete-write", component: CompletePerfilWriteComponent, canActivate: [AuthGuard]},
  {path: "int-codigo", component: IntroduceCodConfirmacionComponent},
  {path: "all-events", component: AllEventsComponent},
  {path: "new-event", component: NewEventComponent, canActivate: [AuthGuard]},
  {path: "organiza-event", component: OrganizaEventComponent},
  {path: "comment-events/:id", component: ComeventComponent, canActivate: [AuthGuard]},
  {path: "add-image", component: AddImageComponent},
  {path: "trending", component: TrendingComponent},
  {path: "user/profile", component: ProfileComponent, canActivate: [AuthGuard]},
  {path: "**", component: Page404Component},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

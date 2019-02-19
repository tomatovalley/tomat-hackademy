import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SocialmediaRegistrationComponent } from './socialmedia-registration/socialmedia-registration.component';
import { FooterComponent } from './footer/footer.component';
import { NavVarComponent } from './nav-var/nav-var.component';
import { CodigoConfirmacionComponent } from './codigo-confirmacion/codigo-confirmacion.component';
import { IntroduceCodConfirmacionComponent } from './introduce-cod-confirmacion/introduce-cod-confirmacion.component';
import { CompletarPerfilComponent } from './completar-perfil/completar-perfil.component';
import { CompletePerfilWriteComponent } from './complete-perfil-write/complete-perfil-write.component';
import { RegistroUsuariosComponent } from './registro-usuarios/registro-usuarios.component';
import { FinalizacionRegComponent } from './finalizacion-reg/finalizacion-reg.component';

@NgModule({
  declarations: [
    AppComponent,
    SocialmediaRegistrationComponent,
    FooterComponent,
    NavVarComponent,
    CodigoConfirmacionComponent,
    IntroduceCodConfirmacionComponent,
    CompletarPerfilComponent,
    CompletePerfilWriteComponent,
    RegistroUsuariosComponent,
    FinalizacionRegComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

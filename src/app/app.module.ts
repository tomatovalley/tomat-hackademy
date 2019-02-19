import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HeroComponent } from './components/hero/hero.component';
import { LoginComponent } from './components/user/login/login.component';
import { RegisterComponent } from './components/user/register/register.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { UpdateProfileComponent } from './components/user/update-profile/update-profile.component';
import { Page404Component } from './components/page404/page404.component';
import { FormsModule } from '@angular/forms';

//Services
import { DataApiService } from 'src/app/services/data-api.service';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './components/footer/footer.component';
import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider, FacebookLoginProvider } from "angular-6-social-login";
import { CodigoConfirmacionComponent } from './components/codigo-confirmacion/codigo-confirmacion.component';
import { CompletarPerfilComponent } from './components/user/completar-perfil/completar-perfil.component';
import { IntroduceCodConfirmacionComponent } from './components/user/introduce-cod-confirmacion/introduce-cod-confirmacion.component';
import { CompletePerfilWriteComponent } from './components/user/complete-perfil-write/complete-perfil-write.component';
import { AllEventsComponent } from './components/events/all-events/all-events.component';
import { NewEventComponent } from './components/events/new-event/new-event.component';
import { EventsComponent } from './src/app/components/events/events.component';
import { OrganizaEventComponent } from './components/events/organiza-event/organiza-event.component';
import { LandingComponent } from './landing/landing.component';
import { TrendingComponent } from './trending/trending.component';

import { ComeventComponent } from './components/comevent/comevent.component';
import { ImagenEventComponent } from './components/events/imagen-event/imagen-event.component';
import { ImageComponent } from './image/image.component';


/*const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('');
], false); */

export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig(
      [
        {
          id: FacebookLoginProvider.PROVIDER_ID,
          provider: new FacebookLoginProvider("2100491900262029")
        },
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider("503524599322-aihkdacm33o7k02po1oafi1b9j564jgq.apps.googleusercontent.com")
        }
      ]
  );
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    HeroComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    Page404Component,
    FooterComponent,
    UpdateProfileComponent,
    CodigoConfirmacionComponent,
    CompletarPerfilComponent,
    IntroduceCodConfirmacionComponent,
    CompletePerfilWriteComponent,
    AllEventsComponent,
    NewEventComponent,
    EventsComponent,
    OrganizaEventComponent,
    LandingComponent,
    TrendingComponent,
    ComeventComponent,
    ImagenEventComponent,
    ImageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SocialLoginModule
  ],
  providers: [DataApiService,{ provide:AuthServiceConfig, useFactory: getAuthServiceConfigs}],
  bootstrap: [AppComponent]
})
export class AppModule { }

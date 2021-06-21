import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CookieModule} from "ngx-cookie";
import {RxStompService} from "@stomp/ng2-stompjs";
import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    CookieModule.forRoot(),
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    RxStompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

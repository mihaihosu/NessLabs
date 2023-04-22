import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatIconModule} from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AuthenticationPageComponent } from './authentication-page/authentication-page.component';
import { CreateAccountComponent } from './create-account/create-account.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginAdminComponent,
    AuthenticationPageComponent,
    CreateAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    ReactiveFormsModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

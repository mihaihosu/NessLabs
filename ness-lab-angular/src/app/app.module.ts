import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InputComponent } from './generic/input/input.component';
import { LoginComponent } from './components/login/login.component';
import { FormComponent } from './generic/form/form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from './generic/button/button.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    InputComponent,
    LoginComponent,
    FormComponent,
    ButtonComponent,
    CreateAccountComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, ReactiveFormsModule, FormsModule, MatCardModule, MatIconModule,MatButtonModule,MatInputModule,MatFormFieldModule,],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

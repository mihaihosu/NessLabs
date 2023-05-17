import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventCardsComponent } from './shared/components/event-cards/event-cards.component';
import { LayoutComponent } from './layout/layout.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { IconModule } from './shared/icon/icon/icon.module';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './shared/material.module';
import { HomePageComponent } from './home-page/home-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ChangesSavedDialogComponent } from './shared/components/changes-saved-dialog/changes-saved-dialog/changes-saved-dialog.component';
import { PleaseLoginDialogComponent } from './shared/components/please-login-dialog/please-login-dialog.component';
import { DeletingEventDialogComponent } from './shared/components/deleting-event-dialog/deleting-event-dialog/deleting-event-dialog.component';
import { EventPostedDialogComponent } from './shared/components/event-posted-dialog/event-posted-dialog/event-posted-dialog.component';
import { DraftSavedDialogComponent } from './shared/components/draft-saved-dialog/draft-saved-dialog/draft-saved-dialog.component';
import { InputComponent } from './generic/input/input.component';
import { LoginComponent } from './components/login/login.component';
import { FormComponent } from './generic/form/form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonComponent } from './generic/button/button.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    EventCardsComponent,
    LayoutComponent,
    NavbarComponent,
    HomePageComponent,
    PleaseLoginDialogComponent,
    ChangesSavedDialogComponent,
    EventPostedDialogComponent,
    DeletingEventDialogComponent,
    DraftSavedDialogComponent,
    InputComponent,
    LoginComponent,
    FormComponent,
    ButtonComponent,
    CreateAccountComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatIconModule,
    MaterialModule,
    FormsModule,
    IconModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
  ],

  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}

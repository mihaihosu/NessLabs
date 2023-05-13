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
import { DialogPleaseLoginService } from './services/dialog-please-login.service';
import { PleaseLoginDialogComponent } from './shared/components/please-login-dialog/please-login-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    EventCardsComponent,
    LayoutComponent,
    NavbarComponent,
    HomePageComponent,
    PleaseLoginDialogComponent,
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
  ],

  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}

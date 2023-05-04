import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatIconModule, MatIconRegistry } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventCardsComponent } from './event-cards/event-cards.component';
import { LayoutComponent } from './layout/layout.component';

@NgModule({
  declarations: [AppComponent, EventCardsComponent, LayoutComponent],
  imports: [BrowserModule, AppRoutingModule, MatIconModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

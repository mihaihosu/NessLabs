import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchService } from '../services/search-service/search.service';
import { ViewEncapsulation } from '@angular/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { AuthService } from '../services/auth-services/auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
  encapsulation: ViewEncapsulation.Emulated,
})
export class LayoutComponent implements OnInit {
  constructor(
    private searchService: SearchService,
    private authService: AuthService
  ) {}

  selectedDate: Date = new Date();
  selectedCards: string = 'all-events';
  isLogin = 'false';
  isOpen: boolean = false;
  isSelectedDate: boolean = false;
  @ViewChild('picker') picker: MatDatepicker<any>;
  isAdmin: boolean = false;

  onSelectedCardsView(view: string) {
    this.selectedCards = view;
  }

  onSelectedTagButton(index: number) {
    this.buttons.buttons[index].selected =
      !this.buttons.buttons[index].selected;
  }

  // sendSearchDateCards() {
  //   this.searchService.searchDate(this.selectedDate);
  //   console.log(this.selectedDate);
  // }

  selectDate() {
    this.isSelectedDate = !this.isSelectedDate;
  }

  open() {
    this.picker.open();
    this.isOpen = true;
    console.log(this.isOpen);
  }

  close() {
    this.picker.close();
    this.isOpen = false;
    console.log(this.isOpen);
  }

  buttons = {
    buttons: [
      {
        name: 'galerie arta',
        selected: false,
        icon: '🎨',
      },
      {
        name: 'food',
        selected: false,
        icon: '🍔',
      },
      {
        name: 'concert',
        selected: false,
        icon: '🎶',
      },
      {
        name: 'festival',
        selected: false,
        icon: '🎶',
      },
      {
        name: 'teatru',
        selected: false,
        icon: '🎭',
      },
      {
        name: 'expozitie',
        selected: false,
        icon: '🏜',
      },
      {
        name: 'food street',
        selected: false,
        icon: '🍔',
      },
      {
        name: 'spectacol',
        selected: false,
        icon: '🎭',
      },
      {
        name: 'pet friendly',
        selected: false,
        icon: '🐶',
      },
      {
        name: 'kid friendly',
        selected: false,
        icon: '👨‍🦲',
      },
    ],
  };

  ngOnInit(): void {
    this.isAdmin = this.authService.isAdmin;
    console.log('Este admin: ' + this.isAdmin);
  }
}

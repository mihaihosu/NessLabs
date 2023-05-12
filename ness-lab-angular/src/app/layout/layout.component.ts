import { Component, OnInit } from '@angular/core';
import { EventCardsComponent } from '../shared/components/navbar/event-cards/event-cards.component';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  constructor() {}

  selectedDate: any;

  selectedCards: string = 'all-events';

  onSelectedCardsView(view: string) {
    this.selectedCards = view;
    console.log(view);
  }

  buttons = {
    buttons: [
      {
        name: 'galerie arta',
      },
      {
        name: 'food',
      },
      {
        name: 'concert',
      },
      {
        name: 'festival',
      },
      {
        name: 'teatru',
      },
      {
        name: 'expozitie',
      },
      {
        name: 'food street',
      },
      {
        name: 'spectacol',
      },
      {
        name: 'pet friendly',
      },
      {
        name: 'kid friendly',
      },
    ],
  };

  ngOnInit(): void {}
}

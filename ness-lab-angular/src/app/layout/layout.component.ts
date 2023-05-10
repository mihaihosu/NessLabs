import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  constructor() {}

  selectedDate = 'Select Date';

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

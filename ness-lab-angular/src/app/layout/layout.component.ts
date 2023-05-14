import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  constructor() {}

  selectedDate: any;
  selectedCards: string = 'all-events';
  isLogin = 'false';

  onSelectedCardsView(view: string) {
    this.selectedCards = view;
  }

  onSelectedTagButton(index: number) {
    this.buttons.buttons[index].selected =
      !this.buttons.buttons[index].selected;
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

  ngOnInit(): void {}
}

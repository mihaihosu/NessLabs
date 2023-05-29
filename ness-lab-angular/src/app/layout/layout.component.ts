import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search-service/search.service';
import { DialogService } from '../services/dialog-service/dialog.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  constructor(private searchService: SearchService, private openDialogService: DialogService) {}

  selectedDate: Date = new Date();
  selectedCards: string = 'all-events';
  isLogin = 'false';

  onSelectedCardsView(view: string) {
    this.selectedCards = view;
  }

  onSelectedTagButton(index: number) {
    this.buttons.buttons[index].selected =
      !this.buttons.buttons[index].selected;
  }

  sendSearchDateCards() {
    this.searchService.searchDate(this.selectedDate);
    console.log(this.selectedDate);
  }

  addNew() {
    this.openDialogService.openAddNewEvent()
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

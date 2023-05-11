import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-cards',
  templateUrl: './event-cards.component.html',
  styleUrls: ['./event-cards.component.scss'],
})
export class EventCardsComponent implements OnInit {
  constructor() {}

  events = {
    events: [
      {
        data: '30 MARTIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
      },
      {
        data: '03 OCTOMBRIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img2.png',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img4.png',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
      },
    ],
  };

  ngOnInit(): void {}
}

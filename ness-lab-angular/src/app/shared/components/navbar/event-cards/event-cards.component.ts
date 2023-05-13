import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-event-cards',
  templateUrl: './event-cards.component.html',
  styleUrls: ['./event-cards.component.scss'],
})
export class EventCardsComponent implements OnChanges, OnInit {
  constructor(private searchCardsService: SearchService) {
    this.searchSubscription = this.searchCardsService.searchSubject$.subscribe(
      (param: string) => {
        this.searchEvents(param);
      }
    );
  }

  @Input() selectedCards: string = 'all-events';
  searchEventsCards: any[] = [];
  private searchSubscription: Subscription;

  searchEvents(param: string) {
    if (param) {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.events.events.filter((event: any) => {
          const eventTitle = event.titlu.toLowerCase();
          return eventTitle.includes(param.toLowerCase());
        });
      } else {
        this.searchEventsCards = this.myevents.myevents.filter((event: any) => {
          const eventTitle = event.titlu.toLowerCase();
          return eventTitle.includes(param.toLowerCase());
        });
      }
    } else {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.events.events;
      } else {
        this.searchEventsCards = this.myevents.myevents;
      }
    }
  }

  ngOnDestroy() {
    this.searchSubscription.unsubscribe();
  }

  ngOnChanges() {
    if (this.selectedCards === 'all-events') {
      this.searchEventsCards = this.events.events;
    } else {
      this.searchEventsCards = this.myevents.myevents;
    }
  }

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
        titlu: 'Saptamana Verde',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img2.png',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Festival Nostalgia',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Atelier Pictura',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Robotii de azi',
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
        titlu: 'Cursuri Yoga',
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
  myevents = {
    myevents: [
      {
        data: '30 MARTIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
      },
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
        titlu: 'Saptamana Verde',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img2.png',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Festival Nostalgia',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Atelier Pictura',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Robotii de azi',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img4.png',
      },
    ],
  };
  ngOnInit(): void {}
}

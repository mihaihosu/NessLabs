import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-event-cards',
  templateUrl: './event-cards.component.html',
  styleUrls: ['./event-cards.component.scss'],
})
export class EventCardsComponent implements OnChanges, OnInit {
  constructor(
    private searchCardsService: SearchService,
    private dialogService: DialogService
  ) {
    this.searchSubscription = this.searchCardsService.searchSubject$.subscribe(
      (param: string) => {
        this.searchEvents(param);
      }
    );
    this.searchSubscription =
      this.searchCardsService.searchDateSubject$.subscribe((param: Date) => {
        this.searchEventsDate(param);
      });
  }

  private searchSubscription: Subscription;
  isLogin = false;
  @Input() selectedCards: string = 'all-events';
  searchEventsCards: any[] = [];
  noEventsYet: string = 'No Event Yet';

  noEventsYetText: string =
    'Click the " + Add New Event" button to add some events, and you\'ll see the events here next time you visit this page.';

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

  searchEventsDate(param: Date) {
    if (param) {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.events.events.filter((event: any) => {
          const eventDate = event.data.toLowerCase();
          return eventDate.includes(param);
        });
      } else {
        this.searchEventsCards = this.myevents.myevents.filter((event: any) => {
          const eventDate = event.titlu.toLowerCase();
          return eventDate.includes(param);
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

  hasPassed(event: any): boolean {
    const eventData = new Date(event.endDateTime);
    const currentData = new Date();
    return eventData < currentData;
  }

  openModal() {
    this.dialogService.openPleaseLoginDialog();
    console.log('Click !');
  }

  events = {
    events: [
      {
        startDateTime: '30 MARTIE 2023',
        startTime: '16:00',
        ora: '20:00',
        duration: '3h',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
        type: 'free',
        status: 'available',
        endDateTime: new Date(2023, 6, 24, 20, 43),
      },
      {
        data: '03 OCTOMBRIE 2023',
        ora: '20:00',
        titlu: 'Saptamana Verde',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img2.png',
        type: 'with ticket',
        status: 'draft',
        endDateTime: new Date(2023, 0, 24, 16, 43),
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Festival Nostalgia',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
        type: 'with ticket',
        status: 'unavailable',
        endDateTime: new Date(2023, 9, 24, 9, 43),
      },
      {
        data: '15 MAI 2023',
        ora: '20:00',
        titlu: 'Atelier Pictura',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
        type: 'with ticket',
        status: 'draft',
        endDateTime: new Date(2023, 1, 24, 9, 43),
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img8.png',
        type: 'free',
        status: 'unavailable',
        endDateTime: new Date(2023, 2, 24, 9, 43),
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Robotii de azi',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img4.png',
        type: 'with ticket',
        status: 'draft',
        endDateTime: new Date(2023, 7, 24, 22, 43),
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
        type: 'free',
        status: 'available',
        endDateTime: new Date(2023, 11, 24, 9, 43),
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Cursuri Yoga',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
        type: 'free',
        status: 'draft',
        endDateTime: new Date(2023, 10, 24, 19, 43),
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
        type: 'with ticket',
        status: 'available',
        endDateTime: new Date(2023, 3, 24, 9, 43),
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img8.png',
        type: 'free',
        status: 'unavailable',
        endDateTime: new Date(2023, 8, 24, 21, 43),
      },
    ],
  };
  myevents = {
    myevents: [
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
        type: 'with ticket',
        status: 'available',
        endDateTime: new Date(2023, 1, 24, 9, 43),
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img8.png',
        type: 'free',
        status: 'unavailable',
      },
      {
        data: '30 MARTIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
        type: 'with ticket',
        status: 'draft',
        endDateTime: new Date(2023, 1, 24, 9, 43),
      },
      {
        data: '30 MARTIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
        type: 'with ticket',
        status: 'unavailable',
      },
      {
        data: '03 OCTOMBRIE 2023',
        ora: '20:00',
        titlu: 'Saptamana Verde',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img2.png',
        type: 'with ticket',
        status: 'available',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Festival Nostalgia',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
        type: 'free',
        status: 'draft',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Atelier Pictura',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img5.png',
        type: 'with ticket',
        status: 'available',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Curs De Gatit',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
        type: 'free',
        status: 'draft',
      },
      {
        data: '15 APRILIE 2023',
        ora: '20:00',
        titlu: 'Robotii de azi',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img4.png',
        type: 'with ticket',
        status: 'available',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Cursuri Yoga',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img7.png',
        type: 'free',
        status: 'draft',
      },
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/universe.png',
        type: 'with ticket',
        status: 'available',
      },
      {
        data: '13 FEBRUARIE 2023',
        ora: '20:00',
        titlu: 'Concert Chitara',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img3.png',
        type: 'with ticket',
        status: 'draft',
      },
    ],
  };
  // myevents = {
  //   myevents: [],
  // };
  ngOnInit(): void {}
}

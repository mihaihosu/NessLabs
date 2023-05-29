import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth-services/auth.service';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';
import { NavbarService } from 'src/app/services/navbar-service/navbar.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-event-cards',
  templateUrl: './event-cards.component.html',
  styleUrls: ['./event-cards.component.scss'],
})
export class EventCardsComponent implements OnChanges, OnInit {
  constructor(
    private searchCardsService: SearchService,
    private dialogService: DialogService,
    private authService: AuthService,
    private navbarService: NavbarService
  ) {
    // this.searchSubscription = this.searchCardsService.searchSubject$.subscribe(
    //   (param: string) => {
    //     this.searchEvents(param);
    //   }
    // );
    // this.searchSubscription =
    //   this.searchCardsService.searchDateSubject$.subscribe((param: Date) => {
    //     this.searchEventsDate(param);
    //   });
    this.subscriptionFavorite =
      this.navbarService.isFavoriteClickedObservable.subscribe(
        (clicked: boolean) => {
          this.isFavoriteClicked = clicked;
        }
      );
  }

  isFavorite: boolean = false;
  isConfirmed: boolean = false;
  isAdmin: boolean = false;
  isFavoriteClicked: boolean = false;
  isHomeClicked: boolean = false;
  private searchSubscription: Subscription;
  @Input() selectedCards: string = 'my-events';
  searchEventsCards: any[] = [];
  noEventsYet: string = 'No Event Yet';
  noEventsYetText: string =
    'Click the " + Add New Event" button to add some events, and you\'ll see the events here next time you visit this page.';
  needToLogin: string = 'Need to Login';
  needToLoginText: string =
    'To add events to your favorites list,please log in to your account, or create one.';
  private subscriptionFavorite: Subscription;

  searchEvents(param: string) {
    if (param) {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.eventsAll.filter((event: any) => {
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
        this.searchEventsCards = this.eventsAll;
      } else {
        this.searchEventsCards = this.myevents.myevents;
      }
    }
  }

  searchEventsDate(param: Date) {
    if (param) {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.eventsAll.filter((event: any) => {
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
        this.searchEventsCards = this.eventsAll;
      } else {
        this.searchEventsCards = this.myevents.myevents;
      }
    }
  }

  ngOnDestroy() {
    // this.searchSubscription.unsubscribe();
    this.subscriptionFavorite.unsubscribe();
  }

  ngOnChanges() {
    if (this.selectedCards === 'all-events') {
      this.searchEventsCards = this.eventsAll;
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
  }

  openDeleteModal() {
    this.dialogService.openDeletingEventDialog();
  }

  addToFavorite() {
    this.isFavorite = !this.isFavorite;
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
        author: 'OtherAdminName',
        is_free: true,
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
        author: 'AdminName',
        is_free: false,
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
        author: 'OtherAdminName',
        is_free: true,
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
        author: 'OtherAdminName',
        is_free: false,
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
        author: 'AdminName',
        is_free: true,
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
        author: 'AdminName',
        is_free: false,
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
        author: 'AdminName',
        is_free: false,
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
        author: 'AdminName',
        is_free: true,
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
        author: 'OtherAdminName',
        is_free: true,
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
        author: 'OtherAdminName',
        is_free: false,
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
        author: 'OtherAdminName',
        is_free: false,
        is_draft: true,
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
        author: 'OtherAdminName',
        is_free: true,
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
        author: 'OtherAdminName',
        is_free: true,
        is_draft: true,
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
        author: 'OtherAdminName',
        is_free: false,
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
        is_free: true,
        is_draft: true,
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
        is_free: false,
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
        is_free: true,
        is_draft: true,
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
        is_free: false,
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
        is_free: true,
        is_draft: true,
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
        is_free: false,
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
        is_free: true,
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
        is_free: false,
      },
    ],
  };
  mostPopular = {
    mostPopular: [
      {
        data: '12 IUNIE 2023',
        ora: '20:00',
        titlu: 'Teatru Papusi',
        loc: 'Piata Unirii',
        autor: 'OtherAdminName',
        imgURL: './assets/img/img10.png',
        type: 'with ticket',
        status: 'available',
        endDateTime: new Date(2023, 12, 24, 9, 43),
        author: 'OtherAdminName',
        is_free: true,
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
        endDateTime: new Date(2023, 11, 24, 9, 43),
        author: 'OtherAdminName',
        is_free: false,
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
        endDateTime: new Date(2023, 12, 24, 9, 43),
        author: 'AdminName',
        is_free: true,
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
        endDateTime: new Date(2023, 8, 24, 9, 43),
        author: 'OtherAdminName',
        is_free: true,
      },
    ],
  };

  eventsAll: any[] = this.events.events;
  // eventsAll: any[] = [];
  // myevents = {
  //   myevents: [],
  // };
  ngOnInit(): void {
    this.selectedCards = 'all-events';
    this.isConfirmed = this.authService.isConfirm;
    this.isAdmin = this.authService.isAdmin;
  }
}

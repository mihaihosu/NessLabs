import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Card } from 'src/app/interfaces/card';
import { AuthService } from 'src/app/services/auth-services/auth.service';
import { CardsService } from 'src/app/services/cards/cards.service';
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
    private navbarService: NavbarService,
    private cardsServices: CardsService
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
        this.searchEventsCards = this.myEvents.filter((event: any) => {
          const eventTitle = event.titlu.toLowerCase();
          return eventTitle.includes(param.toLowerCase());
        });
      }
    } else {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.eventsAll;
      } else {
        this.searchEventsCards = this.myEvents;
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
        this.searchEventsCards = this.myEvents.filter((event: any) => {
          const eventDate = event.titlu.toLowerCase();
          return eventDate.includes(param);
        });
      }
    } else {
      if (this.selectedCards === 'all-events') {
        this.searchEventsCards = this.eventsAll;
      } else {
        this.searchEventsCards = this.myEvents;
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
      this.searchEventsCards = this.myEvents;
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

  addToFavorite(event: any) {
    event.isFavorite = !event.isFavorite;
  }

  eventsAll: Card[] = this.cardsServices.getCards();
  myEvents: Card[] = this.cardsServices.getMyCards();
  mostPopular: Card[] = this.cardsServices.getMostPopularCards();
  favoriteEvents: Card[] = this.cardsServices
    .getCards()
    .filter((card) => card.isFavorite === true && !this.hasPassed(card));

  // eventsAll: any[] = [];
  // myevents = {
  //   myevents: [],
  // };

  ngOnInit(): void {
    console.log(this.favoriteEvents);
    this.selectedCards = 'all-events';
    this.isConfirmed = this.authService.isConfirm;
    this.isAdmin = this.authService.isAdmin;
  }
}

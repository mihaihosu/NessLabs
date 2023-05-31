import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NavbarService {
  constructor() {}

  private isFavoriteClickedSubject$ = new Subject<boolean>();
  isFavoriteClickedObservable = this.isFavoriteClickedSubject$.asObservable();

  private isHomeClickedSubject$ = new Subject<boolean>();
  isHomeClickedObservable = this.isFavoriteClickedSubject$.asObservable();

  emitClickFavorite(clicked: boolean) {
    this.isFavoriteClickedSubject$.next(clicked);
  }

  emitClickHome(clicked: boolean) {
    this.isHomeClickedSubject$.next(clicked);
  }
}

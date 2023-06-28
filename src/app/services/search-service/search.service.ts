import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  constructor() {}

  private searchSubject = new Subject<string>();
  public searchSubject$ = this.searchSubject.asObservable();

  private searchDateSubject = new Subject<Date>();
  public searchDateSubject$ = this.searchDateSubject.asObservable();

  search(param: string) {
    this.searchSubject.next(param);
  }

  searchDate(date: Date) {
    this.searchDateSubject.next(date);
  }
}

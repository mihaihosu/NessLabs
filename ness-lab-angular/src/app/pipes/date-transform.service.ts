import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class DateTransformService {
  constructor(private datePipe: DatePipe) {}

  transformStartDate(date: Date): string {
    return this.datePipe.transform(date, 'dd MMMM yyyy');
  }
  transformStartTime(time: Date): string {
    return this.datePipe.transform(time, 'HH:mm');
  }
}

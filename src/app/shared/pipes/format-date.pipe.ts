import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatDate'
})
export class FormatDatePipe implements PipeTransform {

  transform(value: any): any {
    const optionsDate: Intl.DateTimeFormatOptions = {
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    };
    const optionsTime:Intl.DateTimeFormatOptions={
      hour:'2-digit',
      minute:'2-digit'
    }
    const formatedDate= new Intl.DateTimeFormat('en-GB', optionsDate).format(value);
    const formatedTime= new Intl.DateTimeFormat('en-GB',optionsTime).format(value);
    return `${formatedDate}, ORA ${formatedTime}`  
  }
}

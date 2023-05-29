import { Component, ElementRef, OnInit } from '@angular/core';
import { DialogService } from '../services/dialog-service/dialog.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-new-event',
  templateUrl: './add-new-event.component.html',
  styleUrls: ['./add-new-event.component.scss']
})
export class AddNewEventComponent implements OnInit {
  form = this.fb.group({
    title: ['', [Validators.required]]
  })
  exportTime = { hour: 7, minute: 15, meriden: 'PM', format: 12 };
  constructor(private openDialog: DialogService, private fb: FormBuilder, public element: ElementRef, private http: HttpClient) { }

  ngOnInit(): void {
  }
  
  onChangeHour(event: any) {
    console.log('event', event);
  }
  addNew() {
    this.openDialog.openEventPostedDialog()
  }

  url = 'https://img.icons8.com/ios/100/000000/contract-job.png';
  onSelect(event: any) {
    let fileType = event.target.files[0].type;
    if (fileType.match(/image\/*/)) {
      let reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event: any) => {
        this.url = event.target.result;
      };
    } else {
      window.alert('Please select correct image format');
    }
  }

  uploadButtonClicked() {
    document.getElementById('img')?.click();
  }
}

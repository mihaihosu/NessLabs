import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-event-posted-dialog',
  templateUrl: './event-posted-dialog.component.html',
  styleUrls: ['./event-posted-dialog.component.scss'],
})
export class EventPostedDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<EventPostedDialogComponent>) {}

  @Input() titluModal: string = 'Event Posted';
  @Input() loginMessage: string = 'Your event was posted with success!';

  closeDialog(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {}
}

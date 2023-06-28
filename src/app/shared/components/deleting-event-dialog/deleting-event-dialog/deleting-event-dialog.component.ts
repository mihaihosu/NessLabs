import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-deleting-event-dialog',
  templateUrl: './deleting-event-dialog.component.html',
  styleUrls: ['./deleting-event-dialog.component.scss'],
})
export class DeletingEventDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<DeletingEventDialogComponent>) {}

  @Input() titluModal: string = 'Deleting Event';
  @Input() loginMessage: string =
    'Your are about to delete this event. Are you certain you want to proceed?';

  closeDialog(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {}
}

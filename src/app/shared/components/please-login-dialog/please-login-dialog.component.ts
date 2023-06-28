import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-please-login-dialog',
  templateUrl: './please-login-dialog.component.html',
  styleUrls: ['./please-login-dialog.component.scss'],
})
export class PleaseLoginDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<PleaseLoginDialogComponent>) {}

  @Input() titluModal: string = 'Please Login';
  @Input() loginMessage: string =
    'Please login to your account before adding events to your favorites list.';

  ngOnInit(): void {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}

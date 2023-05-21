import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-my-account-dialog',
  templateUrl: './my-account-dialog.component.html',
  styleUrls: ['./my-account-dialog.component.scss'],
})
export class MyAccountDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<MyAccountDialogComponent>) {}

  changePassword: boolean = false;
  saveChanges: boolean = false;
  isFieldClicked = false;
  showPassword1: boolean = false;
  showPassword2: boolean = false;

  saveChangesClick() {
    this.saveChanges = true;
  }
  changePasswordClick() {
    this.changePassword = true;
  }
  closeDialog(): void {
    this.dialogRef.close();
  }
  showPasswordClick1() {
    this.showPassword1 = !this.showPassword1;
  }
  showPasswordClick2() {
    this.showPassword2 = !this.showPassword2;
  }
  ngOnInit(): void {}
}

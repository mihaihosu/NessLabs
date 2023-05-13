import { Injectable } from '@angular/core';
import { PleaseLoginDialogComponent } from '../shared/components/please-login-dialog/please-login-dialog.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root',
})
export class DialogPleaseLoginService {
  constructor(private dialog: MatDialog) {}

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '600px';
    dialogConfig.height = '600px';
    dialogConfig.position = {};

    this.dialog.open(PleaseLoginDialogComponent, dialogConfig);
  }
}

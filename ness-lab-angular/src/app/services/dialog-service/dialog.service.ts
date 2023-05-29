import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ChangesSavedDialogComponent } from 'src/app/shared/components/changes-saved-dialog/changes-saved-dialog/changes-saved-dialog.component';
import { PleaseLoginDialogComponent } from 'src/app/shared/components/please-login-dialog/please-login-dialog.component';
import { DeletingEventDialogComponent } from 'src/app/shared/components/deleting-event-dialog/deleting-event-dialog/deleting-event-dialog.component';
import { DraftSavedDialogComponent } from 'src/app/shared/components/draft-saved-dialog/draft-saved-dialog/draft-saved-dialog.component';
import { EventPostedDialogComponent } from 'src/app/shared/components/event-posted-dialog/event-posted-dialog/event-posted-dialog.component';
import { MyAccountDialogComponent } from 'src/app/shared/components/my-account-dialog/my-account-dialog/my-account-dialog.component';
import { AddNewEventComponent } from 'src/app/add-new-event/add-new-event.component';

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(private dialog: MatDialog) {}

  openPleaseLoginDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(PleaseLoginDialogComponent, dialogConfig);
  }

  openChangesSavedDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(ChangesSavedDialogComponent, dialogConfig);
  }
  openDeletingEventDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(DeletingEventDialogComponent, dialogConfig);
  }
  openDraftSavedDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(DraftSavedDialogComponent, dialogConfig);
  }
  openEventPostedDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(EventPostedDialogComponent, dialogConfig);
  }
  openMyAccountDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '700px';
    dialogConfig.height = '740px';
    dialogConfig.position = {};

    this.dialog.open(MyAccountDialogComponent, dialogConfig);
  }

  openAddNewEvent() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '100%';
    dialogConfig.height = '80%';
    dialogConfig.position = {};

    this.dialog.open(AddNewEventComponent, dialogConfig);
  }
}

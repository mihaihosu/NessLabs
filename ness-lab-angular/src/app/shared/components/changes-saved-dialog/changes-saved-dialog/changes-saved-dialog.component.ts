import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-changes-saved-dialog',
  templateUrl: './changes-saved-dialog.component.html',
  styleUrls: ['./changes-saved-dialog.component.scss'],
})
export class ChangesSavedDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<ChangesSavedDialogComponent>) {}

  saveChanges: boolean = false;
  @Input() titluModal: string = 'Changes Saved';
  @Input() loginMessage: string = 'Your changes were saved with success!';

  closeDialog(): void {
    this.dialogRef.close();
  }

  saveChangesClick() {
    this.saveChanges = true;
  }
  ngOnInit(): void {}
}

import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-draft-saved-dialog',
  templateUrl: './draft-saved-dialog.component.html',
  styleUrls: ['./draft-saved-dialog.component.scss'],
})
export class DraftSavedDialogComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<DraftSavedDialogComponent>) {}

  @Input() titluModal: string = 'Draft Saved';
  @Input() loginMessage: string = 'Your event was saved as draft.';

  closeDialog(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {}
}

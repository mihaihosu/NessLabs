import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangesSavedDialogComponent } from './changes-saved-dialog.component';

describe('ChangesSavedDialogComponent', () => {
  let component: ChangesSavedDialogComponent;
  let fixture: ComponentFixture<ChangesSavedDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangesSavedDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangesSavedDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

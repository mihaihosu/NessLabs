import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DraftSavedDialogComponent } from './draft-saved-dialog.component';

describe('DraftSavedDialogComponent', () => {
  let component: DraftSavedDialogComponent;
  let fixture: ComponentFixture<DraftSavedDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DraftSavedDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DraftSavedDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

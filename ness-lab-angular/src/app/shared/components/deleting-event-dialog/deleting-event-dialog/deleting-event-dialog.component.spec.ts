import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletingEventDialogComponent } from './deleting-event-dialog.component';

describe('DeletingEventDialogComponent', () => {
  let component: DeletingEventDialogComponent;
  let fixture: ComponentFixture<DeletingEventDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletingEventDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletingEventDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

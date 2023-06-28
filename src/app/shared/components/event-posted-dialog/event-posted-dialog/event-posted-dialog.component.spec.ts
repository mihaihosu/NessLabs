import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventPostedDialogComponent } from './event-posted-dialog.component';

describe('EventPostedDialogComponent', () => {
  let component: EventPostedDialogComponent;
  let fixture: ComponentFixture<EventPostedDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventPostedDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventPostedDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

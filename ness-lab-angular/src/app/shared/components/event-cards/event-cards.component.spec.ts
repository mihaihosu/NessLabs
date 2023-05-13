import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventCardsComponent } from './event-cards.component';

describe('EventCardsComponent', () => {
  let component: EventCardsComponent;
  let fixture: ComponentFixture<EventCardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventCardsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

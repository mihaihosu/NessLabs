import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PleaseLoginDialogComponent } from './please-login-dialog.component';

describe('PleaseLoginDialogComponent', () => {
  let component: PleaseLoginDialogComponent;
  let fixture: ComponentFixture<PleaseLoginDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PleaseLoginDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PleaseLoginDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

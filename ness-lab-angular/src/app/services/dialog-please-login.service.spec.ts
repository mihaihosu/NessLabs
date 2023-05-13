import { TestBed } from '@angular/core/testing';

import { DialogPleaseLoginService } from './dialog-please-login.service';

describe('DialogPleaseLoginService', () => {
  let service: DialogPleaseLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DialogPleaseLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

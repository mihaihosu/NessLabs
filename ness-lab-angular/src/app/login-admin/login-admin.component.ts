import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.scss'],
})
export class LoginAdminComponent {
  form = new FormGroup({
    name: new FormControl(),
    email: new FormControl(),
  });

  isFormSubmitted: boolean = false;

  submitForm() {
    this.isFormSubmitted = true;
  }
}

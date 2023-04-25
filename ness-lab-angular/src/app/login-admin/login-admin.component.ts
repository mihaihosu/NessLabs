import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.scss'],
})
export class LoginAdminComponent {
  parentInput = 'Initial Value';
  
  form = new FormGroup({
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  isFormSubmitted: boolean = false;

  submitForm() {
    this.isFormSubmitted = true;

    if (this.form.valid) {
      console.log('Valid');
    } else {
      console.log('Invalid');
    }
  }
}

import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})
export class CreateAccountComponent {
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

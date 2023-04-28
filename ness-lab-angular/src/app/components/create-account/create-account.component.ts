import { Component, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FormInputBase } from 'src/app/model/form-input-base.model';
import { FormTextbox } from 'src/app/model/form-textbox.model';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})
export class CreateAccountComponent {
  buttonEnabled: boolean = false;

  myForm: FormInputBase<string | boolean>[] = [
    new FormTextbox({
      key: 'email',
      label: 'Email Address',
      iconSrc: '../assets/icons/mail.svg',
      type: 'email',
      required: true,
    }),

    new FormTextbox({
      key: 'username',
      label: 'Username',
      iconSrc: '../assets/icons/user.svg',
      type: 'text',
      required: true,
    }),

    new FormTextbox({
      key: 'password',
      label: 'Password',
      iconSrc: '../assets/icons/locker password.svg',
      type: 'password',
      required: true,
    }),
  ];

  idk($event) {
    this.buttonEnabled = $event;
  }

  clickHandler() {
    console.log('NICE');
  }
}

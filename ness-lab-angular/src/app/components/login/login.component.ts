import { Component } from '@angular/core';

import { FormInputBase } from '../../model/form-input-base.model';
import { FormTextbox } from '../../model/form-textbox.model';
import { FormDropdown } from '../../model/form-dropdown.model';
import { FormCheckbox } from '../../model/form-checkbox.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  template: `styles: ['button {color:white;}']`,
})
export class LoginComponent {
  buttonEnabled: boolean = false;

  myForm: FormInputBase<string | boolean>[] = [
    new FormTextbox({
      key: 'username',
      iconSrc: '../assets/icons/user.svg',
      label: 'Username',
      type: 'text',
      required: true,
    }),

    new FormTextbox({
      key: 'password',
      iconSrc: '../assets/icons/locker password.svg',
      label: 'Password',
      type: 'password',
      required: true,
    }),
  ];

  setButtonEnabled($event) {
    this.buttonEnabled = $event.buttonEnabled;
  }

  clickHandler() {}
}

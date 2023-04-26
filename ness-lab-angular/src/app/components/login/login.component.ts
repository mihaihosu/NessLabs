import { Component } from '@angular/core';

import { FormInputBase } from '../../model/form-input-base.model';
import { FormTextbox } from '../../model/form-textbox.model';
import { FormDropdown } from '../../model/form-dropdown.model';
import { FormCheckbox } from '../../model/form-checkbox.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  myForm: FormInputBase<string | boolean>[] = [
    new FormTextbox({
      key: 'username',
      label: 'Username',
      type: 'text',
      required: true,
    }),

    new FormTextbox({
      key: 'password',
      label: 'Password',
      type: 'password',
      required: true,
    }),
  ];
  

  clickHandler() {}

  
  
}

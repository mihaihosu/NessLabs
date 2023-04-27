import { Component } from '@angular/core';

import { FormInputBase } from '../../model/form-input-base.model';
import { FormTextbox } from '../../model/form-textbox.model';
import { FormDropdown } from '../../model/form-dropdown.model';
import { FormCheckbox } from '../../model/form-checkbox.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  template:`styles: ['button {color:white;}']`
})
export class LoginComponent {
  myForm: FormInputBase<string | boolean>[] = [
    new FormTextbox({
      key: 'username',
      iconSrc:'../assets/img/user.svg',
      label: 'Username',
      type: 'text',
      required: true,
      
    }),

    new FormTextbox({
      key: 'password',
      iconSrc:'../assets/img/locker password.svg',
      label: 'Password',
      type: 'password',
      required: true,
    }),
  ];
  

  clickHandler() {}

  
  
}

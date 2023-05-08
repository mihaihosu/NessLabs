import { Component,Input  } from '@angular/core';

import { FormInputBase } from '../../model/form-input-base.model';
import { FormTextbox } from '../../model/form-textbox.model';
import { FormDropdown } from '../../model/form-dropdown.model';
import { FormCheckbox } from '../../model/form-checkbox.model';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  template: `styles: ['button {color:white;}']`,
})
export class LoginComponent {
  buttonEnabled: boolean = false;
  loginForm: FormGroup;
  password:string;
  showPassword = false;

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }
  
  setButtonEnabled($event) {
    this.buttonEnabled = $event.buttonEnabled;
  }
 
  clickHandler() {}
}

import { Component, Input, ViewChild, ElementRef } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FormInputBase } from 'src/app/model/form-input-base.model';
import { FormTextbox } from 'src/app/model/form-textbox.model';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})
export class CreateAccountComponent {
  @ViewChild('passwordInput') passwordInput: ElementRef;

  buttonEnabled: boolean = false;

  passwordRequirementBulletCheck: {} = {
    0: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    1: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    2: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    3: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
  };

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

  setInputEvents($event) {
    for (let key in this.passwordRequirementBulletCheck) {
      if (this.passwordRequirementBulletCheck[key][1] !== true) {
        this.buttonEnabled = false;
        break;
      }

      if (key === '3') {
        this.buttonEnabled = $event.buttonEnabled;
      }
    }

    this.passwordRequirementBulletCheck = $event.passwordRequirementBulletCheck;
  }

  clickHandler() {
    console.log('NICE');
  }

  onPasswordInput() {
    console.log('WHAT');
  }
}

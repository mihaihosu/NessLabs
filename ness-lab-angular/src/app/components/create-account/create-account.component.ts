import { Component, Input, ViewChild, ElementRef } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { FormInputBase } from 'src/app/model/form-input-base.model';
import { FormTextbox } from 'src/app/model/form-textbox.model';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss'],
})
export class CreateAccountComponent {
  constructor(private fb: FormBuilder) {
    this.createAccountForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: [
        '',
        [Validators.required, Validators.pattern(/^[a-zA-Z]{4,30}$/)],
      ],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  @ViewChild('passwordInput') passwordInput: ElementRef;

  buttonEnabled: boolean = false;

  createAccountForm: FormGroup;

  conditionPasswordRegexes: boolean = false;

  requirements = [
    { regex: /.{8,}/, name: 'length' },
    { regex: /[a-z]/, name: 'lower' },
    { regex: /[A-Z]/, name: 'upper' },
    { regex: /[!-/:-@[-`{-~0-9]/, name: 'symbol' },
  ];

  passwordRequirementBulletCheck: {} = {
    0: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    1: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    2: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    3: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
  };
  password: string;
  showPassword = false;

  showPasswordPath = {
    show: '../assets/icons/show password.svg',
    hide: '../assets/icons/hide password.svg',
  };

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

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

  buttonEnabledConditions() {
    const emailControl = this.createAccountForm.get('email');
    const emailValue = emailControl.value;

    const usernameControl = this.createAccountForm.get('username');
    const usernameValue = usernameControl.value;

    const passwordControl = this.createAccountForm.get('password');
    const passwordValue = passwordControl.value;

    if (
      this.passwordRequirementBulletCheck[0][1] &&
      this.passwordRequirementBulletCheck[1][1] &&
      this.passwordRequirementBulletCheck[2][1] &&
      this.passwordRequirementBulletCheck[3][1]
    ) {
      this.conditionPasswordRegexes = true;
    } else {
      this.conditionPasswordRegexes = false;
      this.buttonEnabled = false;
    }

    if (
      passwordValue !== '' &&
      emailValue !== '' &&
      usernameValue !== '' &&
      this.conditionPasswordRegexes &&
      emailControl.valid &&
      usernameControl.valid
    ) {
      this.buttonEnabled = true;
    } else {
      this.buttonEnabled = false;
    }
  }

  hasInputChanged(inputName) {
    if (inputName !== 'password') {
      return;
    }

    const passwordControl = this.createAccountForm.get('password');
    const password = passwordControl.value;

    this.updatePasswordRequirements(password);

    this.buttonEnabledConditions();
  }

  updatePasswordRequirements(password) {
    for (const requirement of this.requirements) {
      const isRequirementMet = requirement.regex.test(password);

      switch (requirement.name) {
        case 'length':
          this.updatePasswordRequirementBullet(0, isRequirementMet);
          break;
        case 'lower':
          this.updatePasswordRequirementBullet(1, isRequirementMet);
          break;
        case 'upper':
          this.updatePasswordRequirementBullet(2, isRequirementMet);
          break;
        case 'symbol':
          this.updatePasswordRequirementBullet(3, isRequirementMet);
          break;
      }
    }
  }

  updatePasswordRequirementBullet(index, isRequirementMet) {
    if (isRequirementMet) {
      this.passwordRequirementBulletCheck[index][0] =
        '../assets/icons/passwordRequirementsChecked.svg';
    } else {
      this.passwordRequirementBulletCheck[index][0] =
        '../assets/icons/passwordRequirementsUnchecked.svg';
    }

    this.passwordRequirementBulletCheck[index][1] = isRequirementMet;
  }
}

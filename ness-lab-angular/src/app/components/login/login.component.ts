import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth-services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  template: `styles: ['button {color:white;}']`,
})
export class LoginComponent {
  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  buttonEnabled: boolean = false;
  loginForm: FormGroup;
  password: string = '';
  email: string = '';
  showPassword = false;

  showPasswordPath = {
    show: '../assets/icons/show password.svg',
    hide: '../assets/icons/hide password.svg',
  };

  isPasswordEmpty: boolean = true;
  isEmailEmpty: boolean = true;

  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  setButtonEnabled($event) {
    this.buttonEnabled = $event.buttonEnabled;
  }

  onSubmit() {
    if (this.authService.login(this.email, this.password)) {
      console.log('Autentificare reusita!');
    } else {
      console.log('Authentificare esuata!');
    }
  }

  canSetButtonEnabled() {
    if (
      !this.loginForm.get('email').hasError('email') &&
      !this.loginForm.get('password').hasError('minlength')
    ) {
      if (!this.isEmailEmpty && !this.isPasswordEmpty) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  hasInputChanged(inputName) {
    if (inputName === 'password') {
      if (this.password === '') {
        this.isPasswordEmpty = true;
      } else {
        this.isPasswordEmpty = false;
      }
    } else if (inputName === 'email') {
      if (this.email === '') {
        this.isEmailEmpty = true;
      } else {
        this.isEmailEmpty = false;
      }
    }

    this.buttonEnabled = this.canSetButtonEnabled();
  }

  clickHandler() {}
}

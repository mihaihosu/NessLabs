import { Component, OnInit } from '@angular/core';
import { FormInputBase } from 'src/app/model/form-input-base.model';
import { FormTextbox } from 'src/app/model/form-textbox.model';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.scss']
})
export class CreateAccountComponent {
  myForm: FormInputBase<string | boolean>[] = [
    
    new FormTextbox({
      key: 'email',
      label: 'Email Address',
      type: 'email',
      required: true,
    }),

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
  clickHandler() {
    
  }
}

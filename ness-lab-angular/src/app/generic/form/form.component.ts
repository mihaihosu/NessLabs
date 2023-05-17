import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormInputBase } from '../../model/form-input-base.model';

interface eventsEmitted {
  passwordRequirementBulletCheck: {};
  buttonEnabled: boolean;
}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
})
export class FormComponent implements OnInit {
  @Input() formFields: FormInputBase<string | boolean>[] | null = [];

  requirements = [
    { regex: /.{8,}/ },
    { regex: /[a-z]/ },
    { regex: /[A-Z]/ },
    { regex: /[!-/:-@[-`{-~0-9]/ },
  ];

  passwordRequirementBulletCheck: {} = {
    0: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    1: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    2: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
    3: ['../assets/icons/passwordRequirementsUnchecked.svg', false],
  };

  form: FormGroup;
  buttonEnabled: boolean = false;

  inputEvents: eventsEmitted = {
    buttonEnabled: this.buttonEnabled,
    passwordRequirementBulletCheck: this.passwordRequirementBulletCheck,
  };

  @Output() inputEventsEmitter = new EventEmitter();

  ngOnInit(): void {
    this.toFormGroup();
  }

  // Returns whether the form exists and has been modified or not as a helper for the confirmation dialog
  hasFormUnsavedChanges(): boolean {
    return this.form && this.form.dirty;
  }

  onSubmit(): void {
    this.form.markAllAsTouched();

    if (this.form.invalid) return;
    alert('Submit form!');
  }

  private toFormGroup(): void {
    const group = {};

    this.formFields.forEach((field) => {
      group[field.key] = field.required
        ? new FormControl(field.value || '', [
            ...field.validators,
            Validators.required,
          ])
        : new FormControl(field.value || '', field.validators);
    });
    this.form = new FormGroup(group);
  }

  handlePasswordRequirements(control) {
    for (let i = 0; i < this.requirements.length; i++) {
      if (control.value.match(this.requirements[i].regex)) {
        this.passwordRequirementBulletCheck[i][0] =
          '../assets/icons/passwordRequirementsChecked.svg';

        this.passwordRequirementBulletCheck[i][1] = true;

        this.inputEvents.buttonEnabled = this.buttonEnabled;
        this.inputEvents.passwordRequirementBulletCheck =
          this.passwordRequirementBulletCheck;

        this.inputEventsEmitter.emit(this.inputEvents);
      } else {
        this.passwordRequirementBulletCheck[i][0] =
          '../assets/icons/passwordRequirementsUnchecked.svg';

        this.passwordRequirementBulletCheck[i][1] = false;

        this.inputEvents.buttonEnabled = this.buttonEnabled;
        this.inputEvents.passwordRequirementBulletCheck =
          this.passwordRequirementBulletCheck;

        this.inputEventsEmitter.emit(this.inputEvents);
      }
    }
  }

  inputModified() {
    this.buttonEnabled = true;

    this.formFields.forEach((f) => {
      const control = this.form.get(f.key);

      if (!control || !control.value) {
        this.buttonEnabled = false;
      }

      this.handlePasswordRequirements(control);
    });

    this.inputEvents.buttonEnabled = this.buttonEnabled;
    this.inputEvents.passwordRequirementBulletCheck =
      this.passwordRequirementBulletCheck;

    this.inputEventsEmitter.emit(this.inputEvents);
  }
}

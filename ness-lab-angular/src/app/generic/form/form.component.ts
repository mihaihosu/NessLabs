import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormInputBase } from '../../model/form-input-base.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
})
export class FormComponent implements OnInit {
  @Input() formFields: FormInputBase<string | boolean>[] | null = [];

  form: FormGroup;
  buttonEnabled: boolean = false;

  @Output() w = new EventEmitter<boolean>();

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

  inputModified() {
    this.formFields.forEach((f) => {
      const control = this.form.get(f.key);

      this.buttonEnabled = true;
      console.log(control);
      if (!control || !control.value) {
        this.buttonEnabled = false;
        this.w.emit(this.buttonEnabled);
        return;
      }
      //this.w.emit(this.buttonEnabled);
    });

    if (this.buttonEnabled) {
      console.log('All form fields have a value');
    } else {
      console.log('Not all form fields have a value');
    }

    this.w.emit(this.buttonEnabled);
  }
}

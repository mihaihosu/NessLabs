import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormInputBase } from '../../model/form-input-base.model';
@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent{
 
  @Input() field: FormInputBase<string | boolean>;
  @Input() form: FormGroup;

  hasFieldError(): boolean {
    return (
      this.form.get(this.field.key).invalid &&
      (this.form.get(this.field.key).dirty ||
        this.form.get(this.field.key).touched)
    );
  }
}



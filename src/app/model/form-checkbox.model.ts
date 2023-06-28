import { FormInputBase } from './form-input-base.model';

export class FormCheckbox extends FormInputBase<string> {
  override controlType = 'checkbox';
}

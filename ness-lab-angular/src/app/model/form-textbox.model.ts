import { FormInputBase } from './form-input-base.model';

export class FormTextbox extends FormInputBase<string> {
  override controlType = 'textbox';
}
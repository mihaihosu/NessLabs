import { FormInputBase } from './form-input-base.model';

export class FormDropdown extends FormInputBase<string> {
  override controlType = 'dropdown';
}

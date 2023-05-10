// icon.module
// Third Example - icon module
import { NgModule } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { MaterialModule } from '../../material.module';
import { MatIconRegistry } from '@angular/material/icon';

@NgModule({
  imports: [MaterialModule],
})
export class IconModule {
  private path: string = '../../assets/icons';

  constructor(
    private domSanitizer: DomSanitizer,
    public matIconRegistry: MatIconRegistry
  ) {
    this.matIconRegistry
      .addSvgIcon('home', this.setPath(`${this.path}/home icon - selected.svg`))
      .addSvgIcon('add', this.setPath(`${this.path}/add.svg`))
      .addSvgIcon('user', this.setPath(`${this.path}/user.svg`))
      .addSvgIcon('heart', this.setPath(`${this.path}/heart.svg`))
      .addSvgIcon('search', this.setPath(`${this.path}/search.svg`));
  }

  private setPath(url: string): SafeResourceUrl {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }
}

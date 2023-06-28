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
      .addSvgIcon(
        'home-unselected',
        this.setPath(`${this.path}/home icon - unselected.svg`)
      )
      .addSvgIcon(
        'home-selected',
        this.setPath(`${this.path}/home icon - selected.svg`)
      )
      .addSvgIcon('add', this.setPath(`${this.path}/add.svg`))
      .addSvgIcon('user', this.setPath(`${this.path}/user.svg`))
      .addSvgIcon('heart', this.setPath(`${this.path}/heart.svg`))
      .addSvgIcon('edit', this.setPath(`${this.path}/edit.svg`))
      .addSvgIcon('trash', this.setPath(`${this.path}/trash.svg`))
      .addSvgIcon('my-events', this.setPath(`${this.path}/my events.svg`))
      .addSvgIcon('chevron-down', this.setPath(`${this.path}/chevron-down.svg`))
      .addSvgIcon('info-icon', this.setPath(`${this.path}/info-icon.svg`))
      .addSvgIcon('mail-icon', this.setPath(`${this.path}/mail.svg`))
      .addSvgIcon('chevron-up', this.setPath(`${this.path}/chevron-up.svg`))
      .addSvgIcon('draft', this.setPath(`${this.path}/draft.svg`))
      .addSvgIcon('free_tag', this.setPath(`${this.path}/free_tag.svg`))
      .addSvgIcon('liked', this.setPath(`${this.path}/liked.svg`))
      .addSvgIcon('no_like', this.setPath(`${this.path}/no_like.svg`))
      .addSvgIcon(
        'check-circle-2',
        this.setPath(`${this.path}/check-circle-2.svg`)
      )

      .addSvgIcon('search', this.setPath(`${this.path}/search.svg`))
      .addSvgIcon(
        'calendar-icon',
        this.setPath(`${this.path}/calendar-icon.svg`)
      )

      .addSvgIcon(
        'password-icon',
        this.setPath(`${this.path}/locker password.svg`)
      )
      .addSvgIcon(
        'show-password',
        this.setPath(`${this.path}/show password.svg`)
      )
      .addSvgIcon(
        'hide-password',
        this.setPath(`${this.path}/hide password.svg`)
      );
  }

  private setPath(url: string): SafeResourceUrl {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(url);
  }
}

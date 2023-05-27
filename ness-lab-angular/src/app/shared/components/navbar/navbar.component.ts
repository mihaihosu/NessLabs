import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth-services/auth.service';
import { DialogService } from 'src/app/services/dialog-service/dialog.service';
import { SearchService } from 'src/app/services/search-service/search.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  clickprofile: boolean = false;
  searchCards: string = '';
  isConfirmed: boolean = false;
  isAdmin: boolean = false;

  private isConfirmedSubscription: Subscription;
  private isAdminSubscription: Subscription;

  constructor(
    private searchCardsService: SearchService,
    private dialogService: DialogService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.isConfirmed = this.authService.isConfirm;
    this.isAdmin = this.authService.isAdmin;

    console.log(this.isAdmin);
    console.log(this.isConfirmed);

    this.isConfirmedSubscription = this.authService
      .isConfirmObservable()
      .subscribe((isConfirmed: boolean) => {
        this.isConfirmed = isConfirmed;
      });
    this.isAdminSubscription = this.authService
      .isAdminObservable()
      .subscribe((isAdmin: boolean) => {
        this.isConfirmed = isAdmin;
      });
    console.log(this.isAdmin);
    console.log(this.isConfirmed);
  }

  mouseEnterProfile() {
    this.clickprofile = true;
  }

  mouseLeaveProfile() {
    this.clickprofile = false;
  }

  sendSearchCards() {
    this.searchCardsService.search(this.searchCards);
    console.log(this.searchCards);
  }
  openModal() {
    this.dialogService.openMyAccountDialog();
  }
  logout() {
    this.router.navigate(['login']);
  }
  login() {
    this.router.navigate(['login']);
  }
  register() {
    this.router.navigate(['create-account']);
  }
  ngOnDestroy() {
    this.isConfirmedSubscription.unsubscribe();
    this.isAdminSubscription.unsubscribe();
  }
}

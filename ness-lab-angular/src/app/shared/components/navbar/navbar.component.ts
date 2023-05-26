import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  isAdmin = true;
  constructor(
    private searchCardsService: SearchService,
    private dialogService: DialogService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  isLogin = true;

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
}

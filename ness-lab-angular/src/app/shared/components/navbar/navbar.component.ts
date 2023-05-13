import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  clickprofile: boolean = false;
  searchCards: string = '';
  constructor(private searchCardsService: SearchService) {}

  ngOnInit(): void {}

  clickProfile() {
    this.clickprofile = !this.clickprofile;
  }

  sendSearchCards() {
    this.searchCardsService.search(this.searchCards);
    console.log(this.searchCards);
  }
}

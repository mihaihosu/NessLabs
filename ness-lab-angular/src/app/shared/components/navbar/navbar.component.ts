import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  clickprofile: boolean = false;
  filter!: string;

  constructor(private filterService: SearchService) {}

  applyFilter() {
    this.filterService.setFilter(this.filter);
  }

  ngOnInit(): void {}

  clickProfile() {
    this.clickprofile = !this.clickprofile;
  }
}

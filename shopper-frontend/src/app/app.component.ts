import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  public loggedIn=false;

  isCustomerLoggedIn : boolean = this.authService.isCustomerLoggedIn();
  isAdminLoddedIn : boolean = this.authService.isAdminLoggedIn();

  constructor(private authService:AuthService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      this.isCustomerLoggedIn = this.authService.isCustomerLoggedIn();
      this.isAdminLoddedIn = this.authService.isAdminLoggedIn();
    })
  }

  logoutUser() {
    this.authService.logout();
    this.router.navigateByUrl('login');
  }

  title = 'shopper-frontend';
}

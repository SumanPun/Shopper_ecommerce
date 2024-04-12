import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

const BASIC_URL = "http://localhost:9090/";

const TOKEN = "token";
const USER = "user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private http: HttpClient,
  ) { }

  register(signupRequest:any): Observable<any>  {
    return this.http.post(BASIC_URL+"sign-up/user", signupRequest);
  }

  login(username: string, password: string): any {
    const body = {username, password};
    return this.http.post(BASIC_URL+"authenticate",body);
  }

  // loginUser(token,userId,userRole) {
  //   localStorage.setItem("jwtToken", token);
  //   localStorage.setItem("userId",userId);
  //   localStorage.setItem("userRole", userRole);
  //   return true;
  // }

  saveToken(token: string): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN,token);
  }

  saveUser(user: any): void {
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  getToken(): string {
    return localStorage.getItem(TOKEN);
  }

  getUser(): any {
    return JSON.parse(localStorage.getItem(USER));
  }

  getUserRole(): string {
    const user = this.getUser();
    if(user == null)
      return "";
    return user.role;
  }

  isAdminLoggedIn(): boolean {
    if(this.getToken() === null) 
      return false;
    const role: string = this.getUserRole();
    return role == "ADMIN";
  }

  isCustomerLoggedIn(): boolean {
    if(this.getToken() === null) 
      return false;
    const role: string = this.getUserRole();
    return role == "CUSTOMER";
  }

  getUserId(): string {
    const user = this.getUser();
    if(user == null) 
      return "";
    return user.id;
  }


  isLoggedIn() {
    let token = localStorage.getItem(TOKEN);
    if(token == undefined || token==='' || token==null) {
      return false;
    } else {
      return true;
    }
  }

  logout(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

const BASIC_URL = "http://localhost:9090/"

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

  loginUser(token) {
    localStorage.setItem("jwtToken", token);
    return true;
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if(token == undefined || token==='' || token==null) {
      return false;
    } else {
      return true;
    }
  }

  logout() {
    localStorage.removeItem("token");
    return true;
  }

  getToken() {
    return localStorage.getItem("token");
  }

}

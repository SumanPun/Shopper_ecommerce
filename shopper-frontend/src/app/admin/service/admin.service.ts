import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

const BASIC_URL = "http://localhost:9090/api/admin/"

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient,
    private authService: AuthService,
  ) { }

  addCategory(categoryDto: any): Observable<any> {
    return this.http.post(BASIC_URL + 'category', categoryDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  private createAuthorizationHeader():HttpHeaders {
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + this.authService.getToken()
    );
  }
}

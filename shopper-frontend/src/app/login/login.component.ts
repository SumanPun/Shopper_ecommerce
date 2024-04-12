import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  loginForm!: FormGroup;
  hidePassword = true;

  constructor( private fb: FormBuilder,
    private router: Router,
    private snackBar: MatSnackBar,
    private authService: AuthService
  ){} 

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [null, Validators.required, Validators.email],
      password: [null, Validators.required]
    })
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    const username = this.loginForm.get('email')!.value;
    const password = this.loginForm.get('password')!.value;

    if(username!='' && password!='' && username!=null && password!=null) {
      this.authService.login(username,password).subscribe(
        (response) => {
          console.log(response);
          if(response.userId != null) {
            const user = {
              id: response.userId,
              role: response.userRole
            }
            this.authService.saveUser(user);
            this.authService.saveToken(response.jwtToken);
            if(this.authService.isAdminLoggedIn()) {
              this.router.navigateByUrl('admin/dashboard');
            } else if(this.authService.isCustomerLoggedIn()) {
              this.router.navigateByUrl('customer/dashboard');
            }
            this.snackBar.open("Login successful", "Close", { duration: 5000 });
          } else {
            this.snackBar.open("Invalid credentials", "Close", { duration: 5000, panelClass: "error-snackbar" });
          }
        },
        (error) => {
          this.snackBar.open('Bad Credential', 'ERROR', { duration: 5000 });
        }
      )
    }else{
      console.log("username or password cannot be empty");
    }

  }

}

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
      email: [null, Validators.required],
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
          console.log(response.jwtToken);
          console.log(response.jwtRefreshToken);
          this.authService.loginUser(response.jwtToken);
          this.snackBar.open('Success', 'OK', { duration:5000 });
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

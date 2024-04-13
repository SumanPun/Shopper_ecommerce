import { NgModule } from "@angular/core";
import { CustomerComponent } from "./customer.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { AngularMaterialModule } from "../AngularMaterialModule";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { CustomerRoutingModule } from "./customer-routing.module";


@NgModule({
    declarations: [
      CustomerComponent,
      DashboardComponent
    ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule,
      CustomerRoutingModule,
      AngularMaterialModule
    ]
  })
  export class CustomerModule { }
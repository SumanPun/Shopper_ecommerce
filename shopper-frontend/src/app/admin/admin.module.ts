import { NgModule } from "@angular/core";
import { AdminComponent } from "./admin.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { CustomerRoutingModule } from "../customer/customer-routing.module";
import { AdminRoutingModule } from "./admin-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { AngularMaterialModule } from "../AngularMaterialModule";
import { PostCategoryComponent } from './components/post-category/post-category.component';


@NgModule({
    declarations: [
      AdminComponent,
      DashboardComponent,
      PostCategoryComponent
    ],
    imports: [
      CommonModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule,
      AdminRoutingModule,
      AngularMaterialModule
    ]
  })
  export class AdminModule { }
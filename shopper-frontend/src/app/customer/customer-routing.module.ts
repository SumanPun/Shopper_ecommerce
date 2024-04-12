import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { CustomerComponent } from "./customer.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";

const routes: Routes = [
    { path: 'customer', component: CustomerComponent },
    { path: 'customer/dashboard', component: DashboardComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CustomerRoutingModule { }
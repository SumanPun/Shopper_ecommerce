import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { NgModule } from "@angular/core";
import { DashboardComponent } from "./components/dashboard/dashboard.component";

const routes: Routes = [
    { path: 'admin', component: AdminComponent },
    { path: 'admin/dashboard', component: DashboardComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
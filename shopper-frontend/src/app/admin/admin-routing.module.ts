import { RouterModule, Routes } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { NgModule } from "@angular/core";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { PostCategoryComponent } from "./components/post-category/post-category.component";
import { PostProductComponent } from "./components/post-product/post-product.component";

const routes: Routes = [
    { path: 'admin', component: AdminComponent },
    { path: 'admin/dashboard', component: DashboardComponent},
    { path: 'admin/category', component: PostCategoryComponent},
    { path: 'admin/product', component: PostProductComponent}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../../service/admin.service';

@Component({
  selector: 'app-post-product',
  templateUrl: './post-product.component.html',
  styleUrls: ['./post-product.component.scss']
})
export class PostProductComponent {

  productForm: FormGroup;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  listOfCategories: any [];

  constructor(
    private fb: FormBuilder,
    private route: Router,
    private snackBar: MatSnackBar,
    private adminService: AdminService
  ){}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.previewImage();
  }

  previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  ngOnInit(): void {
    this.productForm = this.fb.group({
      categoryId: [null, [Validators.required]],
      name: [null, [Validators.required]],
      price: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });  
    this.getAllCategories();  
  }

  getAllCategories() {
    this.adminService.getAllCategories().subscribe((res) =>{
      this.listOfCategories = res;
    })
  }
  
  addProduct(): void {
    if(this.productForm.valid) {
      this.adminService.addProduct(this.productForm.value).subscribe((res)=>{

        if(res != null) {
          this.snackBar.open('Product added successfully', 'Close', {
            duration: 5000
          });
          this.route.navigateByUrl('/admin/dashboard');
        }else {
          this.snackBar.open(res.message, 'Close', {
            duration: 5000, panelClass:'error-snackbar'
          });
        }
      })
    }else {
      this.productForm.markAllAsTouched();
    }
  }
}

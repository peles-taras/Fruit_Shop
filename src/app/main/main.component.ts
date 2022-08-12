import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Route, RouterModule } from '@angular/router';
import { Product } from '../product/product';
import { ProductService } from '../product/product.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: []
})
export class MainComponent implements OnInit {

  public products: Product[] = this.getProducts();
  public deleteProduct?: Product;
  public updateProduct?:Product;

  constructor (private productService: ProductService, private activatedRoute: ActivatedRoute){};

  ngOnInit() {
    this.productService.findAllProducts();
    const id = Number(this.activatedRoute.snapshot.paramMap.get('id'))
  }

  public getProducts(): any{
    this.productService.findAllProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
        console.log(this.products);
      },
      (error: HttpErrorResponse) => {
//TODO
console.log(error);
      }
    )
  }

  public onAddProduct(addForm: NgForm): any {
    let button = document.getElementById("add-product-form");
    button!.click();
    this.productService.saveProduct(addForm.value).subscribe(
      (response: Product) => {
        console.log(response);
        this.getProducts();
        document.getElementById("form-close")!.click();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateProduct(product: Product): any {
    this.productService.updateProduct(product).subscribe(
      (response: Product) => {
        console.log(response);
        this.getProducts();
        document.getElementById("form-close-update")!.click();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteProduct(productId: any): any {
    this.productService.deleteProduct(productId).subscribe(
      (response: void) => {
        console.log(response);
        this.getProducts();
        document.getElementById("form-close-delete")!.click();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onOpenModal(product: Product, mode: string): void {
    const container = document.getElementById('product-main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'update') {
      this.updateProduct = product;
      button.setAttribute('data-target', '#updateProductModal');
    }
    if (mode === 'delete') {
      this.deleteProduct = product;
      button.setAttribute('data-target', '#deleteProductModal');
    }
    container!.appendChild(button);
    button.click();
  }
}

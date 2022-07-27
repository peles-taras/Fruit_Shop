import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: []
})
export class MainComponent implements OnInit {

  public products: Product[] = this.getProducts();

  constructor (private productService: ProductService){};

  ngOnInit() {
    this.productService.findAllProducts();
  }

  public getProducts(): any{
    this.productService.findAllProducts().subscribe(
      (response: Product[]) => {
        this.products = response;
        console.log(this.products);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }
}

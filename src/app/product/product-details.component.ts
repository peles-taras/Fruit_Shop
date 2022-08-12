
import { ProductService } from './product.service';
import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

 product: Product | undefined;
  private errorMessage : string | undefined;

  constructor(private productService: ProductService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getProduct(id);
    }
  }


public getProduct(id: number): any{
this.productService.findProductById(id).subscribe({
  next: product => this.product = product,
  error: err => this.errorMessage = err
});
}

}

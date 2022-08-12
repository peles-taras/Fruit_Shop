import { RouterModule } from '@angular/router';
import { SharedModule } from './../shared/shared/shared.module';
import { NgModule } from '@angular/core';
import { MainComponent } from '../main/main.component';
import { ProductDetailsComponent } from './product-details.component';
import { ProductGuard } from './product.guard';



@NgModule({
  declarations: [
    MainComponent,
    ProductDetailsComponent
  ],
  imports: [
    RouterModule.forChild([
      {path: "products/:id",canActivate:[ProductGuard],
       component:ProductDetailsComponent}
    ]),
    SharedModule
  ],
  exports: [
    MainComponent,
    ProductDetailsComponent
  ]
})
export class ProductModule { }

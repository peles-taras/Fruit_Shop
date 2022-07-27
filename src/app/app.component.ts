import { HttpErrorResponse } from '@angular/common/http';
import { Component,OnInit} from '@angular/core';
import { Product } from './product';
import { ProductService } from './product.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: []
})
export class AppComponent implements OnInit{
  title = 'FruitShop';

  ngOnInit() {
  }
}

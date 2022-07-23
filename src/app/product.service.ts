import { Product } from './product';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }


  public saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiServerUrl}/product/save`, product);
  }

  public findProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiServerUrl}/product/find/${id}`);
  }

  public findAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiServerUrl}/product/all`);
  }

  public updateUser(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiServerUrl}/product/update`, product);
  }

  public deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/product/delete/${id}`);
  }




}

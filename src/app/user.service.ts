import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public saveUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiServerUrl}/user/save`, user);
  }

  public findUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiServerUrl}/user/find/${id}`);
  }

  public updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
  }

  public deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/user/delete/${id}`);
  }

}

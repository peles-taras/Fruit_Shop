import { UserService } from './../user/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../user/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

user: User | undefined;
errorMessage: string | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

 public saveUser(addForm: NgForm){
  this.userService.saveUser(addForm.value).subscribe({
    next: user => console.log(user),
    error: error => this.errorMessage= error
 });
  this.user = addForm.value;
  addForm.reset();
 }

}

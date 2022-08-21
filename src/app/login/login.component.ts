import { NgForm } from '@angular/forms';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { UserService } from '../user/user.service';
import { User } from '../user/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

public validateUser : User | undefined;
public user: User | undefined;
errorMessage: string | undefined;

@Output() newItemEvent = new EventEmitter<User>();

  constructor(private userService: UserService) { }

  ngOnInit(): void {

  }

  addNewUser(){
this.newItemEvent.emit(this!.user);
  }

public loginUser(form: NgForm){
  this.validateUser = form.value;
  this.userService.findUserByEmail(this.validateUser!.email).subscribe({
    next: user => { if(this.validateUser?.password  === user.password){
        this.user = user;
        console.log(`Welcome, ${user.firstName} ${user.lastName}!`)
         form.reset();
        }
      },
    error: error => {
      this.errorMessage = error
      console.log(this.errorMessage);
    }
  });

}

}

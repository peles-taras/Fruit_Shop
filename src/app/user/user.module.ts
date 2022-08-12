import { SharedModule } from './../shared/shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    LoginComponent,
    SignUpComponent
  ],
  imports: [
    RouterModule,
    CommonModule,
    SharedModule
    
  ],
  exports:[    
    LoginComponent,
    SignUpComponent]
})
export class UserModule { }

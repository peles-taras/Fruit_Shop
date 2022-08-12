import { SignUpComponent } from './../../sign-up/sign-up.component';
import { LoginComponent } from './../../login/login.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from 'src/app/footer/footer.component';
import { HeaderComponent } from 'src/app/header/header.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: "login", component: LoginComponent},
      {path: "sign-up", component: SignUpComponent}
    ])
  ],
  exports:[
    HeaderComponent,
    FooterComponent,
    FormsModule,
    CommonModule
  ]
})
export class SharedModule { }

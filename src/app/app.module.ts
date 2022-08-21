import { MainComponent } from './main/main.component';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from './shared/shared/shared.module';
import { ProductModule } from './product/product.module';
import { UserModule } from './user/user.module';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {path: "", component: MainComponent, pathMatch: 'full'},
      {path: "**", component: MainComponent, pathMatch: 'full'}]),
    HttpClientModule,SharedModule, UserModule, ProductModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

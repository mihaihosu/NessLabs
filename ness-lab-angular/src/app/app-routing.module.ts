import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationPageComponent } from './authentication-page/authentication-page.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { CreateAccountComponent } from './create-account/create-account.component';

const routes: Routes = [
  {
    path: '',
    component: AuthenticationPageComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginAdminComponent },
      { path: 'create-account', component: CreateAccountComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

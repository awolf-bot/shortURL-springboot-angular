import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LinkExpiredComponent } from './link-expired/link-expired.component';
import { NoShortLinkComponent } from './no-short-link/no-short-link.component';

const routes: Routes = [
    {path:"",component:HomeComponent},
    {path:"noSuchShortLink",component:NoShortLinkComponent},
    {path:"linkExpired", component:LinkExpiredComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

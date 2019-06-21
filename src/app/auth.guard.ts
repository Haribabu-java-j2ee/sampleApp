import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { AppService } from "./app.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor( private router: Router, private appService: AppService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.appService.isLogged()) {
      return true;
    } else {
      //return true;
      if (state.url !== 'null') {
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
      } else {
        this.router.navigate(['/login']);
      }
      return false;
    }
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(childRoute, state);
  }
}

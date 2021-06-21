import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {observable, Observable} from 'rxjs';
import {UserService} from "../services/user.service";
import {HttpRequestService} from "../services/http-request.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private httpServices: HttpRequestService, private router: Router) {
    }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> {
        return this.connected();
    }

    private connected(): Observable<boolean> {
        return new Observable(observer => {
            this.httpServices.isUserConnected().subscribe((res) => {
                observer.next(res);

                if (!res)
                    this.router.navigate(['/']);
            });
        });
    }

}

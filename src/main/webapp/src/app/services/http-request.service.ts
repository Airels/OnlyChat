import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {CookieService} from "ngx-cookie";
import {UserService} from "./user.service";

@Injectable({
providedIn: 'root'
})
export class HttpRequestService {
    private serverURL = "http://localhost:8080";
    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded'
        })
    }

    constructor(private http: HttpClient, private cookieService: CookieService, private connectionService: UserService) {
    }

    public login(username: string, password: string): Observable<any> {
        return this.http.post(this.serverURL + '/login', new HttpParams()
            .set('username', username)
            .set('password', password),
            this.httpOptions)
    }

    public register(username: string, password: string): Observable<any> {
        return this.http.post(this.serverURL + '/register', new HttpParams()
            .set('username', username)
            .set('password', password),
            this.httpOptions);
    }

    public logout(): Observable<any> {
        return this.http.post(this.serverURL + '/disconnect', new HttpParams()
            .set('hash', this.connectionService.getHashCode()),
            this.httpOptions);
    }

    public isUserConnected(): Observable<any> {
        return this.http.post(this.serverURL + "/connected", new HttpParams()
                .set("hash", this.connectionService.getHashCode()),
            this.httpOptions);
    }

    public getMessages(): Observable<any> {
        return this.http.get(this.serverURL + '/messages');
    }

    public postMessage(message: string): Observable<any> {
        return this.http.post(this.serverURL + '/messages', new HttpParams()
            .set("userHash", this.connectionService.getHashCode())
            .set("message", message),
            this.httpOptions);
    }
}

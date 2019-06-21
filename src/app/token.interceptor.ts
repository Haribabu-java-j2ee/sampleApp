import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AppService } from "./app.service";


declare var $: any;

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(private appService: AppService) { }

    httpHandle(request: HttpRequest<any>, next: HttpHandler) {
        return next.handle(request).pipe(
            catchError((error: any) => {
                switch (error.status) {
                    case 401:
                        alert("UnAuthorized");
                        break;

                    case 404:
                        alert("Resource not found");
                        break;

                    case 0:
                    case 500:
                        alert("Server error");
                        break;
                }
                return throwError(error);
            })
        );
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
        //no need to attach token in login request
        if (req.url.indexOf("login") > -1) {
            return this.httpHandle(req, next);
        }

        let token = this.appService.getJwtToken();
        req = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
        return this.httpHandle(req, next);
    }
}

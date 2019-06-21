import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from "rxjs";

import { AppService } from "../app.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginProgress: boolean = false;
  public userInfo: any = {
    uName: "",
    pwd: ""
  };
  
  constructor(private router: Router, private http: HttpClient, private appService: AppService) { }

  ngOnInit() {

  }

  login() {
    this.loginProgress = true;
    let apiUrl = this.appService.getApiUrl() + "login";
    
    //let apiObservable = this.http.post(apiUrl, this.userInfo);
    let apiObservable = of(true);

    apiObservable.subscribe((response:any) => {
      if(response){
        this.appService.setJetToken("etlasdasldkljcjksdklasdfasldasjkdasldk"); //replace this with token from response
        this.router.navigate(["/home"]);
      }
    }, (error) => {
      console.log(error);
    });
  }
}

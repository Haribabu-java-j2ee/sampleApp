import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { of } from "rxjs";

import { AppService } from "../app.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  public responseData: any = {};
  
  constructor(private http: HttpClient, private appService: AppService) { }

  ngOnInit() {
  }

  fetchData(){
    let apiUrl = this.appService.getApiUrl() + "fetch?id=1";
    
    let apiObservable = this.http.get(apiUrl);
    // let apiObservable = of(true);

    apiObservable.subscribe((response:any) => {
      this.responseData = response;
    }, (error) => {
      console.log(error);
    });
  }

  createData(){
    let apiUrl = this.appService.getApiUrl() + "fetch";
    
    //let apiObservable = this.http.post(apiUrl, body);
    let apiObservable = of({});

    apiObservable.subscribe((response:any) => {
      this.responseData = response;
    }, (error) => {
      console.log(error);
    });
  }

  updateData(){
    let apiUrl = this.appService.getApiUrl() + "update";
    
    //let apiObservable = this.http.patch(apiUrl, body);
    let apiObservable = of({});

    apiObservable.subscribe((response:any) => {
      this.responseData = response;
    }, (error) => {
      console.log(error);
    });
  }

  deleteData(){
    let apiUrl = this.appService.getApiUrl() + "delete";
    
    //let apiObservable = this.http.delete(apiUrl);
    let apiObservable = of(true);

    apiObservable.subscribe((response:any) => {
      this.responseData = response;
    }, (error) => {
      console.log(error);
    });
  }
}

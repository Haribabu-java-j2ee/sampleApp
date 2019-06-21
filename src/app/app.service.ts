import { Injectable } from '@angular/core';

import { environment } from "@env/environment";

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private jwtToken: string = "";

  constructor() { }

  setJetToken(jwtToken: string) {
    localStorage.setItem("token", jwtToken);
    this.jwtToken = jwtToken;
  }
  getJwtToken() {
    let jwtToken = localStorage.getItem("token");

    if (this.jwtToken == "" && jwtToken != "") {
      this.setJetToken(jwtToken);
    }

    return this.jwtToken;
  }

  getApiUrl() {
    return environment.apiUrl;
  }

  isLogged() {
    if (localStorage.getItem("token")) {
      return true;
    }
    return false;
  }
}

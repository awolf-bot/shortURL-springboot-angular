import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-link-expired',
  templateUrl: './link-expired.component.html',
  styleUrls: ['./link-expired.component.css']
})
export class LinkExpiredComponent implements OnInit {

  constructor(private _router:Router) { }

  ngOnInit(): void {
  }

  navigateToHome(){
    //console.log("in navigate") for debugging only
    this._router.navigate([""]);
  }

}

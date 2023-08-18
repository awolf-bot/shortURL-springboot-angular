import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-no-short-link',
  templateUrl: './no-short-link.component.html',
  styleUrls: ['./no-short-link.component.css']
})
export class NoShortLinkComponent implements OnInit {

  constructor(private _router:Router) { }

  ngOnInit(): void {
  }

  navigateToHome(){
    //console.log("in navigate") for debugging 
    this._router.navigate([""]);
  }
}

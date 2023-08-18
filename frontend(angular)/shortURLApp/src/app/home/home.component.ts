import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { UrlServiceService } from '../services/url-service.service';
import { IurlDto } from '../utility/iurl-dto';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  public shortURL:string='';
  public searchShortURL:string='';
  public errorMsg:string='';
  public urlForm: FormGroup;
  public searchForm:FormGroup;  
  public linkExpired:boolean = false;
  public isCreated:boolean = false;
  public isSearched:boolean = false;

  constructor(private _urlService:UrlServiceService,private fb: FormBuilder) { 
    this.urlForm = fb.group({
      longURL: ['', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')]]
    })

    this.searchForm = fb.group({
      searchlongURL: [''] 
    })
  }

  ngOnInit(): void {
    
  }

  onSubmit(){
    const urlDto:IurlDto = {
      longURl: this.urlForm.value.longURL
    }
    console.log("in submit function");
    console.log(this.urlForm)
    console.log(urlDto)
    this._urlService.createShortURL(urlDto).subscribe(
      {
        next: response => {
          //console.log(response);
          this.shortURL = response.shortUrl;
          this.isCreated = true;
        },
        error: error => {
          //console.log(error); for debugging
          if(error.status === 400){
            this.errorMsg = "long url can not be blank or empty"
          }
          if(error.status ===409){
            this.errorMsg = "short url already exist for this url. Please visit search area to find the shortURL"
          }
          
        }
      }
      );
  }

  get formControls(){
    return this.urlForm.controls;
  }

  get searchFormControls(){
    return this.searchForm.controls;
  }
  
  // this function will hit backend after 5 min and make the flag isExpired = true  
  startTimer(){
    console.log("time started");
    setTimeout(()=>{
      const urlDto:IurlDto = {
        longURl: this.urlForm.value.longURL
      }
      console.log("5 mins over");
      //console.log(this.shortURL)
      this._urlService.updateURL(urlDto).subscribe(res => console.log(res));
    },300000)

    
  }

  onSubmitSearch(){
    const urlDto:IurlDto = {
      longURl: this.searchForm.value.searchlongURL
    }
    this._urlService.searchURL(urlDto).subscribe(res=>
      {
        //console.log(res);
        this.searchShortURL = res.shortUrl;
        this.isSearched = true;
      })
  }
}

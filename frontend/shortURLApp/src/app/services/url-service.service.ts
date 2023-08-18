import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IShortURLModel } from '../utility/ishort-urlmodel';
import { IurlDto } from '../utility/iurl-dto';

@Injectable({
  providedIn: 'root'
})
export class UrlServiceService {

  private baseURL:string = "http://localhost:8080/";
  
  constructor(private http:HttpClient) { }

  createShortURL(longUrlDto:IurlDto):Observable<IShortURLModel>{
    console.log("in service method")
    console.log(longUrlDto)
    return this.http.post<IShortURLModel>(this.baseURL+"create",longUrlDto);
  }

  searchURL(urlDto:IurlDto):Observable<IShortURLModel>{
    console.log(urlDto);
    return this.http.post<IShortURLModel>(this.baseURL+"search",urlDto);
  }

  updateURL(urlDto:IurlDto):Observable<any>{
    return this.http.put<any>(this.baseURL+"update",urlDto);
  }
}

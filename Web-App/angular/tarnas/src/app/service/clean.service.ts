import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RNAFile } from '../model/RNAFile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CleanService {

  private apiServerUrl: string = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public mergeLines(rnaFile: RNAFile): Observable<RNAFile> {
    return this.http.post<RNAFile>(`${this.apiServerUrl}/clean/merge-db-lines`, rnaFile, { withCredentials: false })
  }

  public removeHeader(rnaFile: RNAFile): Observable<RNAFile> {
    return this.http.post<RNAFile>(`${this.apiServerUrl}/clean/remove-header`, rnaFile, { withCredentials: false })
  }

  public removeLinesStartingWith(rnaFile: RNAFile, character: string): Observable<RNAFile> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("character",character[0]);
    return this.http.post<RNAFile>(`${this.apiServerUrl}/clean/remove-lines-start-with`, rnaFile, {params:queryParams, withCredentials: false })
  }

  public removeLinesContaining(rnaFile: RNAFile, word: string): Observable<RNAFile> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("word",word);
    return this.http.post<RNAFile>(`${this.apiServerUrl}/clean/remove-lines-contains`, rnaFile, {params:queryParams, withCredentials: false})
  }

  public removeWhiteSpaces(rnaFile: RNAFile): Observable<RNAFile> {
    return this.http.post<RNAFile>(`${this.apiServerUrl}/clean/remove-white-spaces`, rnaFile, { withCredentials: false })
  }
}

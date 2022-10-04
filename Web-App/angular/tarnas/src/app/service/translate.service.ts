import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RNAFormat } from 'src/app/model/RNAFormat';
import { RNAFile } from '../model/RNAFile';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  private apiServerUrl: string = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getAvailableTranslations(format: RNAFormat): Observable<RNAFormat[]> {
    return this.http.get<RNAFormat[]>(`${this.apiServerUrl}/translation/available-translations/${format}`, { withCredentials: false })
  }

  public translate(rnaFile: RNAFile, dstFormat: RNAFormat): Observable<RNAFile> {
    return this.http.post<RNAFile>(`${this.apiServerUrl}/translation/translate/${dstFormat}`, rnaFile, { withCredentials: false })
  }

  public checkSyntax(rnaFile: RNAFile): Observable<RNAFile> {
    return this.http.post<RNAFile>(`${this.apiServerUrl}/translation/rnafile-syntax`, rnaFile, { withCredentials: false })
  }
}

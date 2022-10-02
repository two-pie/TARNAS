import { Component } from '@angular/core';
import { RNAFile } from './model/RNAFile';
import { RNAFormat } from './model/RNAFormat';
import { TranslateService } from './service/translate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private translateService:TranslateService) {}

  getAvailableTranslations(){
    this.translateService.getAvailableTranslations(RNAFormat.DB).subscribe(f => console.log(f))
  }

  translate(){
    let s = "(1,3);(2,4)";
    let cont:string[] = [s];
    let rnaFile:RNAFile = {fileName:"pippo.txt",content:cont};
    this.translateService.translate(rnaFile,RNAFormat.DB_NO_SEQUENCE).subscribe(f => console.log(f))
  }
}

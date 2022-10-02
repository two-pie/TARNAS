import { Component } from '@angular/core';
import { RNAFile } from './model/RNAFile';
import { RNAFormat } from './model/RNAFormat';
import { CleanService } from './service/clean.service';
import { TranslateService } from './service/translate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private translateService:TranslateService, private cleanService:CleanService) {}

  getAvailableTranslations(){
    this.translateService.getAvailableTranslations(RNAFormat.DB).subscribe(f => console.log(f))
  }

  translate(){
    let s = ".(((..)..[..).)..].(((.).).[[.{.]}][....{..)..]..}\n..A.B...a.b";
    let lines = s.split("\n")
    let rnaFile:RNAFile = {fileName:"pippo.txt",content:lines,format:RNAFormat.DB_NO_SEQUENCE};
    this.cleanService.mergeLines(rnaFile).subscribe(f => console.log(f))
  }
}

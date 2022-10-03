import { Component, OnInit } from '@angular/core';
import { RNAFile } from './model/RNAFile';
import { RNAFormat } from './model/RNAFormat';
import { CleanService } from './service/clean.service';
import { TranslateService } from './service/translate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  public rnaFile?: RNAFile[]

  constructor(private translateService: TranslateService, private cleanService: CleanService) { }

  ngOnInit(): void {
    this.rnaFile = new Array()
  }


  getAvailableTranslations() {
    //this.translateService.getAvailableTranslations(RNAFormat.DB).subscribe(f => console.log(f))
  }

  translate() {

  }

  async loadFile(event: Event) {
    let target = event.target as HTMLInputElement
    let files = target.files as FileList
    let file = files.item(0)
    let content: string = "";
    await file?.text().then(c => content = c);
    let contentSplitted: string[] = content.split("\n").map(l => l.trim())
    this.translateService.checkSyntax({ fileName: file?.name, content:contentSplitted}).subscribe(r => this.rnaFile?.push(r));
    //this.translateService.getAvailableTranslations({ fileName: file?.name, content:contentSplitted}).subscribe(f => console.log(f))
  }

  preview() {

  }

  delete() {

  }


}

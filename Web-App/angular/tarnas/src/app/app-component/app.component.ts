import { Component, OnInit } from '@angular/core';
import { RNAFile } from '../model/RNAFile';
import { RNAFormat } from '../model/RNAFormat';
import { CleanService } from '../service/clean.service';
import { TranslateService } from '../service/translate.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { firstValueFrom } from 'rxjs';
import { FileSaverService } from 'ngx-filesaver';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private _rnaFiles: RNAFile[]
  private _availableFormats: RNAFormat[]
  private _formClean: FormGroup
  private _formTranslate: FormGroup

  constructor(private translateService: TranslateService, private cleanService: CleanService, private formBuilder: FormBuilder, private _FileSaverService: FileSaverService) {
    this._rnaFiles = new Array()
    this._availableFormats = new Array()
    this._formClean = this.formBuilder.group({
      checkboxRemoveLinesContaining: [false],
      inputRemoveLinesContaining: [""],
      checkboxRemoveLinesStartingWithPrefix: [false],
      inputRemoveLinesStartingWithPrefix: [""],
      checkboxRemoveBlankLines: [false],
      checkboxMergeLines: [false]
    })
    this._formTranslate = this.formBuilder.group({
      checkboxIncludeHeader: new FormControl({ value: false, disabled: true }, Validators.required),
      selectFormat: new FormControl()
    })
  }

  ngOnInit(): void { }


  getAvailableTranslations() {
    this.translateService.getAvailableTranslations(RNAFormat.DB).subscribe(f => console.log(f))
  }

  translate() {
    this.translateService
      .translate(this._rnaFiles.at(0)!, this.formTranslate.get('selectFormat')?.value)
      .subscribe(f => {
        if (this.formTranslate.get('checkboxIncludeHeader')?.value == false) {
          this.cleanService.removeHeader(f).subscribe(f => {
            this.saveFile(f)
            })
        }
        else{
          this.saveFile(f)
        }
      })
  }

  async loadFile(event: Event) {
    let target = event.target as HTMLInputElement
    let files = target.files as FileList
    if (files.length > 0) {
      let file = files.item(0)
      let content: string = "";
      await file?.text().then(c => content = c);
      let contentSplitted: string[] = content.split("\n").map(l => l.trim())
      this.translateService.checkSyntax({ fileName: file!.name, content: contentSplitted }).subscribe
        (r => {
          this.addFileToTable(r)
          this.formTranslate.get('checkboxIncludeHeader')?.enable()
          this.formTranslate.get('selectFormat')?.enable()
        });
    }
  }

  public preview() {
    alert(this._rnaFiles.at(0)?.content.join("\n"))
  }


  public async clean() {
    let rnaFile = this._rnaFiles.at(0)
    if (this._formClean.get('checkboxRemoveLinesContaining')?.value == true) {
      let word = <string>this._formClean.get('inputRemoveLinesContaining')?.value
      rnaFile = await firstValueFrom(this.cleanService.removeLinesContaining(rnaFile!, word))
    }
    if (this._formClean.get('checkboxRemoveLinesStartingWithPrefix')?.value == true) {
      let character = <string>this._formClean.get('inputRemoveLinesStartingWithPrefix')?.value
      rnaFile = await firstValueFrom(this.cleanService.removeLinesStartingWith(rnaFile!, character))
    }
    if (this._formClean.get('checkboxRemoveBlankLines')?.value == true) {
      rnaFile = await firstValueFrom(this.cleanService.removeWhiteSpaces(rnaFile!))
    }
    if (this._formClean.get('checkboxMergeLines')?.value == true) {
      rnaFile = await firstValueFrom(this.cleanService.mergeLines(rnaFile!))
    }
    // save the file
    this.saveFile(rnaFile!)
  }

  public enableTranslate() {
    this._formTranslate.get('buttonTranslate')?.enable()
  }

  public isFileLoaded(): boolean {
    return this._rnaFiles.length != 0
  }

  public canTranslate(): boolean {
    return this._availableFormats.length > 0
  }

  public canClean(): boolean {
    return this._rnaFiles.length > 0
  }

  public canMergeLines(): boolean {
    if (this._rnaFiles.length > 0) {
      return this._rnaFiles.at(0)?.format == RNAFormat.DB || this._rnaFiles.at(0)?.format == RNAFormat.DB_NO_SEQUENCE
    }
    return false
  }

  public reset() {
    this._rnaFiles = new Array()
    this._availableFormats = new Array()
    this._formClean.get('inputRemoveLinesContaining')?.setValue("")
    this._formClean.get('inputRemoveLinesStartingWithPrefix')?.setValue("")
    this._formClean.get('checkboxRemoveLinesContaining')?.setValue(false)
    this._formClean.get('checkboxRemoveLinesStartingWithPrefix')?.setValue(false)
    this._formClean.get('checkboxRemoveBlankLines')?.setValue(false)
    this._formClean.get('checkboxMergeLines')?.setValue(false)
    this._formClean.get('checkboxMergeLines')?.setValue(false)
    this._formTranslate.get('checkboxIncludeHeader')?.setValue(false)
  }

  get formClean() {
    return this._formClean
  }

  get formTranslate() {
    return this._formTranslate
  }

  get rnaFiles() {
    return this._rnaFiles
  }

  get availableFormats() {
    return this._availableFormats
  }


  private scrollToBottom() {
    document.body.scrollTop = document.body.scrollHeight;
    document.documentElement.scrollTop = document.documentElement.scrollHeight;
  }

  private saveFile(rnaFile:RNAFile){
    let contentWithSlash:string[] = new Array();
    contentWithSlash.push(rnaFile.content.join("\n")); 
    window.saveAs(new Blob(contentWithSlash),rnaFile.fileName)
  }

  private addFileToTable(rnaFile: RNAFile) {
    if (this.rnaFiles.length > 0) {
      this.reset()
    }
    this.rnaFiles.push(rnaFile)
    this.translateService.getAvailableTranslations(rnaFile.format!).subscribe(list => {
      this._availableFormats = list
      if (this._availableFormats.length == 0) {
        this._formTranslate.disable()
      }
      else {
        this._formTranslate.get('selectFormat')?.setValue(list.at(0))
      }
      if (this._rnaFiles.at(0)?.format != RNAFormat.DB && this._rnaFiles.at(0)?.format != RNAFormat.DB_NO_SEQUENCE) {
        this._formClean.get('checkboxMergeLines')?.disable()
      }
      else {
        this._formClean.get('checkboxMergeLines')?.enable()
      }
      this.scrollToBottom()
    })
  }
}

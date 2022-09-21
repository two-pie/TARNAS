package it.unicam.cs.twopie.tarnas.model.rnafile;

import it.unicam.cs.twopie.tarnas.model.rnastructure.RNASecondaryStructure;

import java.util.List;

public record RNAFile(String fileName,List<String> header, RNASecondaryStructure structure, RNAFormat format) {
    public String getFileName(){
        return fileName;
    }
}

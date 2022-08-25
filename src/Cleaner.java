public class Cleaner {

    public String mergeDBLines(RNAsecondaryStructure rnasecondaryStructure){
        if(rnasecondaryStructure.format() != FormatType.DB && rnasecondaryStructure.format() != FormatType.DB_NO_SEQUENCE)
            throw new IllegalArgumentException();//todo creare nuova eccezione o lasciare così
        return "";
        //todo
    }

    public String removeLinesStartingWith(char symbol){
        return "";
        //todo
    }

    public String removeLinesContaining(String word){
        return "";
        //todo
    }

    public String removeWhiteSpaces(){
        return "";
        //todo
    }

}

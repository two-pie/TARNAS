package it.unicam.cs.twopie.tarnas.view;

import it.unicam.cs.twopie.tarnas.model.antlr.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureLexer;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureParser;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class HomeController {

    @FXML
    private Button addFile;

    @FXML
    private TableView<RNAFile> filesTable;


    @FXML
    public void initialize() {
        this.addFile.setVisible(true);
        // table
        this.filesTable.setEditable(true);
        var nameColumn = new TableColumn<RNAFile,String>("File Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.filesTable.getColumns().add(nameColumn);
    }

    @FXML
    public void handleAddFile() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            var rnafile = this.readFile(selectedFile.toPath());
            this.filesTable.getItems().add(rnafile);
        }


    }

    private RNAFile readFile(Path path) throws IOException {
        CharStream input = CharStreams.fromPath(path);
        // create a lexer that feeds off of input CharStream
        RNASecondaryStructureLexer lexer = new RNASecondaryStructureLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer
        RNASecondaryStructureParser structureParser = new RNASecondaryStructureParser(tokens);
        // begin parsing at rna rule
        ParseTree tree = structureParser.rna_format();
        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Create the specialised listener for the RNA secondary structure
        RNAFileConstructor constructor = new RNAFileConstructor(path.getFileName().toString());
        // Walk the tree created during the parse, trigger callbacks
        walker.walk(constructor, tree);

        return constructor.getRnaFile();
    }
}

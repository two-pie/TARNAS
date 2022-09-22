package it.unicam.cs.twopie.tarnas.view;

import it.unicam.cs.twopie.App;
import it.unicam.cs.twopie.tarnas.model.antlr.RNAFileConstructor;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureLexer;
import it.unicam.cs.twopie.tarnas.model.antlr.RNASecondaryStructureParser;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.view.utils.TrashCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class HomeController {

    @FXML
    private TableView<RNAFile> filesTable;

    @FXML
    private TableColumn<RNAFile, String> nameColumn;

    @FXML
    private TableColumn<RNAFile, String> formatColumn;

    @FXML
    private TableColumn<RNAFile, RNAFile> actionsColumn;

    @FXML
    private Button addFile;

    @FXML
    public void initialize() {
        // load trash image
        var trashImage = new Image(Objects.requireNonNull(App.class.getResource("/img/trash.png")).toExternalForm(), 18, 18, false, false);
        //change table label
        this.filesTable.setPlaceholder(new Label("No loaded files"));
        // init columns
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));
        this.actionsColumn.setCellValueFactory(rnaFile -> new ReadOnlyObjectWrapper<>(rnaFile.getValue()));
        this.actionsColumn.setCellFactory(column -> new TrashCell(trashImage));
    }

    @FXML
    public void handleAddFile() throws IOException {
        var fileChooser = new FileChooser();
        var selectedFile = fileChooser.showOpenDialog(this.getPrimaryStage());
        if (selectedFile != null) {
            var rnaFile = this.readFile(selectedFile.toPath());
            this.filesTable.getItems().add(rnaFile);
        }
    }

    @FXML
    public void handleAddFolder() throws IOException {
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        if (selectedDirectory != null) {
            var files = Files.walk(selectedDirectory.toPath())
                    .filter(Files::isRegularFile)
                    .toList();
            for (var f : files)
                this.filesTable.getItems().add(this.readFile(f));
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

    private Stage getPrimaryStage() {
        return (Stage) addFile.getScene().getWindow();
    }

}

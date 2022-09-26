package it.unicam.cs.twopie.tarnas.view;

import it.unicam.cs.twopie.App;
import it.unicam.cs.twopie.tarnas.controller.CleanerController;
import it.unicam.cs.twopie.tarnas.controller.IOController;
import it.unicam.cs.twopie.tarnas.controller.TranslatorController;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import it.unicam.cs.twopie.tarnas.view.utils.DeleteCell;
import it.unicam.cs.twopie.tarnas.view.utils.LenCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

public class HomeController {
    private final Logger logger = Logger.getLogger("it.unicam.cs.two.pie.tarnas.view.HomeController");

    private TranslatorController translatorController;

    private IOController ioController;

    private CleanerController cleanerController;

    private RNAFormat selectedFormat;

    @FXML
    private TableView<RNAFile> filesTable;

    @FXML
    private TableColumn<RNAFile, String> nameColumn;

    @FXML
    private TableColumn<RNAFile, String> formatColumn;

    @FXML
    private TableColumn<RNAFile, RNAFile> previewColumn;

    @FXML
    private TableColumn<RNAFile, RNAFile> deleteColumn;

    @FXML
    public MenuButton btnSelectFormatTranslation;

    @FXML
    public MenuItem itmAAS, itmAASNS, itmBPSEQ, itmCT, itmDB, itmDBNS, itmFASTA; // example: "AAS_NO_SEQUENCE" instead "AAS NO SEQUENCE" for enum recognition

    @FXML
    public Button btnTranslateAllLoadedFiles;

    @FXML
    public CheckBox chbxRmLinesContainingWord;

    @FXML
    public CheckBox chbxRmLinesContainingPrefix;

    @FXML
    public CheckBox chbxRmBlankLines;

    @FXML
    public CheckBox chbxMergeLines;


    @FXML
    public CheckBox chbxIncludeHeader;

    @FXML
    public TextField txtfRmLinesContainingWord;

    @FXML
    public TextField txtRmLinesContainingPrefix;

    @FXML
    public Label lblRecognizedFormat;

    @FXML
    public void initialize() {
        logger.info("Initializing...");
        // init controllers
        this.cleanerController = CleanerController.getInstance();
        this.ioController = IOController.getInstance();
        this.translatorController = TranslatorController.getInstance();
        // allow only one character in txtRmLinesContainingPrefix
        txtRmLinesContainingPrefix.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 1) {
                return null;
            } else {
                return change;
            }
        }));
        // load trash image
        var trashImage = new Image(Objects.requireNonNull(App.class.getResource("/img/trash.png")).toExternalForm(), 18, 18, false, false);
        var lenImage = new Image(Objects.requireNonNull(App.class.getResource("/img/lens-icon.jpeg")).toExternalForm(), 18, 18, false, false);
        //change table label
        this.filesTable.setPlaceholder(new Label("No loaded files"));
        // set column values
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));
        this.previewColumn.setCellValueFactory(rnaFile -> new ReadOnlyObjectWrapper<>(rnaFile.getValue()));
        this.deleteColumn.setCellValueFactory(rnaFile -> new ReadOnlyObjectWrapper<>(rnaFile.getValue()));
        // set custom cell
        this.previewColumn.setCellFactory(column -> new LenCell(lenImage));
        this.deleteColumn.setCellFactory(column -> new DeleteCell(trashImage,this.lblRecognizedFormat));
        // add event to select ButtonItem for destination format translation
        this.initSelectEventOnButtonItems();
        this.btnTranslateAllLoadedFiles.setDisable(true);
        logger.info("Initialization done");
    }

    @FXML
    public void handleAddFile() {
        logger.info("AGGIUNGI FILE button clicked");
        try {
            var fileChooser = new FileChooser();
            var selectedFile = fileChooser.showOpenDialog(this.getPrimaryStage());
            if (selectedFile != null) {
                var selectedRNAFile = Path.of(selectedFile.getPath());
                this.addFileToTable(selectedRNAFile);
            }
            logger.info("File added successfully");
        } catch (Exception e) {
            logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void handleAddFolder() {
        logger.info("AGGIUNGI CARTELLA button clicked");
        try {
            var directoryChooser = new DirectoryChooser();
            var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
            if (selectedDirectory != null) {
                var files = Files.walk(selectedDirectory.toPath())
                        .filter(Files::isRegularFile)
                        .toList();
                for (var f : files)
                    this.addFileToTable(f);
            }
            logger.info("Folder added successfully");
        } catch (Exception e) {
            logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void handleClean() {
        logger.info("Pulisci button clicked");
        try {
            var cleanedFiles = this.filesTable.getItems().stream().toList();
            if (this.chbxRmLinesContainingWord.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.removeLinesContaining(f, this.txtfRmLinesContainingWord.getText()))
                        .toList();
            if (this.chbxRmLinesContainingPrefix.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.removeLinesStartingWith(f, this.txtRmLinesContainingPrefix.getText()))
                        .toList();
            if (this.chbxRmBlankLines.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.removeWhiteSpaces(f))
                        .toList();
            if (this.chbxRmBlankLines.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.mergeDBLines(f))
                        .toList();
            this.saveFilesTo(cleanedFiles);
            logger.info("Cleaned all files successfully");
        } catch (Exception e) {
            logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void translateAllLoadedFiles() {
        logger.info("TRADUCI button clicked");
        List<RNAFile> translatedRNAFiles;
        translatedRNAFiles = this.translatorController.translateAllLoadedFiles(this.ioController.getLoadedRNAFiles(), this.selectedFormat);
        try {
            if(!this.chbxIncludeHeader.isSelected())
                translatedRNAFiles = translatedRNAFiles.parallelStream()
                        .map(f ->this.cleanerController.removeHeader(f))
                        .toList();
            this.saveFilesTo(translatedRNAFiles);
            logger.info("Files translated successfully");
        }
        catch (IOException e) {
            logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void resetAll(ActionEvent event) {
        logger.info("RESET button clicked");
        // Reset all data structures
        this.filesTable.getItems().clear();
        // Reset all buttons
        this.btnSelectFormatTranslation.setText("TRANSLATE TO...");
        this.btnTranslateAllLoadedFiles.setDisable(true);
        //reset all checkbox
        this.chbxIncludeHeader.setSelected(false);
        this.chbxRmBlankLines.setSelected(false);
        this.chbxRmLinesContainingPrefix.setSelected(false);
        this.chbxRmLinesContainingWord.setSelected(false);
        this.chbxMergeLines.setSelected(false);
        //reset controller files
        this.ioController.clearAllDataStructures();
        //reset recognized format
        this.lblRecognizedFormat.setText("");
        this.lblRecognizedFormat.setVisible(false);
        logger.info("Reset done");
    }

    private Stage getPrimaryStage() {
        return (Stage) this.filesTable.getScene().getWindow();
    }

    private Optional<ButtonType> showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.initOwner(this.getPrimaryStage());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    private void initSelectEventOnButtonItems() {
        this.itmAAS.setId(AAS.toString());
        this.itmAASNS.setId(AAS_NO_SEQUENCE.toString());
        this.itmBPSEQ.setId(BPSEQ.toString());
        this.itmCT.setId(CT.toString());
        this.itmDB.setId(DB.toString());
        this.itmDBNS.setId(DB_NO_SEQUENCE.toString());
        this.itmFASTA.setId(FASTA.toString());
        //EventHandler<ActionEvent> event1 = e -> System.out.println((((MenuItem) e.getSource()).getText() + " selected"));
        EventHandler<ActionEvent> event1 = e -> {
            this.selectedFormat = RNAFormat.valueOf((((MenuItem) e.getSource()).getId()));  // set RNAFormat enum
            //System.out.println("sel: " + selectedFormat);
            this.btnSelectFormatTranslation.setText(String.valueOf((((MenuItem) e.getSource()).getText()))); // set String to display in MenuItem
            this.btnTranslateAllLoadedFiles.setDisable(false);  // when format translation is selected, translate btn is enabled
        };
        this.btnSelectFormatTranslation.getItems().forEach(f -> f.setOnAction(event1));

    }

    private void addFileToTable(Path selectedRNAFile) throws IOException {
        var rnaFile = this.ioController.loadFile(selectedRNAFile);
        var recognizedFormat = this.ioController.getRecognizedFormat();
        if (recognizedFormat != null) {
            var labelText = recognizedFormat.toString();
            this.lblRecognizedFormat.setText("RECOGNIZED FORMAT: " + recognizedFormat.getName());
            this.lblRecognizedFormat.setVisible(true);
        }
        this.filesTable.getItems().add(rnaFile);
    }

    private void saveFilesTo(List<RNAFile> rnaFiles) throws IOException {
        this.showAlert(Alert.AlertType.INFORMATION, "", "", "Choose the directory where to save the files");
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        if (selectedDirectory != null) {
            this.ioController.saveFilesTo(rnaFiles, selectedDirectory.toPath());
            this.showAlert(Alert.AlertType.INFORMATION,
                    "",
                    "Files saved successfully",
                    rnaFiles.size() + " files saved in: " + selectedDirectory.getPath());
        }
    }
}

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
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

public class HomeController {
    private final Logger logger = Logger.getLogger("it.unicam.cs.two.pie.tarnas.view.HomeController");
    private TranslatorController translatorController;

    private IOController ioController;

    private CleanerController cleanerController;

    private RNAFormat selectedFormat;

    @FXML
    private Pane paneTranslator;

    @FXML
    private Pane paneCleaner;

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
    public Button btnTranslate;

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
    public CheckBox chkbxSaveAsZIP;

    @FXML
    public TextField lblArchiveName;

    /*@FXML
    public Button btnCancelWriteContent;

    @FXML
    public Button btnSaveWroteContent;

    @FXML
    public TextArea txtAreaWriteContent;

    @FXML
    public Button btnWriteContent; TODO*/

    @FXML
    public void initialize() {
        this.logger.info("Initializing...");
        // init controllers
        this.cleanerController = CleanerController.getInstance();
        this.ioController = IOController.getInstance();
        this.translatorController = TranslatorController.getInstance();
        // allow only one character in txtRmLinesContainingPrefix
        this.txtRmLinesContainingPrefix.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
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
        this.deleteColumn.setCellFactory(column -> new DeleteCell(trashImage, this.paneTranslator, this.paneCleaner));
        // add event to select ButtonItem for destination format translation
        this.initSelectEventOnButtonItems();
        this.logger.info("Initialization done");
    }

    @FXML
    public void handleAddFile() {
        this.logger.info("AGGIUNGI FILE button clicked");
        var fileChooser = new FileChooser();
        var selectedFile = fileChooser.showOpenDialog(this.getPrimaryStage());
        if (selectedFile != null) {
            var selectedRNAFile = Path.of(selectedFile.getPath());
            this.addFileToTable(selectedRNAFile);
        }
        this.logger.info("File added successfully");
    }

    @FXML
    public void handleAddFolder() {
        this.logger.info("AGGIUNGI CARTELLA button clicked");
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        if (selectedDirectory != null) {
            try {
                var files = Files.walk(selectedDirectory.toPath())
                        .filter(Files::isRegularFile)
                        .toList();
                files.forEach(this::addFileToTable);
            } catch (Exception e) {
                this.logger.severe(e.getMessage());
                this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
            }
        }
        this.logger.info("Folder added successfully");
    }

    @FXML
    public void handleClean() {
        this.logger.info("Pulisci button clicked");
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
            this.logger.info("Cleaned all files successfully");
        } catch (Exception e) {
            this.logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void handleTranslate() {
        this.logger.info("TRADUCI button clicked");
        List<RNAFile> translatedRNAFiles;
        translatedRNAFiles = this.translatorController.translateAllLoadedFiles(this.ioController.getLoadedRNAFiles(), this.selectedFormat);
        try {
            if (!this.chbxIncludeHeader.isSelected())
                translatedRNAFiles = translatedRNAFiles.parallelStream()
                        .map(f -> this.cleanerController.removeHeader(f))
                        .toList();
            this.saveFilesTo(translatedRNAFiles);
            this.logger.info("Files translated successfully");
        } catch (IOException e) {
            this.logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void handleReset(ActionEvent event) {
        this.logger.info("RESET button clicked");
        // Reset all data structures
        this.filesTable.getItems().clear();
        //reset controller files
        this.ioController.clearAllDataStructures();
        this.logger.info("Reset done");
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

    private void initSelectEventOnButtonItems() { // TODO: fare in modo che quando viene riconosciuto un formato, si vedono solo i formati traducibili nel MenuItem
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
            this.btnTranslate.setDisable(false);  // when format translation is selected, translate btn is enabled
        };
        this.btnSelectFormatTranslation.getItems().forEach(f -> f.setOnAction(event1));

    }

    private void addFileToTable(Path selectedRNAFile) {
        try {
            var rnaFile = this.ioController.loadFile(selectedRNAFile);
            this.filesTable.getItems().add(rnaFile);
            this.chbxMergeLines.setDisable(this.ioController.getRecognizedFormat() != DB && this.ioController.getRecognizedFormat() != DB_NO_SEQUENCE);
        } catch (Exception e) {
            this.logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    private void saveFilesTo(List<RNAFile> rnaFiles) throws IOException {
        boolean saveAsZIP = this.chkbxSaveAsZIP.isSelected() && !this.lblArchiveName.getText().isEmpty();
        String filesOrArchive = saveAsZIP ? this.lblArchiveName.getText() + ".zip" : "files";
        this.showAlert(Alert.AlertType.INFORMATION, "", "", "Choose the directory where to save the " + filesOrArchive);
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        if (selectedDirectory != null) {
            // zip options
            if (saveAsZIP) {
                this.ioController.zipFiles(selectedDirectory.toPath(), this.lblArchiveName.getText(), rnaFiles);
                this.showAlert(Alert.AlertType.INFORMATION,
                        "",
                        "Files saved successfully",
                        rnaFiles.size() + " files saved in: " + selectedDirectory.toPath().resolve(this.lblArchiveName.getText() + ".zip"));
            } else { // files options
                this.ioController.saveFilesTo(rnaFiles, selectedDirectory.toPath());
                this.showAlert(Alert.AlertType.INFORMATION,
                        "",
                        "Files saved successfully",
                        rnaFiles.size() + " files saved in: " + selectedDirectory.getPath());
            }
        }
    }

    /**
     * Inits btn write content and hides btn cancel, save and text area.
     */
    /*private void initBtnWriteContent() {
        this.logger.info(this.btnWriteContent.getText() + " visible, other btns are invisible");
        this.btnCancelWriteContent.setVisible(false);
        this.btnSaveWroteContent.setVisible(false);
        this.txtAreaWriteContent.setVisible(false);
    }*/

    /**
     * Action for showing btns cancel, save and text area.
     */
    /*public void handleWriteContent() {
        this.logger.info(this.btnWriteContent.getText() + " button clicked");
        this.btnWriteContent.setVisible(false);
        this.btnCancelWriteContent.setVisible(true);
        this.btnSaveWroteContent.setVisible(true);
        this.txtAreaWriteContent.setVisible(true);
    }*/

    /**
     * Action for cancel write file content.
     */
   /* public void handleCancelWriteContent() {
        this.logger.info(this.btnCancelWriteContent.getText() + " button clicked");
        this.btnWriteContent.setVisible(true);
        this.btnCancelWriteContent.setVisible(false);
        this.btnSaveWroteContent.setVisible(false);
        this.txtAreaWriteContent.clear();
        this.txtAreaWriteContent.setVisible(false);
    }*/

    /**
     * Action for saving file content in the TableView.
     */
    /*public void handleSaveWroteContent() {
        this.logger.info(this.btnSaveWroteContent.getText() + " button clicked");
        TextInputDialog nameFileContent = new TextInputDialog("example.bpseq");
        nameFileContent.setHeaderText("Inserisci il nome del file");
        nameFileContent.initModality(Modality.APPLICATION_MODAL);
        this.btnSaveWroteContent.setOnMouseClicked(e -> {
            try {
                String fileName;
                nameFileContent.showAndWait();
                if (this.txtAreaWriteContent.getText().isEmpty()) {
                    this.showAlert(Alert.AlertType.ERROR, "Error", "", "Il file da salvare non deve essere vuoto");
                    this.logger.severe(this.txtAreaWriteContent.getId() + " is empty");
                } else {
                    fileName = nameFileContent.getEditor().getText();
                    this.logger.info(fileName + " created");
                    File tmp = new File(Path.of(System.getProperty("user.dir")).resolve(fileName).toUri());
                    this.logger.info("write content on " + fileName);
                    Files.write(tmp.toPath(), this.txtAreaWriteContent.getText().getBytes());
                    var selectedRNAFile = Path.of(tmp.getPath());
                    this.addFileToTable(selectedRNAFile);
                    Files.delete(tmp.toPath());
                    this.logger.info(fileName + " deleted");
                    // clear
                    this.txtAreaWriteContent.clear();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }*/
}

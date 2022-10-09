package it.unicam.cs.twopie.tarnas.view;

import it.unicam.cs.twopie.Main;
import it.unicam.cs.twopie.tarnas.controller.CleanerController;
import it.unicam.cs.twopie.tarnas.controller.IOController;
import it.unicam.cs.twopie.tarnas.controller.TranslatorController;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFile;
import it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat;
import it.unicam.cs.twopie.tarnas.view.utils.DeleteCell;
import it.unicam.cs.twopie.tarnas.view.utils.LenCell;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
    public TextField textFieldRmLinesContainingWord;

    @FXML
    public TextField textFieldRmLinesContainingPrefix;

    @FXML
    public CheckBox chbxSaveAsZIP;

    @FXML
    public TextField textFieldArchiveName;

    @FXML
    public BorderPane paneTranslationCleaning;

    @FXML
    public void initialize() throws URISyntaxException {
        this.logger.info("Initializing...");
        //disable cleaning and translation
        this.paneTranslationCleaning.setDisable(true);
        // init controllers
        this.cleanerController = CleanerController.getInstance();
        this.ioController = IOController.getInstance();
        this.translatorController = TranslatorController.getInstance();
        // allow only one character in txtRmLinesContainingPrefix
        this.textFieldRmLinesContainingPrefix.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
            String newText = change.getControlNewText();
            if (newText.length() > 1) {
                return null;
            } else {
                return change;
            }
        }));
        // load trash image
        var trashImage = new Image(Objects.requireNonNull(Main.class.getResource("/img/trash.png")).toExternalForm(), 18, 18, false, false);
        var lenImage = new Image(Objects.requireNonNull(Main.class.getResource("/img/lens-icon.jpeg")).toExternalForm(), 18, 18, false, false);
        //change table label
        this.filesTable.setPlaceholder(new Label("No files loaded"));
        this.filesTable.setId("fileTables");
        // set column values
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.formatColumn.setCellValueFactory(new PropertyValueFactory<>("format"));
        this.previewColumn.setCellValueFactory(rnaFile -> new ReadOnlyObjectWrapper<>(rnaFile.getValue()));
        this.deleteColumn.setCellValueFactory(rnaFile -> new ReadOnlyObjectWrapper<>(rnaFile.getValue()));
        // set custom cell
        this.previewColumn.setCellFactory(column -> new LenCell(lenImage));
        this.deleteColumn.setCellFactory(column -> new DeleteCell(trashImage, this.eventTableEmpty()));
        this.logger.info("Initialization done");
    }

    @FXML
    public void handleAddFile() {
        this.logger.info("ADD FILE button clicked");
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
        this.logger.info("ADD FOLDER button clicked");
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
        this.logger.info("CLEAN button clicked");
        try {
            var cleanedFiles = this.filesTable.getItems().stream().toList();
            if (this.chbxRmLinesContainingWord.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.removeLinesContaining(f, this.textFieldRmLinesContainingWord.getText()))
                        .toList();
            if (this.chbxRmLinesContainingPrefix.isSelected())
                cleanedFiles = cleanedFiles
                        .parallelStream()
                        .map(f -> this.cleanerController.removeLinesStartingWith(f, this.textFieldRmLinesContainingPrefix.getText()))
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
        this.logger.info("TRANSLATE button clicked");
        List<RNAFile> translatedRNAFiles;
        try {
            translatedRNAFiles = this.translatorController.translateAllLoadedFiles(this.ioController.getLoadedRNAFiles(), this.selectedFormat);
            if (!this.chbxIncludeHeader.isSelected())
                translatedRNAFiles = translatedRNAFiles.parallelStream()
                        .map(f -> this.cleanerController.removeHeader(f))
                        .toList();
            this.saveFilesTo(translatedRNAFiles);
        } catch (IOException e) {
            this.logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    @FXML
    public void handleReset() {
        this.logger.info("RESET button clicked");
        // reset all data structures
        this.filesTable.getItems().clear();
        // reset controller files
        this.ioController.clearAllDataStructures();
        // disable translation and cleaning pane
        this.tableEmpty();
        this.logger.info("Reset done");
    }

    @FXML
    public void handleWriteFile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/writeFile.fxml"));
        Parent root = loader.load();
        var stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root, 400, 400));
        var textArea = (TextArea) loader.getNamespace().get("txtAreaRnaFileContent");
        var saveButton = (Button) loader.getNamespace().get(("btnSaveWriteFile"));
        var cancelButton = (Button) loader.getNamespace().get(("btnCancelWriteFile"));
        saveButton.setOnAction(e -> {
            textArea.setEditable(false);
            stage.close();
        });
        cancelButton.setOnAction(e -> stage.close());
        stage.showAndWait();
        if (!textArea.isEditable()) {
            if (textArea.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "", "", "The content of the file cannot be empty");
            } else {
                var dialog = new TextInputDialog("example.ct");
                dialog.setHeaderText("Digit the file name");
                dialog.initModality(Modality.APPLICATION_MODAL);
                String fileName;
                dialog.showAndWait();
                if (dialog.getEditor().getText().isEmpty()) {
                    this.showAlert(Alert.AlertType.ERROR, "Error", "", "The file cannot be empty");
                    this.logger.severe(dialog.getEditor().getId() + " is empty");
                } else {
                    fileName = dialog.getEditor().getText();
                    this.logger.info(fileName + " created");
                    File tmp = new File(Path.of(System.getProperty("user.dir")).resolve(fileName).toUri());
                    this.logger.info("write content on " + fileName);
                    Files.write(tmp.toPath(), textArea.getText().getBytes());
                    var selectedRNAFile = Path.of(tmp.getPath());
                    this.addFileToTable(selectedRNAFile);
                    Files.delete(tmp.toPath());
                    this.logger.info(fileName + " deleted");
                    // clear
                    dialog.getEditor().clear();
                }
            }
        }

    }

    private Stage getPrimaryStage() {
        return (Stage) this.filesTable.getScene().getWindow();
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.initOwner(this.getPrimaryStage());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void initSelectEventOnButtonItems(List<RNAFormat> availableTranslations) {
        this.btnSelectFormatTranslation.getItems().clear();
        availableTranslations.forEach(a -> {
            var item = new MenuItem(a.getName());
            item.setUserData(a);
            this.btnSelectFormatTranslation.getItems().add(item);
            item.setOnAction(e -> {
                this.selectedFormat = (RNAFormat) ((MenuItem) e.getSource()).getUserData();  // set RNAFormat enum
                this.btnSelectFormatTranslation.setText((((MenuItem) e.getSource()).getText())); // set String to display in MenuItem
                this.btnSelectFormatTranslation.setUserData(selectedFormat);
                this.btnTranslate.setDisable(false);
            });
        });
        this.btnTranslate.setDisable(true);
    }

    private void addFileToTable(Path selectedRNAFile) {
        try {
            var rnaFile = this.ioController.loadFile(selectedRNAFile);
            this.filesTable.getItems().add(rnaFile);
            this.paneTranslationCleaning.setDisable(false);
            this.chbxMergeLines.setDisable(this.ioController.getRecognizedFormat() != DB && this.ioController.getRecognizedFormat() != DB_NO_SEQUENCE);
            // add event to select ButtonItem for destination format translation
            this.initSelectEventOnButtonItems(this.translatorController.getAvailableTranslations(rnaFile.getFormat()));
        } catch (Exception e) {
            this.logger.severe(e.getMessage());
            this.showAlert(Alert.AlertType.ERROR, "Error", "", e.getMessage());
        }
    }

    private void saveFilesTo(List<RNAFile> rnaFiles) throws IOException {
        if ((this.chbxSaveAsZIP.isSelected()) && (this.textFieldArchiveName.getText().isEmpty() || this.textFieldArchiveName.getText().isBlank())) {
            showAlert(Alert.AlertType.ERROR, "", "", "Inserire un nome per lo zip!");
            return;
        }
        this.showAlert(Alert.AlertType.INFORMATION, "", "", "Choose the directory where to save the files");
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        if (selectedDirectory != null) {
            // zip options
            if (this.chbxSaveAsZIP.isSelected()) {
                String folderName = this.textFieldArchiveName.getText();
                var zipPath = this.ioController.zipFiles(selectedDirectory.toPath(), folderName, rnaFiles);
                this.showAlert(Alert.AlertType.INFORMATION,
                        "",
                        "Files saved successfully",
                        rnaFiles.size()+" files saved in: " + zipPath);
                this.logger.info(rnaFiles.size()+" files saved in: " + zipPath);
            } else { // files options
                this.ioController.saveFilesTo(rnaFiles, selectedDirectory.toPath());
                this.showAlert(Alert.AlertType.INFORMATION,
                        "",
                        "Files saved successfully",
                        rnaFiles.size() + " files saved in: " + selectedDirectory.getPath());
                this.logger.info(rnaFiles.size() + " files saved in: " + selectedDirectory.getPath());
            }

        }
        else{
            this.logger.info("no files saved");
        }
    }

    private void tableEmpty() {
        // reset checkboxes
        this.chbxMergeLines.setSelected(false);
        this.chbxRmLinesContainingWord.setSelected(false);
        this.chbxRmBlankLines.setSelected(false);
        this.chbxRmLinesContainingPrefix.setSelected(false);
        this.chbxIncludeHeader.setSelected(false);
        this.chbxSaveAsZIP.setSelected(false);
        // reset textAreas
        this.textFieldArchiveName.setText("");
        this.textFieldRmLinesContainingWord.setText("");
        this.textFieldRmLinesContainingPrefix.setText("");
        // reset menu button
        this.btnSelectFormatTranslation.setText("Translate to");
        // reset translate button
        this.btnTranslate.setDisable(true);
        // reset panes
        this.paneTranslationCleaning.setDisable(true);
    }

    private EventHandler<? super MouseEvent> eventTableEmpty() {
        return e -> this.tableEmpty();
    }

}

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

import static it.unicam.cs.twopie.tarnas.model.rnafile.RNAFormat.*;

public class HomeController {

    private TranslatorController translatorController;
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
    public CheckBox firstCheckboxOption;

    @FXML
    public CheckBox secondCheckboxOption;

    @FXML
    public CheckBox thirdCheckboxOption;

    @FXML
    public CheckBox fourthCheckboxOption;

    @FXML
    public TextField firstTextFieldOption;

    @FXML
    public TextField secondTextFieldOption;

    @FXML
    public void initialize() {
        // creates controllers
        this.createsControllers();
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
        this.deleteColumn.setCellFactory(column -> new DeleteCell(trashImage));
        // add event to select ButtonItem for destination format translation
        this.initSelectEventOnButtonItems();
        this.btnTranslateAllLoadedFiles.setDisable(true);
    }

    @FXML
    public void handleAddFile() throws IOException {
        var fileChooser = new FileChooser();
        var selectedFile = fileChooser.showOpenDialog(this.getPrimaryStage());
        if (selectedFile != null) {
            var selectedRNAFile = Path.of(selectedFile.getPath());
            translatorController.loadFile(selectedRNAFile);
            var t = translatorController.getRNAFileOf(selectedRNAFile);
            this.filesTable.getItems().add(t);
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
                this.filesTable.getItems().add(translatorController.getRNAFileOf(Path.of(String.valueOf(f))));
            translatorController.loadDirectory(selectedDirectory.toPath());
        }
    }

    @FXML
    public void handleClean() throws IOException {
        var cleanedFiles = this.filesTable.getItems().stream().toList();
        if (this.firstCheckboxOption.isSelected())
            cleanedFiles = cleanedFiles
                    .parallelStream()
                    .map(f -> this.cleanerController.removeLinesContaining(f, this.firstTextFieldOption.getText()))
                    .toList();
        if (this.secondCheckboxOption.isSelected())
            cleanedFiles = cleanedFiles
                    .parallelStream()
                    .map(f -> this.cleanerController.removeLinesStartingWith(f, this.secondTextFieldOption.getText()))
                    .toList();
        if (this.thirdCheckboxOption.isSelected())
            cleanedFiles = cleanedFiles
                    .parallelStream()
                    .map(f -> this.cleanerController.removeWhiteSpaces(f))
                    .toList();
        if (this.thirdCheckboxOption.isSelected())
            cleanedFiles = cleanedFiles
                    .parallelStream()
                    .map(f -> this.cleanerController.mergeDBLines(f))
                    .toList();
        var directoryChooser = new DirectoryChooser();
        var selectedDirectory = directoryChooser.showDialog(this.getPrimaryStage());
        IOController.saveFilesTo(cleanedFiles, selectedDirectory);
        this.showAlert(Alert.AlertType.INFORMATION,
                "",
                "Files saved successfully",
                cleanedFiles.size() + " files saved in: " + selectedDirectory.getPath());
    }

    @FXML
    public void translateAllLoadedFiles(ActionEvent event) {
        List<RNAFile> formattedRNAFileList = null;
        var result = this.showAlert(Alert.AlertType.CONFIRMATION,
                "TRANSLATION FILES CONFIRM",
                "Translate all loaded files to " + this.selectedFormat + "?",
                "Are you sure you want to translate all loaded files?");

        if (result.isPresent() && result.get() == ButtonType.OK)
            formattedRNAFileList = this.translatorController.translateAllLoadedFiles(this.selectedFormat);
        if (formattedRNAFileList != null) {
            formattedRNAFileList.forEach(f -> {
                try {
                    this.translatorController.saveFile(f, Path.of("C:\\Users\\Piermuz\\Documents\\GitHub\\TARNAS\\src\\main\\java\\it\\unicam\\cs\\twopie\\tarnas\\controller\\" + f.getFileName()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }


    @FXML
    public void resetAll(ActionEvent event) {
        // Reset all data structures
        this.translatorController.resetAll();
        this.filesTable.getItems().clear();
        // Reset all buttons
        this.btnSelectFormatTranslation.setText("TRANSLATE TO...");
        this.btnTranslateAllLoadedFiles.setDisable(true);
        // TODO: insert clean options
    }

    private Stage getPrimaryStage() {
        return (Stage) this.filesTable.getScene().getWindow();
    }

    private Optional<ButtonType> showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert translateConfirmationAlert = new Alert(alertType);
        translateConfirmationAlert.initOwner(this.getPrimaryStage());
        translateConfirmationAlert.initModality(Modality.WINDOW_MODAL);
        translateConfirmationAlert.setTitle(title);
        translateConfirmationAlert.setHeaderText(header);
        translateConfirmationAlert.setContentText(content);
        return translateConfirmationAlert.showAndWait();
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

    private void createsControllers() {
        this.translatorController = new TranslatorController();
        this.cleanerController = new CleanerController();
    }
}

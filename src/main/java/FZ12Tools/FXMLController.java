/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author matsandersson
 */
public class FXMLController implements Initializable {

    FZ12Program fZ12Program = null;
    ToaToolDescription toaToolDescription = null;
    ToolCollection usedTools = null;
    ZollerValues zollerValues = null;

    boolean tableViewHasValues = false;

    @FXML
    private TextArea programTextArea, toaTextArea, zollerTextArea;
    @FXML
    private TableView<Tool> tableView;
    @FXML
    private TableColumn tNoCol, dNoCol, toolNameCol, typeCol, slValueCol, l1ValueCol,
            l2ValueCol, l3ValueCol, rValueCol;
    @FXML
    private TableColumn l1OfsCol, l2OfsCol, l3OfsCol, rOfsCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mpfTab, toaTab, zollerTab;
    @FXML
    private Button eraseTableRowButton;

    @FXML
    private void onOpenToa() {
        toaToolDescription = ToaToolDescription.loadFromFile();
        if (toaToolDescription != null) {
            toaTextArea.setText(toaToolDescription.getToaText());
            tabPane.getSelectionModel().select(toaTab);
        }
    }

    @FXML
    private void onSaveToa() {
        if (toaToolDescription != null) {
            toaToolDescription.saveToFile();
        }
    }

    @FXML
    private void onOpenMpf() {
        fZ12Program = FZ12Program.loadFromFile();
        if (fZ12Program != null) {
            programTextArea.setText(fZ12Program.entireProgram);
            tabPane.getSelectionModel().select(mpfTab);
        }
    }

    @FXML
    private void onOpenZollerFile() {
        zollerValues = ZollerValues.loadFromFile();
        if (zollerValues != null) {
            zollerTextArea.setText(zollerValues.text);
            tabPane.getSelectionModel().select(zollerTab);
        }
    }

    @FXML
    private void onLoadToolsFromToa() {
        if (toaToolDescription != null) {
            usedTools = toaToolDescription.buildToolTreeFromTOA();
            initTableView();
        }
    }

    @FXML
    private void onBuildToolTree() {
        if (fZ12Program != null) {
            usedTools = fZ12Program.buildToolTreeFromMpf();
            initTableView();
        }
    }

    @FXML
    private void onFillInFromZoller() {
        if (zollerValues != null) {
            if (usedTools != null) {
                zollerValues.fillInToolCollectionFromZoller(usedTools);
            }
            tableView.refresh();
        }
    }

    @FXML
    private void onFillInFromToa() {
        if (toaToolDescription != null) {
            if (usedTools != null) {
                toaToolDescription.fillInToolCollectionFromToa(usedTools);
                tableView.refresh();
            }
        }
    }

    @FXML
    private void onBuildToaFromTable() {
        if (tableViewHasValues) {
            toaToolDescription = ToaToolDescription.buildFromToolTable(usedTools);
            toaTextArea.setText(toaToolDescription.getToaText());
        }
    }

    @FXML
    private void onBuildOdsToolList() {
        if (fZ12Program != null && usedTools != null) {
            OdsList.BuildFromProgramAndToolTable(fZ12Program, usedTools);
        }
    }

    @FXML
    private void onOpenSettings() {
        SettingsDialog.showTemplateFileSettingsDialog();
    }

    @FXML
    private void onClose() {
        System.exit(0);
    }

    @FXML
    private void onAbout() {
        Utilities.showAboutBox();
    }

    @FXML
    private void onEraseTableRow() {
        if (tableViewHasValues) {
            Tool tool = tableView.getSelectionModel().getSelectedItem();
            usedTools.removeTool(tool);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eraseTableRowButton.setDisable(true);
    }

    private void initTableView() {
        tNoCol.setCellValueFactory(new PropertyValueFactory<>("tNo"));
        dNoCol.setCellValueFactory(new PropertyValueFactory<>("dNo"));
        toolNameCol.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        toolNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        toolNameCol.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<Tool, String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<Tool, String> event) {
                ((Tool) event.getTableView().getItems().get( 
                        event.getTablePosition().getRow())).setToolName(event.getNewValue());
            }

        }
        );
        typeCol.setCellValueFactory(new PropertyValueFactory<>("toolType"));
        slValueCol.setCellValueFactory(new PropertyValueFactory<>("slValue"));
        l1ValueCol.setCellValueFactory(new PropertyValueFactory<>("l1Value"));
        l2ValueCol.setCellValueFactory(new PropertyValueFactory<>("l2Value"));
        l3ValueCol.setCellValueFactory(new PropertyValueFactory<>("l3Value"));
        rValueCol.setCellValueFactory(new PropertyValueFactory<>("rValue"));
        l1OfsCol.setCellValueFactory(new PropertyValueFactory<>("l1Ofs"));
        l2OfsCol.setCellValueFactory(new PropertyValueFactory<>("l2Ofs"));
        l3OfsCol.setCellValueFactory(new PropertyValueFactory<>("l3Ofs"));
        rOfsCol.setCellValueFactory(new PropertyValueFactory<>("rOfs"));
        if (!usedTools.collection.isEmpty()) {
            tableView.setItems(usedTools.collection);
            tableViewHasValues = true;
        }
        tableView.setEditable(true);
        tableView.getSelectionModel().selectedItemProperty().addListener((obsList, oldSelection, newSelection) -> {
            eraseTableRowButton.setDisable(newSelection == null);
        });
    }

}

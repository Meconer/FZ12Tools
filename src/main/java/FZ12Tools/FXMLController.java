/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.StringConverter;

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
    private TableColumn<Tool, Integer> tNoCol, dNoCol, typeCol, slValueCol;
    @FXML
    private TableColumn<Tool, String> toolNameCol, l1ValueCol,
            l2ValueCol, l3ValueCol, rValueCol, orientationValueCol;
    @FXML
    private TableColumn<Tool, String> l1OfsCol, l2OfsCol, l3OfsCol, rOfsCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mpfTab, toaTab, zollerTab;
    @FXML
    private Button eraseTableRowButton, addTableRowButton;

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
    void draggingOver(DragEvent event) {
        Dragboard board = event.getDragboard();
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    void dropping(DragEvent event) {
        try {
            Dragboard board = event.getDragboard();
            List<File> phil = board.getFiles();
            FileInputStream fis;
            fis = new FileInputStream(phil.get(0));

            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }

            fis.close();

            programTextArea.setText(builder.toString());

        } catch (FileNotFoundException fnfe) {
            Utilities.showAlert("Hittar inte filen vid drop");
        } catch (IOException ioe) {
            Utilities.showAlert("Fel vid inläsning av mpf-fil");
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

    @FXML
    private void onAddTableRow() {
        if (tableViewHasValues) {
            Tool tool = new Tool();
            tool.setTNo(usedTools.getNextFreeToolNo());
            tool.setdNo(1);
            usedTools.addTool(tool);
            Platform.runLater(tableView::refresh);
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
        addTableRowButton.setDisable(true);
    }

    private final StringConverter<Integer> stringIntegerConverter = new StringConverter<Integer>() {
        @Override
        public String toString(Integer intValue) {
            return Integer.toString(intValue);
        }

        @Override
        public Integer fromString(String stringValue) {
            try {
                return Integer.parseInt(stringValue);
            } catch (NumberFormatException ex) {
                Utilities.showAlert("Måste vara ett heltal");
            }
            return -1;
        }
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, Integer>> tNoEditCommitHandler = (TableColumn.CellEditEvent<Tool, Integer> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        int newVal = event.getNewValue();
        if (newVal < 0) {
            newVal = event.getOldValue();
        }
        toolToChange.setTNo(newVal);
        Platform.runLater(tableView::refresh);
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, Integer>> dNoEditCommitHandler = (TableColumn.CellEditEvent<Tool, Integer> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        int newVal = event.getNewValue();
        if (newVal < 0) {
            newVal = event.getOldValue();
        }
        toolToChange.setdNo(newVal);
        Platform.runLater(tableView::refresh);
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, Integer>> typeEditCommitHandler = (TableColumn.CellEditEvent<Tool, Integer> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        int newVal = event.getNewValue();
        if (newVal < 0) {
            newVal = event.getOldValue();
        }
        toolToChange.setToolType(newVal);
        Platform.runLater(tableView::refresh);
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> toolNameEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setToolName(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, Integer>> slValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, Integer> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        int newVal = event.getNewValue();
        if (newVal < 0) {
            newVal = event.getOldValue();
        }
        toolToChange.setSlValue(newVal);
        Platform.runLater(tableView::refresh);
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l1ValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL1Value(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l2ValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL2Value(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l3ValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL3Value(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> rValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setRValue(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> orientationValueEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setOrientationValue(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l1OfsEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL1Ofs(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l2OfsEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL2Ofs(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> l3OfsEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setL3Ofs(event.getNewValue());
    };

    private final EventHandler<TableColumn.CellEditEvent<Tool, String>> rOfsEditCommitHandler = (TableColumn.CellEditEvent<Tool, String> event) -> {
        Tool toolToChange = (Tool) event.getTableView().getItems().get(event.getTablePosition().getRow());
        toolToChange.setROfs(event.getNewValue());
    };

    private void initTableView() {
        tNoCol.setCellValueFactory(new PropertyValueFactory<>("tNo"));
        tNoCol.setCellFactory(TextFieldTableCell.forTableColumn(stringIntegerConverter));
        tNoCol.setOnEditCommit(tNoEditCommitHandler);

        dNoCol.setCellValueFactory(new PropertyValueFactory<>("dNo"));
        dNoCol.setCellFactory(TextFieldTableCell.forTableColumn(stringIntegerConverter));
        dNoCol.setOnEditCommit(dNoEditCommitHandler);

        toolNameCol.setCellValueFactory(new PropertyValueFactory<>("toolName"));
        toolNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        toolNameCol.setOnEditCommit(toolNameEditCommitHandler);

        typeCol.setCellValueFactory(new PropertyValueFactory<>("toolType"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn(stringIntegerConverter));
        typeCol.setOnEditCommit(typeEditCommitHandler);

        slValueCol.setCellValueFactory(new PropertyValueFactory<>("slValue"));
        slValueCol.setCellFactory(TextFieldTableCell.forTableColumn(stringIntegerConverter));
        slValueCol.setOnEditCommit(slValueEditCommitHandler);

        l1ValueCol.setCellValueFactory(new PropertyValueFactory<>("l1Value"));
        l1ValueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l1ValueCol.setOnEditCommit(l1ValueEditCommitHandler);

        l2ValueCol.setCellValueFactory(new PropertyValueFactory<>("l2Value"));
        l2ValueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l3ValueCol.setOnEditCommit(l2ValueEditCommitHandler);

        l3ValueCol.setCellValueFactory(new PropertyValueFactory<>("l3Value"));
        l3ValueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l3ValueCol.setOnEditCommit(l3ValueEditCommitHandler);

        rValueCol.setCellValueFactory(new PropertyValueFactory<>("rValue"));
        rValueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        rValueCol.setOnEditCommit(rValueEditCommitHandler);

        orientationValueCol.setCellValueFactory(new PropertyValueFactory<>("orientationValue"));
        orientationValueCol.setCellFactory(TextFieldTableCell.forTableColumn());
        orientationValueCol.setOnEditCommit(rValueEditCommitHandler);

        l1OfsCol.setCellValueFactory(new PropertyValueFactory<>("l1Ofs"));
        l1OfsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l1OfsCol.setOnEditCommit(l1OfsEditCommitHandler);

        l2OfsCol.setCellValueFactory(new PropertyValueFactory<>("l2Ofs"));
        l2OfsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l2OfsCol.setOnEditCommit(l2OfsEditCommitHandler);

        l3OfsCol.setCellValueFactory(new PropertyValueFactory<>("l3Ofs"));
        l3OfsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        l3OfsCol.setOnEditCommit(l3OfsEditCommitHandler);

        rOfsCol.setCellValueFactory(new PropertyValueFactory<>("rOfs"));
        rOfsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        rOfsCol.setOnEditCommit(rOfsEditCommitHandler);

        if (!usedTools.collection.isEmpty()) {
            tableView.setItems(usedTools.collection);
            tableViewHasValues = true;
        }
        tableView.setEditable(true);

        addTableRowButton.setDisable(!tableViewHasValues);

        tableView.getSelectionModel().selectedItemProperty().addListener((obsList, oldSelection, newSelection) -> {
            eraseTableRowButton.setDisable(newSelection == null);
        });
    }

}

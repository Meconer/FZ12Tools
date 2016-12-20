/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author matsandersson
 */
public class FXMLController implements Initializable {

    FZ12Program fZ12Program = null;
    ToaToolDescription toaToolDescription = null;
    ToolCollection usedTools;
    ZollerValues zollerValues = null;

    @FXML
    private TextArea programTextArea, toaTextArea, zollerTextArea;
    @FXML
    private TableView<Tool> tableView;
    @FXML
    private TableColumn tNoCol, dNoCol, typeCol, slValueCol, l1ValueCol,
            l2ValueCol, l3ValueCol, rValueCol;
    @FXML
    private TableColumn l1OfsCol, l2OfsCol, l3OfsCol, rOfsCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mpfTab, toaTab, zollerTab;

    @FXML
    private void onOpenToa() {
        toaToolDescription = ToaToolDescription.loadFromFile();
        if ( toaToolDescription != null ) {
            toaTextArea.setText(toaToolDescription.getToaText());
            tabPane.getSelectionModel().select(toaTab);
        }
    }

    @FXML
    private void onOpenMpf() {
        fZ12Program = FZ12Program.loadFromFile();
        if ( fZ12Program != null ) {
            programTextArea.setText(fZ12Program.entireProgram);
            tabPane.getSelectionModel().select(mpfTab);
        }
    }

    @FXML
    private void onOpenZollerFile() {
        zollerValues = ZollerValues.loadFromFile();
        if ( zollerValues != null ) {
            zollerTextArea.setText(zollerValues.text);
            tabPane.getSelectionModel().select(zollerTab);
        }
    }

    @FXML
    private void onLoadToolsFromToa() {
        if ( toaToolDescription != null ) {
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
    private void onClose() {
        System.exit(0);
    }

    @FXML
    private void onAbout() {

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Todo
    }

    private void initTableView() {
        tNoCol.setCellValueFactory( new PropertyValueFactory<>("tNo"));
        dNoCol.setCellValueFactory( new PropertyValueFactory<>("dNo"));
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
        tableView.setItems(usedTools.collection);
    }

}

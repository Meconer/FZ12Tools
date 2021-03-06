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

    @FXML
    private TextArea programTextArea, toaTextArea;
    @FXML
    private TableView<Tool> tableView;
    @FXML
    private TableColumn tNoCol, dNoCol, l1ValueCol,
            l2ValueCol, l3ValueCol, rValueCol;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mpfTab, toaTab;

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
        if ( programTextArea != null ) {
            programTextArea.setText(fZ12Program.entireProgram);
            tabPane.getSelectionModel().select(mpfTab);
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Todo
    }

    private void initTableView() {
        
        tNoCol.setCellValueFactory( new PropertyValueFactory<>("tNo"));
        dNoCol.setCellValueFactory( new PropertyValueFactory<>("dNo"));
        l1ValueCol.setCellValueFactory(new PropertyValueFactory<>("l1Value"));
        l2ValueCol.setCellValueFactory(new PropertyValueFactory<>("l2Value"));
        l3ValueCol.setCellValueFactory(new PropertyValueFactory<>("l3Value"));
        rValueCol.setCellValueFactory(new PropertyValueFactory<>("rValue"));
        tableView.setItems(usedTools.collection);
    }

}

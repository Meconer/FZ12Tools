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
    private TextArea programTextArea;
    @FXML
    private TextArea toaTextArea;
    @FXML
    private TableView<Tool> tableView;
    @FXML
    private TableColumn tNoCol;
    @FXML
    private TableColumn dNoCol;
    @FXML
    private TableColumn l1ValueCol;
    @FXML
    private TableColumn l2ValueCol;
    @FXML
    private TableColumn l3ValueCol;
    @FXML
    private TableColumn rValueCol;

    @FXML
    private void onOpenToa() {
        toaToolDescription = ToaToolDescription.loadFromFile();
        toaTextArea.setText(toaToolDescription.getToaText());
    }

    @FXML
    private void onOpenMpf() {
        fZ12Program = FZ12Program.loadFromFile();
        programTextArea.setText(fZ12Program.entireProgram);
    }

    @FXML
    private void onLoadToolsFromToa() {

    }

    @FXML
    private void onBuildToolTree() {
        if (fZ12Program != null) {
            fZ12Program.buildToolTreeFromMpf(usedTools);
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

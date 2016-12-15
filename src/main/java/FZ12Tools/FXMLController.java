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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author matsandersson
 */
public class FXMLController implements Initializable {
    
    FZ12Program fZ12Program = null;
    
    @FXML 
    private TextArea programTextArea;
    
    @FXML
    private void onOpenToa() {
        
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
        // TODO
    }    
    
}

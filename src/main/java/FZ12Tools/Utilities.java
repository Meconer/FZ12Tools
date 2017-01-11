/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.control.Alert;

/**
 *
 * @author matsandersson
 */
class Utilities {

    private static final boolean DEBUG = false;
    
    static File getNcdokDirectory() {
        String meconaHomeDir = FZ12Preferences.getInstance().getDefaultDirectory();
        if ( Files.exists( Paths.get( meconaHomeDir ))) {
            return new File( meconaHomeDir );
        } else {
            return new File( System.getProperty("user.home") + "\\Documents");
        }
    }

    static String removeComment(String s) {
        return s.replaceAll(";.*", "");
    }

    static File getZollerFileDirectory() {
        String toolDir = FZ12Preferences.getInstance().getToolDirectory();
        if ( Files.exists( Paths.get( toolDir ))) {
            return new File( toolDir );
        } else {
            return new File( System.getProperty("user.home") + "\\Documents");
        }
    }
    
    public static void showAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fel!");
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    static void showAboutBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Om");
        alert.setContentText("Om FZ12Tools\nVersion 0.1 2017-01-09");
        alert.showAndWait();
    }

    
}

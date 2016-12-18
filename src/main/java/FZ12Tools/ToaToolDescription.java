/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author Mats Andersson <mats.andersson@mecona.se>
 */
class ToaToolDescription {

    private String toaText;
    private Path currentFilePath;

    public String getToaText() {
        return toaText;
    }

    public Path getCurrentFilePath() {
        return currentFilePath;
    }

    public static ToaToolDescription loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Öppna TOA-fil");
        fileChooser.setInitialDirectory(Utilities.getHomeDirectory());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TOA", "*.toa"),
                new FileChooser.ExtensionFilter("Alla filer", "*.*")
        );

        File toaFile = fileChooser.showOpenDialog(null);
        if (toaFile != null) {
            ToaToolDescription toaToolDescription = new ToaToolDescription();
            toaToolDescription.currentFilePath = Paths.get(toaFile.getAbsolutePath());
            try {
                toaToolDescription.toaText = new String(Files.readAllBytes(toaToolDescription.currentFilePath));
                return toaToolDescription;
            } catch (IOException ex) {
                Logger.getLogger(FZ12Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    ToolCollection buildToolTreeFromTOA() {
        ToolCollection usedTools = new ToolCollection();
        if ( toaText != null && !toaText.isEmpty()) {
            for ( String toaLine : toaText.split("\n")) {
                toaLine = toaLine.replaceAll("\r", "");
                Tool tool = new Tool();
                 
            }
        }
        
        
        return usedTools;
    }

}

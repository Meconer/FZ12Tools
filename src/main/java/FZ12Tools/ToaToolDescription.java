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
        fileChooser.setInitialDirectory(Utilities.getNcdokDirectory());
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

    public static ToaToolDescription buildFromToolTable(ToolCollection usedTools) {
        if (usedTools.collection.isEmpty()) {
            return null;
        }
        ToaToolDescription toaToolDescription = new ToaToolDescription();
        StringBuilder sb = new StringBuilder();
        usedTools.collection.forEach((tool) -> {
            sb.append(tool.getToaLines());
        });
        toaToolDescription.toaText = sb.toString();
        toaToolDescription.currentFilePath = null;
        return toaToolDescription;
    }

    ToolCollection buildToolTreeFromTOA() {
        ToolCollection usedTools = new ToolCollection();
        if (toaText != null && !toaText.isEmpty()) {
            for (String toaLine : toaText.split("\n")) {
                toaLine = toaLine.replace("\r", "");
                toaLine = Utilities.removeComment(toaLine);
                int tNo = Tool.getTNoFromToaLine(toaLine);
                int dNo = Tool.getDNoFromToaLine(toaLine);
                if (tNo > 0 && dNo > 0) {
                    Tool tool;
                    if (usedTools.toolExist(tNo, dNo)) {
                        tool = usedTools.getTool(tNo, dNo);
                    } else {
                        usedTools.addTool(tNo, dNo);
                        tool = usedTools.getTool(tNo, dNo);
                    }
                    tool.addParameterFromToaLine(toaLine);
                }
            }
        }

        return usedTools;
    }

    void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Spara TOA-fil");
        fileChooser.setInitialDirectory(Utilities.getNcdokDirectory());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TOA", "*.toa"),
                new FileChooser.ExtensionFilter("Alla filer", "*.*")
        );

        File toaFile = fileChooser.showSaveDialog(null);
        if (toaFile != null) {
            if (!toaFile.getName().toUpperCase().endsWith(".TOA")) {
                toaFile = new File(toaFile.getPath() + ".TOA");
            }
            currentFilePath = Paths.get(toaFile.getAbsolutePath());
            try {
                Files.write(currentFilePath, toaText.getBytes());
            } catch (IOException ex) {
                Utilities.showAlert("Kan inte spara TOA-filen");
            }
        }
    }

    void fillInToolCollectionFromToa(ToolCollection usedTools) {
        ToolCollection toaTools = buildToolTreeFromTOA();
        for ( Tool tool : usedTools.collection ) {
            Tool toaTool = toaTools.getTool(tool.getTNo(), tool.getDNo());
            if ( toaTool != null ) tool.copyValuesFromTool( toaTool );
        }
    }
}

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
 * @author matsandersson
 */
class ZollerValues {
    String text;
    Path currentFilePath;

    static ZollerValues loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Öppna Zollerfil");
        fileChooser.setInitialDirectory(Utilities.getZollerFileDirectory());

        File zollerToolFile = fileChooser.showOpenDialog(null);
        if (zollerToolFile != null) {
            ZollerValues zollerValues = new ZollerValues();
            zollerValues.currentFilePath = Paths.get(zollerToolFile.getAbsolutePath());
            FZ12Preferences.getInstance().setToolDirectory(zollerValues.currentFilePath.getParent().toString());
            try {
                zollerValues.text = new String(Files.readAllBytes(zollerValues.currentFilePath));
                return zollerValues;
            } catch (IOException ex) {
                Logger.getLogger(FZ12Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void fillInToolCollectionFromZoller(ToolCollection usedTools) {
        for ( String zollerLine : text.split(System.lineSeparator())) {
            Tool zollerTool = Tool.getToolFromZollerLine( Utilities.removeComment(zollerLine) );
            Tool tool = usedTools.getTool(zollerTool.getTNo(), zollerTool.getDNo());
            if ( tool != null ) {
                tool.copyFromZollerValues( zollerTool );
            }
        }
    }

    public ToolCollection createToolCollectionFromZollerFile() {
        ToolCollection usedTools = new ToolCollection();
        for ( String zollerLine : text.split(System.lineSeparator())) {
            Tool zollerTool = Tool.getToolFromZollerLine( Utilities.removeComment(zollerLine) );
            Tool tool = new Tool(zollerTool.getDNo(), zollerTool.getTNo());
            tool.copyFromZollerValues( zollerTool );
            usedTools.addTool(tool);
        }
        return usedTools;
    }
    
}

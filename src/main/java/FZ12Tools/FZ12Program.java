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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 *
 * @author matsandersson
 */
public class FZ12Program {
    String entireProgram = null;
    private ToolCollection usedTools;
    private Path currentFilePath;

    public static FZ12Program loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Öppna FZ12program");
        fileChooser.setInitialDirectory( Utilities.getHomeDirectory() );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MPF", "*.mpf"),
                new FileChooser.ExtensionFilter("Alla filer", "*.*")
        );

        File fz12ProgramFile = fileChooser.showOpenDialog(null);
        if ( fz12ProgramFile != null ) {
            FZ12Program fZ12Program = new FZ12Program();
            fZ12Program.currentFilePath = Paths.get(fz12ProgramFile.getAbsolutePath());
            try {
                fZ12Program.entireProgram = new String(Files.readAllBytes(fZ12Program.currentFilePath));
                return fZ12Program;
            } catch (IOException ex) {
                Logger.getLogger(FZ12Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        fileChooser.setInitialDirectory(Utilities.getHomeDirectory());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MPF", "*.mpf"),
                new FileChooser.ExtensionFilter("Alla filer", "*.*")
        );

        File fz12ProgramFile = fileChooser.showOpenDialog(null);
        if (fz12ProgramFile != null) {
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

    void buildToolTreeFromMpf() {
        usedTools = new ToolCollection();
        String toolRegexp = ".*(T\\d+).*";
        String dNoRegexp = ".*(D\\d+).*";
        Pattern toolPattern = Pattern.compile(toolRegexp);
        Pattern dNoPattern = Pattern.compile(dNoRegexp);

        int currentToolNo = 0;

        // Check each line
        for (String line : entireProgram.split("\n")) {

            line = line.replace("\r", "");
            // Now remove all comments
            line = line.replaceAll(";.*", "");
            // and also remove everything in parenthesis
            line = line.replaceAll("\\(.*?\\)", "");
            // make it in uppercase
            line = line.toUpperCase();

            // Check if the line has a T number. If so, set the current tool number
            Matcher m = toolPattern.matcher(line);
            if (m.matches()) {
                String toolNumber = m.group(1);
                int toolNo = Integer.parseInt( toolNumber.substring(1) );
                currentToolNo = toolNo;
            }

            // now check if the line has a D number. If so, add the current tool
            m = dNoPattern.matcher(line);
            if (m.matches()) {
                String dString = m.group(1);
                int dNo = Integer.parseInt(dString.substring(1));
                if ((currentToolNo != 0) && (dNo != 0)) {
                    usedTools.addTool( currentToolNo, dNo);

                }
            }
        }

        // If the same tool place has more than one d number then we should calculate a
        // new station number for each dNo that tool place has.
        usedTools.toolPrint();
    }

}



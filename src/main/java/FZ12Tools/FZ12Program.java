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
    private Path currentFilePath;

    public Path getCurrentFilePath() {
        return currentFilePath;
    }

    
    public static FZ12Program loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Öppna FZ12program");
        fileChooser.setInitialDirectory(Utilities.getNcdokDirectory());
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

    ToolCollection buildToolTreeFromMpf() {
        ToolCollection usedTools = new ToolCollection();
        String toolRegexp = ".*(T\\d+).*";
        String dNoRegexp = ".*(D\\d+).*";
        String turnOnRegexp = ".*_TURN[V|H]";
        String turnOffRegexp = ".*_TURN_OFF";
        String toolNameRegexp = "^;\\*\\*\\* T(\\d+) D(\\d+) (.+)$";
        Pattern toolPattern = Pattern.compile(toolRegexp);
        Pattern dNoPattern = Pattern.compile(dNoRegexp);
        Pattern turnOnPattern = Pattern.compile(turnOnRegexp);
        Pattern turnOffPattern = Pattern.compile(turnOffRegexp);
        Pattern toolNamePattern = Pattern.compile(toolNameRegexp);

        int currentToolNo = 0;
        boolean isTurning = false;

        String ls = System.lineSeparator();
        // Check each line
        for (String line : entireProgram.split(ls)) {

            // Now remove all comments
            line = Utilities.removeComment(line);
            // and also remove everything in parenthesis
            line = line.replaceAll("\\(.*?\\)", "");
            // make it in uppercase
            line = line.toUpperCase();

            // Check if the line turns on or off turning mode
            Matcher m = turnOnPattern.matcher(line);
            if (m.matches()) {
                isTurning = true;
            }

            m = turnOffPattern.matcher(line);
            if (m.matches()) {
                isTurning = false;
            }

            // Check if the line has a T number. If so, set the current tool number
            m = toolPattern.matcher(line);
            if (m.matches()) {
                String toolNumber = m.group(1);
                int toolNo = Integer.parseInt(toolNumber.substring(1));
                currentToolNo = toolNo;
            }

            // now check if the line has a D number. If so, add the current tool
            m = dNoPattern.matcher(line);
            if (m.matches()) {
                String dString = m.group(1);
                int dNo = Integer.parseInt(dString.substring(1));
                if ((currentToolNo != 0) && (dNo != 0)) {
                    usedTools.addTool(currentToolNo, dNo);
                    Tool tool = usedTools.getTool(currentToolNo, dNo);
                    if (isTurning) {
                        tool.setToolType(510);
                    } else {
                        tool.setToolType(120);
                    }
                    tool.setL1Value("0");
                    tool.setL2Value("0");
                    tool.setL3Value("0");
                    tool.setRValue("0");
                    tool.setSlValue(3);
                }
            }
            
            // Check if this line is a tool name comment. Add the tool name to 
        }

        // If the same tool place has more than one d number then we should calculate a
        // new station number for each dNo that tool place has.
        usedTools.toolPrint();
        return usedTools;
    }

}

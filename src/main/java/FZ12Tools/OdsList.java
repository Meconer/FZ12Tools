/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import javafx.stage.FileChooser;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author Mats Andersson <mats.andersson@mecona.se>
 */
class OdsList {

    static void BuildFromProgramAndToolTable(FZ12Program fZ12Program, ToolCollection usedTools) {
        if (fZ12Program == null || usedTools == null) {
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Skapa ODS-verktygslista");

        Path initialPath = fZ12Program.getCurrentFilePath();
        initialPath = initialPath.getParent();
        fileChooser.setInitialDirectory(initialPath.toFile());

        fileChooser.setInitialFileName("VLIST.ODS");
        File odsFile = fileChooser.showSaveDialog(null);

        if (odsFile != null) {
            saveOdsFile(odsFile, usedTools);
        }

    }

    private static void saveOdsFile(File odsFile, ToolCollection usedTools) {
        try {
            Sheet sheet = SpreadSheet.createFromFile(Utilities.getTemplatePath().toFile()).getFirstSheet();

            // Lägg in datum högst upp till höger
            sheet.getCellAt(7, 0).setValue(new Date());

            // Lägg in kund, benämning och artikelnummer i kolumn 0 och raderna 3,4 och 5
            // Först kund
            Path path = odsFile.getAbsoluteFile().toPath();
            int level = path.getNameCount();
            String customerName;
            if ( level > 5 ) customerName = path.getName(level - 5).toString();
            else customerName = "Kund";
            sheet.getCellAt(5, 3).setValue(customerName);

            // och så artikelnummer och benämning. Artikelnummer är det först "ordet" i pathnivå level - 4
            String pathLevelm4;
            if ( level > 4 ) pathLevelm4 = path.getName(level - 4).toString();
            else pathLevelm4 = "Artikelnummer Pos";
            int posOfFirstSpace = pathLevelm4.indexOf(" ");
            String artNo = "";
            String name;
            if (posOfFirstSpace > 0) {
                artNo = pathLevelm4.substring(0, posOfFirstSpace);
                name = pathLevelm4.substring(posOfFirstSpace + 1, pathLevelm4.length());
            } else {
                name = pathLevelm4;
            }
            sheet.getCellAt(5, 4).setValue(name);
            sheet.getCellAt(5, 5).setValue(artNo);

            int startOdsLine = 15;
            for (Tool tool : usedTools.collection) {
                int currentLine = startOdsLine + tool.getTNo() + tool.getDNo() - 1;
                sheet.getCellAt(0, currentLine).setValue(tool.getTNo());
                sheet.getCellAt(1, currentLine).setValue(tool.getDNo());
                sheet.getCellAt(2, currentLine).setValue(tool.getToolType());
                sheet.getCellAt(5, currentLine).setValue(tool.getL1Value());
                sheet.getCellAt(7, currentLine).setValue(tool.getRValue());
            }
            sheet.getSpreadSheet().saveAs(odsFile);
            OOUtils.open(odsFile);

        } catch (IOException ex) {
            Utilities.showAlert("Kan inte skapa ods-verktygslista");
        }

    }

}

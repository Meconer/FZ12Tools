/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FZ12Tools;

import java.util.Optional;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author Mats Andersson <mats.andersson@mecona.se>
 */
class SettingsDialog {

    public static void showTemplateFileSettingsDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Mall för verktygslista");
        //dialog.setHeaderText("This is a custom dialog. Enter info and \n" +
        //    "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        dialog.setWidth(500);

        Label label1 = new Label("Sökväg till mallfil: ");
        TextField templateFilePathField = new TextField();
        templateFilePathField.setText(FZ12Preferences.getInstance().getDefaultTemplatePath());
        templateFilePathField.setPrefWidth(300);

        GridPane grid = new GridPane();
        grid.add(label1, 1, 1);
        grid.add(templateFilePathField, 2, 1);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter((ButtonType b) -> {
            if (b == buttonTypeOk) {

                return templateFilePathField.getText();
            }

            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            FZ12Preferences.getInstance().setDefaultTemplatePath(result.get());
        }
    }

}

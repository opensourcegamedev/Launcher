package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

/**
 * Shows different menus in the settings menu.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class SettingsController {

    @FXML private VBox root;

    @FXML private void showVersions(){
        VBox superRoot = (VBox)root.getParent();
        superRoot.getChildren().remove(1);
        try {
            superRoot.getChildren().add(1, FXMLLoader.load(new URL("file:data/fxml/versionsMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void chooseLanguage(){
        ChooseLanguage languageChooser = new ChooseLanguage();
        languageChooser.activate();
    }
}

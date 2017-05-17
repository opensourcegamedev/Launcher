package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import spacechaos.launcher.config.LanguageController;
import spacechaos.launcher.config.Settings;

/**
 * Controller for the stage in which you choose which language the launcher should have.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class ChooseLanguageController {

    @FXML private ComboBox<String> languages;

    @FXML private void initialize(){
        languages.getItems().addAll(LanguageController.getSupported());
    }

    @FXML private void choose(){
        if(!languages.getValue().equals("Language")){
            new Settings("config.properties").changeConfiguration("language", languages.getValue());
        }
    }
}

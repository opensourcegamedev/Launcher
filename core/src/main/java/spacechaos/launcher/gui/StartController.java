package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import spacechaos.launcher.Main;

/**
 * Starts game from an executable jar.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class StartController {
    @FXML private Button startButton;
    @FXML private ComboBox versionsBox;

    @FXML private void initialize(){
        startButton.setText(Main.getLangController().getString("start"));
        versionsBox.setPromptText(Main.getLangController().getString("version"));
    }

    @FXML private void startGame(){
        //TODO start game
    }
}

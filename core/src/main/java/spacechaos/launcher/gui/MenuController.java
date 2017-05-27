package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import spacechaos.launcher.Main;

import java.io.IOException;
import java.net.URL;

/**
 * Controller for the menu in the upper half of the Start-Screen.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class MenuController {

    private boolean settingsShown = false;

    @FXML private VBox root;
    @FXML private Button settingsButton;

    @FXML private void initialize() {
        settingsButton.setGraphic(new ImageView("file:data/images/settings.png"));
    }

    @FXML protected void showSettings(){
        if(!settingsShown){
            root.getChildren().remove(1, 4);
            try {
                root.getChildren().add(FXMLLoader.load(new URL("file:data/fxml/settings.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            settingsShown = true;
            settingsButton.setGraphic(null);
            settingsButton.setText(Main.getLangController().getString("back"));
        }else{
            root.getChildren().remove(1);
            try {
                root.getChildren().add(1, FXMLLoader.load(new URL("file:data/fxml/infoMenu.fxml")));
                root.getChildren().add(2, FXMLLoader.load(new URL("file:data/fxml/newsCanvas.fxml")));
                root.getChildren().add(3, FXMLLoader.load(new URL("file:data/fxml/start.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            settingsShown = false;
            settingsButton.setText("");
            settingsButton.setGraphic(new ImageView("file:data/images/settings.png"));
        }
    }
}
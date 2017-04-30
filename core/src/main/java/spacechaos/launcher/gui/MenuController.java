package spacechaos.launcher.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;


public class MenuController {

    @FXML private VBox root;
    @FXML private Button settingsButton;

    @FXML private void initialize() {
        settingsButton.setGraphic(new ImageView("file:data/images/settings.png"));
    }

    @FXML private void settings(){}

    @FXML protected void showCredits(ActionEvent e){
        root.getChildren().remove(2);
        try {
            root.getChildren().add(2, FXMLLoader.load(new URL("file:core/src/main/resources/fxml/credits.fxml")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML protected void showImageSlider(){
        root.getChildren().remove(2);
        try {
            root.getChildren().add(2, FXMLLoader.load(new URL("file:core/src/main/resources/fxml/newsCanvas.fxml")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

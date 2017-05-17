package spacechaos.launcher.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Creates a new stage to choose your language
 *
 * @author Constantin Schulte
 * @version 0.1
 */
class ChooseLanguage {
    ChooseLanguage(){
        Stage chooseStage = new Stage();
        chooseStage.setTitle("LanguageChooser");
        chooseStage.getIcons().add(new Image("file:data/images/logo_square.jpg"));
        try {
            Scene chooseScene = new Scene(FXMLLoader.load(new URL("file:data/fxml/chooseLanguage.fxml")));
            chooseStage.setScene(chooseScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chooseStage.show();
    }
}

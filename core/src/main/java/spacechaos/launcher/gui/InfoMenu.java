package spacechaos.launcher.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

/**
 * Controls the main panel in the start screen.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class InfoMenu {

    @FXML private HBox root;
    private VBox rooter;

    @FXML
    protected void showCredits(){
        rooter = (VBox)root.getParent();
        rooter.getChildren().remove(2);
        try {
            rooter.getChildren().add(2, FXMLLoader.load(new URL("file:data/fxml/credits.fxml")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML protected void showImageSlider(){
        rooter = (VBox)root.getParent();
        rooter.getChildren().remove(2);
        try {
            rooter.getChildren().add(2, FXMLLoader.load(new URL("file:data/fxml/newsCanvas.fxml")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

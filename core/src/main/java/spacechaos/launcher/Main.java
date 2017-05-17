package spacechaos.launcher;

import spacechaos.launcher.config.LanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spacechaos.launcher.config.Settings;

import java.net.URL;

/**
 * Starts launcher and sets UI up with the ImageSlider, the Header and the Start-parts.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Settings setup = new Settings("setup.properties");
        Settings config = new Settings("config.properties");
        LanguageController langController = new LanguageController(config.getConfiguration("language"));

        VBox root = FXMLLoader.load(new URL("file:data/fxml/menu.fxml"));
        root.getChildren().add(1, FXMLLoader.load(new URL("file:data/fxml/infoMenu.fxml")));
        root.getChildren().add(2, FXMLLoader.load(new URL("file:data/fxml/newsCanvas.fxml")));
        root.getChildren().add(3, FXMLLoader.load(new URL("file:data/fxml/start.fxml")));
        primaryStage.setTitle(setup.getConfiguration("title"));
        primaryStage.getIcons().add(new Image("file:data/images/" + setup.getConfiguration("logo_location")));
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

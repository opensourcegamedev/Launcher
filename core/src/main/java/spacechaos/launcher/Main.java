package spacechaos.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import spacechaos.launcher.config.Configuration;
import spacechaos.launcher.config.LanguageController;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Configuration config = new Configuration();
        config.load();

        LanguageController langController = new LanguageController(config.getConfiguration("language"));

        Parent root = FXMLLoader.load(new URL("file:core/src/main/resources/fxml/menu.fxml"));
        primaryStage.setTitle("SpaceChaos Launcher");
        primaryStage.getIcons().add(new Image("file:data/images/logo_square.jpg"));
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

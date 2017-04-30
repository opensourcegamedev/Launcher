package spacechaos.launcher;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import spacechaos.launcher.config.Configuration;
import spacechaos.launcher.config.LanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static HostServicesDelegate hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Configuration config = new Configuration();
        config.load();

        LanguageController langController = new LanguageController(config.getConfiguration("language"));


        VBox root = FXMLLoader.load(new URL("file:core/src/main/resources/fxml/menu.fxml"));
        root.getChildren().add(1, FXMLLoader.load(new URL("file:core/src/main/resources/fxml/infoMenu.fxml")));
        root.getChildren().add(2, FXMLLoader.load(new URL("file:core/src/main/resources/fxml/newsCanvas.fxml")));
        root.getChildren().add(3, FXMLLoader.load(new URL("file:core/src/main/resources/fxml/start.fxml")));
        primaryStage.setTitle("SpaceChaos Launcher");
        primaryStage.getIcons().add(new Image("file:data/images/logo_square.jpg"));
        primaryStage.setScene(new Scene(root, 1100, 700));
        primaryStage.show();
        hostServices = HostServicesFactory.getInstance(this);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

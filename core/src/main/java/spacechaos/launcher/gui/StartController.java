package spacechaos.launcher.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import spacechaos.launcher.Main;
import spacechaos.launcher.config.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Starts game from an executable jar.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class StartController {
    @FXML private Button startButton;
    @FXML private ComboBox<String> versionsBox;

    private HashMap<String, String> versions;

    @FXML private void initialize(){
        startButton.setText(Main.getLangController().getString("start"));
        versionsBox.setPromptText(Main.getLangController().getString("version"));
        versions = new HashMap<>();
        int versionNumbers = Integer.parseInt(new Settings("versionData/versions.properties").getConfiguration("versionNumbers"));
        for(int number = 1; number <= versionNumbers; ++number){
            try(Scanner scanner = new Scanner(new File("./data/versionData/version_" + number + ".txt"))){
                String versionName = scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                if(scanner.hasNextLine()){
                    versions.put(versionName, scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        ObservableList<String> versionList = versionsBox.getItems();
        versionList.addAll(versions.keySet());
    }

    @FXML private void startGame(){
        if(versionsBox.getValue() != null) {
            String path = new Settings("setup.properties").getConfiguration("path") + versions.get(versionsBox.getValue());
            try {
                Runtime.getRuntime().exec("java -jar " + path, null, new File(path).getParentFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}

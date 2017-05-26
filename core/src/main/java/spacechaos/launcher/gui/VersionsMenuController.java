package spacechaos.launcher.gui;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import spacechaos.launcher.config.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class VersionsMenuController {
    @FXML private VBox versionsPane;

    @FXML private void initialize(){
        int versionNumbers = Integer.parseInt(new Settings("versionData/versions.properties").getConfiguration("versionNumbers"));

        for(int number = 1; number <= versionNumbers; ++number){
            Canvas versionCanvas = new Canvas(900, 100);
            String[] versionInfo = new String[3];
            boolean installed = false;
            try(Scanner scanner = new Scanner(new File("./data/versionData/version_" + number + ".txt"))){
                versionInfo[0] = scanner.nextLine();
                versionInfo[1] = scanner.nextLine();
                versionInfo[2] = scanner.nextLine();
                if(scanner.hasNextLine()){
                    installed = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            GraphicsContext gc = versionCanvas.getGraphicsContext2D();
            gc.setStroke(Color.DARKGRAY);
            gc.setLineWidth(5);
            gc.strokeRoundRect(0, 0, 900, 100, 20, 20);
            gc.setFill(Color.WHITE);
            gc.fillRoundRect(5, 5, 890, 90, 20, 20);
            gc.setFill(Color.GRAY);
            gc.setFont(Font.font(20));
            gc.fillText(versionInfo[0], 50, 55);
            gc.setFont(Font.font(14));
            gc.fillText(versionInfo[2], 300, 55);

            if(installed){
                gc.setStroke(Color.GREENYELLOW);
                gc.strokeLine(850, 50, 860, 60);
                gc.strokeLine(860, 60, 875, 35);
            }else {
                gc.clearRect(820, 35, 60, 30);
                gc.setFont(Font.font(16));
                gc.setLineWidth(2);
                gc.fillText("Install", 830, 55);
                gc.strokeRoundRect(820, 35, 60, 30, 5, 5);

                versionCanvas.setOnMouseClicked(e->{
                    if(e.getX() > 820 && e.getX() < 880 && e.getY() > 35 && e.getY() < 90){
                        gc.setFill(Color.gray(0.1, 0.2));
                        gc.fillRoundRect(820, 35, 60, 30, 5, 5);
                        //TODO install version
                        new AnimationTimer() {
                            private long startTime = System.nanoTime();
                            @Override
                            public void handle(long now) {
                                if(now - startTime >= 100 * 1000000){
                                    gc.clearRect(820, 35, 60, 30);
                                    gc.setFont(Font.font(16));
                                    gc.setLineWidth(2);
                                    gc.setFill(Color.GRAY);
                                    gc.fillText("Install", 830, 55);
                                    gc.strokeRoundRect(820, 35, 60, 30, 5, 5);
                                    this.stop();
                                }
                            }
                        }.start();
                    }
                });
            }

            versionsPane.getChildren().add(versionCanvas);
        }
    }
}

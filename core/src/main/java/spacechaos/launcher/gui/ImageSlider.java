package spacechaos.launcher.gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import spacechaos.launcher.config.Settings;

/**
 * Controller for an ImageSlider which shows different pictures and notes
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class ImageSlider {
    @FXML private Canvas imageCanvas;

    private GraphicsContext gc;
    private AnimationTimer sliderThread;

    private ArrayList<String> notes;
    private ArrayList<Image> images;
    private int pictureNumbers;

    @FXML private void initialize() {
        pictureNumbers = Integer.parseInt(new Settings("news_data/newsData.properties").getConfiguration("pictureNumbers"));
        notes = new ArrayList<>();
        images = new ArrayList<>();

        for(int number = 1; number <= pictureNumbers; ++number){
            try(Scanner scanner = new Scanner(new File("./data/news_data/notes/note_" + number + ".txt"))){
                notes.add(scanner.nextLine());
                images.add(new Image("file:data/news_data/images/image_" + number + ".png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        gc = imageCanvas.getGraphicsContext2D();

        gc.setStroke(Color.WHITE);
        gc.setFont(new Font("arial", 16));

        gc.drawImage(images.get(0), 0, 0, 1024, 400);
        gc.setFill(Color.gray(0.0, 0.7));
        gc.fillRect(0, 300, 1024, 100);
        gc.setFill(Color.WHITE);
        gc.fillText(notes.get(0), 32, 325);

        sliderThread = new AnimationTimer() {
            private int currentPicture = 1;
            private int changingStatus = 0;
            private int changingRound = 0;

            private long lastChange = System.nanoTime();

            @Override
            public void handle(long now) {
                if(changingStatus == 0 && (now - lastChange) / 1000000 > 5 * 1000){
                    drawSimpleThings();
                    changingStatus = 1;
                    lastChange = now;
                }else if(changingStatus == 1 && (now - lastChange) / 1000000 > 32){
                    drawSimpleThings();
                    gc.setFill(Color.gray(1, changingRound  / 40.0));
                    gc.fillRect(0, 0, 1024, 400);
                    ++changingRound;
                    if(changingRound == 39){
                        changingStatus = 2;
                        ++currentPicture;
                        if(currentPicture > pictureNumbers){
                            currentPicture = 1;
                        }
                    }
                    lastChange = now;
                }else if(changingStatus == 2 && (now - lastChange) / 1000000 > 32){
                    drawSimpleThings();
                    gc.setFill(Color.gray(1, changingRound / 40.0));
                    gc.fillRect(0, 0, 1024, 400);
                    --changingRound;
                    if(changingRound == 0){
                        changingStatus = 0;
                    }
                    lastChange = now;
                }
            }

            private void drawSimpleThings(){
                gc.clearRect(0, 0, 1024, 400);
                gc.drawImage(images.get(currentPicture-1), 0, 0, 1024, 400);
                gc.setFill(Color.gray(0.0, 0.7));
                gc.fillRect(0, 300, 1024, 100);
                gc.setFill(Color.WHITE);
                gc.fillText(notes.get(currentPicture-1), 32, 325);
            }
        };
        draw();
    }

    private void draw() {
        sliderThread.start();
    }
}

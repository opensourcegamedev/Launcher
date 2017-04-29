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

/**
 * Created by Constantin on 29.04.2017.
 */
public class ImageSlider {
    public final static int PICTURE_NUMBERS = 2;

    @FXML
    private Canvas imageCanvas;

    private GraphicsContext gc;
    private AnimationTimer sliderThread;

    private ArrayList<String> notes;
    private ArrayList<Image> images;

    @FXML private void initialize() {
        notes = new ArrayList<String>();
        images = new ArrayList<Image>();

        for(int number = 1; number <= PICTURE_NUMBERS; ++number){
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

        gc.clearRect(0, 0, 1024, 512);
        gc.drawImage(images.get(0), 0, 0, 1024, 512);
        gc.setFill(Color.gray(0.0, 0.7));
        gc.fillRect(0, 320, 1024, 196);
        gc.strokeText(notes.get(0), 32, 352);

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
                    gc.fillRect(0, 0, 1024, 512);
                    ++changingRound;
                    if(changingRound == 39){
                        changingStatus = 2;
                        ++currentPicture;
                        if(currentPicture > PICTURE_NUMBERS){
                            currentPicture = 1;
                        }
                    }
                    lastChange = now;
                }else if(changingStatus == 2 && (now - lastChange) / 1000000 > 32){
                    drawSimpleThings();
                    gc.setFill(Color.gray(1, changingRound / 40.0));
                    gc.fillRect(0, 0, 1024, 512);
                    --changingRound;
                    if(changingRound == 0){
                        changingStatus = 0;
                    }
                    lastChange = now;
                }
            }

            private void drawSimpleThings(){
                gc.clearRect(0, 0, 1024, 512);
                gc.drawImage(images.get(currentPicture -1), 0, 0, 1024, 512);
                gc.setFill(Color.gray(0.0, 0.7));
                gc.fillRect(0, 320, 1024, 196);
                gc.strokeText(notes.get(currentPicture -1), 32, 352);
            }
        };
        draw();
    }

    private void draw() {
        sliderThread.start();
    }

    @FXML private void settings(){

    }
}

package spacechaos.launcher;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {

    public final static int PICTURE_NUMBERS = 2;

    @FXML private Canvas imageCanvas;
    @FXML private Button settingsButton;

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

        settingsButton.setGraphic(new ImageView("file:data/images/settings.png"));

        sliderThread = new AnimationTimer() {
            private int picture = 1;
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
                        ++picture;
                        if(picture > PICTURE_NUMBERS){
                            picture = 1;
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
                gc.drawImage(images.get(picture-1), 0, 0, 1024, 512);
                gc.setFill(Color.gray(0.0, 0.7));
                gc.fillRect(0, 320, 1024, 196);
                gc.strokeText(notes.get(picture-1), 32, 352);
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

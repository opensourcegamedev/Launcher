package spacechaos.launcher.gui;

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
    @FXML private Button settingsButton;

    @FXML private void initialize() {
        settingsButton.setGraphic(new ImageView("file:data/images/settings.png"));
    }

    @FXML private void settings(){}
}

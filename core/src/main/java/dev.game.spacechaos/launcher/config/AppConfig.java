package dev.game.spacechaos.launcher.config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AppConfig {
    private File properties;

    public AppConfig(){
        String path = "./data/config.properties";
        properties = new File(path);
    }

    public void activate(){
        try {
            properties.createNewFile();
            PrintWriter print = new PrintWriter(properties);
            print.println("language = default");
            print.println("build = " + 1);
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

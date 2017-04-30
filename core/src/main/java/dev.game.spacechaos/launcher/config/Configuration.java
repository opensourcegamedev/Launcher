package dev.game.spacechaos.launcher.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private Properties prop;
    private FileInputStream stream;

    public Configuration(){
        prop = new Properties();
        stream = null;

        init();
    }

    private void init(){
        String path = "./data/config.properties";
        File config = new File(path);
        if(!config.exists()){
            AppConfig startUp = new AppConfig();
            startUp.activate();
        }

        load();
    }

    public String getConfiguration(String key){
        return prop.getProperty(key);
    }

    public void load(){
        String path = "./data/config.properties";

        try {
            stream = new FileInputStream(path);
            prop.load(stream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void changeConfiguration(String key, String newValue){
        FileOutputStream out = null;
        try {
            String path = "./data/config.properties";
            File file = new File(path);
            out = new FileOutputStream(file);
            prop.setProperty(key, newValue);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

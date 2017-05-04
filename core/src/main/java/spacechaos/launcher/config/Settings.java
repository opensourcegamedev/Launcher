package spacechaos.launcher.config;

import java.io.*;
import java.util.Properties;

/**
 * @author Constantin Schulte
 */
public class Settings {
    private Properties prop;
    private FileInputStream stream;

    public Settings(String path){
        path = "./core/src/main/resources/" + path;
        prop = new Properties();
        stream = null;
        load(path);
    }

    public String getConfiguration(String key){
        return prop.getProperty(key);
    }

    private void load(String path){
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

    public void changeConfiguration(String key, String newValue, String path){
        FileOutputStream out = null;
        path = "./core/src/main/resources/" + path;
        try {
            File file = new File(path);
            out = new FileOutputStream(file);
            prop.setProperty(key, newValue);

        } catch (FileNotFoundException e) {e.printStackTrace();}

        try {
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

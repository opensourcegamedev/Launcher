package spacechaos.launcher.config;

import java.io.*;
import java.util.Properties;

/**
 * An object to load any *.properties-File into a Properties-object.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class Settings {
    private Properties prop;
    private String path;

    public Settings(String path){
        this.path = "./data/" + path;
        prop = new Properties();
        load();
    }

    private void load(){
        try(FileInputStream stream = new FileInputStream(path)) {
            prop.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getConfiguration(String key){
        return prop.getProperty(key);
    }

    public void changeConfiguration( String key, String newValue){
        FileOutputStream out = null;
        try {
            File file = new File(path);
            out = new FileOutputStream(file);
            prop.setProperty(key, newValue);

        } catch (FileNotFoundException e) {e.printStackTrace();}

        try {
            assert out != null;
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

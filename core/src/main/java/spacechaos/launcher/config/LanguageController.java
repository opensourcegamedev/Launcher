package spacechaos.launcher.config;

import java.util.*;

/**
 * Loads a specific language as ResourceBundle.
 *
 * @author Constantin Schulte
 * @version 0.1
 */
public class LanguageController {
    //ToDo Create a single list for the supported Languages and the and the returned supported languages.
    private static ArrayList<String> supported = new ArrayList<>();
    private ResourceBundle translation;

    public LanguageController(String language){
        Map<String, Locale> supportedLanguages = new HashMap<>();
        supportedLanguages.put("English", Locale.ENGLISH);
        supportedLanguages.put("Deutsch", Locale.GERMAN);
        translation = ResourceBundle.getBundle("language.language", supportedLanguages.get(language));
    }

    public String getString(String keyword){
        return translation.getString(keyword);
    }

    public static ArrayList<String> getSupported(){
        if(!supported.contains("English")){
            supported.add("English");
            supported.add("Deutsch");
        }
        return supported;
    }
}
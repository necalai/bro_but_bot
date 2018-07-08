import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

/**
 * A snippet for Google Translation showing how to detect the language of some text and translate
 * some other text.
 */
public class Translator {
    private static final String GOOGLE_API_KEY = "AIzaSyA-ZLv2ecFF3zB0ZTSKNKfL5H611W_tYBk";

    public static String translate(String text, String lang) {
        // Create a service object
        //
        // If no explicit credentials or API key are set, requests are authenticated using Application
        // Default Credentials if available; otherwise, using an API key from the GOOGLE_API_KEY
        // environment variable
        Translate translate = TranslateOptions.newBuilder().setApiKey(GOOGLE_API_KEY).build().getService();

        // Detect the language of the mysterious text
        Detection detection = translate.detect(text);
        String detectedLanguage = detection.getLanguage();

        if (detectedLanguage.equals(lang)) {
            return text;
        }

        // Translate the mysterious text to English
        Translation translation = translate.translate(
                text,
                TranslateOption.sourceLanguage(detectedLanguage),
                TranslateOption.targetLanguage(lang));

        return translation.getTranslatedText();
    }
}
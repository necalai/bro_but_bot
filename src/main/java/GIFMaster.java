import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;

public class GIFMaster {
    private static final String GIPHY_API_KEY = "dc6zaTOxFJmzC";
    private static final String EN_LANG = "en";

    public static String sendGIF(String request) {
        String urlGIF = "";
        Giphy giphy = new Giphy(GIPHY_API_KEY);

        SearchRandom giphyData;

        try {
            request = Translator.translate(request.replace("бро гиф ", ""), EN_LANG);
            giphyData = giphy.searchRandom(request);
            urlGIF = giphyData.getData().getImageOriginalUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlGIF;
    }
}

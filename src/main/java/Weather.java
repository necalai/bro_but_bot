import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    // ad22d2e74bf4e094c4fce5f91a3f6429
    public static String getWeather(String city, Model model) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=ad22d2e74bf4e094c4fce5f91a3f6429");

        Scanner in = new Scanner((InputStream) url.getContent());
        while (in.hasNext()) {
            result.append(in.nextLine());
        }

        return result.toString();
    }
}

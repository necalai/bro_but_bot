import com.github.imgur.ImgUr;
import com.github.imgur.api.image.ImageRequest;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        ImgUr imgUr = new ImgUr("5abcdefghi123456123111");
        ImageRequest imageRequest = new ImageRequest("");

        try {
            imgUr.call(imageRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
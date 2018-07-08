import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.nio.file.Path;

public class Actions {
    public static void sendText(BroBot broBot, Message message, String text) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            broBot.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendPicture(BroBot broBot, Message message, Path pathToPhoto) {
        SendPhoto sendPhoto = new SendPhoto();

        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setNewPhoto(pathToPhoto.toFile());

        try {
            broBot.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendGIF(BroBot broBot, Message message) {
        SendDocument sendDocument = new SendDocument();

        sendDocument.setChatId(message.getChatId());
        sendDocument.setDocument(GIFMaster.sendGIF(message.getText()));

        try {
            broBot.sendDocument(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

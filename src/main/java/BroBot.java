import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BroBot extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "588842074:AAGU7GqaZ5TiBgmm43fsu5NMIWy0jCAKJI8";
    private static final String BOT_USERNAME = "бро";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new BroBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    private void sendText(Message message, String text) {
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
            this.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Model model = new Model();
        Path pathToPhoto = Paths.get("src/main/resources/norvezhskie_kotyata.jpg");

        if (message != null && message.hasText()) {
            String text = message.getText();

            switch (text) {
                case "привет":
                    sendText(message, "приветики");
                    break;
                default:
                    try {
                        if (text.equalsIgnoreCase(getBotUsername() + " погода")) {
                            sendText(message, Weather.getWeather("Питер", model));
                            sendText(message, Weather.getWeather("Москва", model));
                        } else if (text.matches(getBotUsername() + " погода .*")) {
                            text = text.replace(getBotUsername() + " погода ", "");

                            sendText(message, Weather.getWeather(text, model));
                        }
                    } catch (IOException e) {
                        sendText(message, "Нет такого города");
                    }

                    break;
            }
        }

        if (message != null && message.getText().matches("бро фото .*")) {
            Actions.sendPicture(this, message, pathToPhoto);
        }

        if (message != null && message.getText().matches("бро гиф .*")) {
            Actions.sendGIF(this, message);
        }
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}

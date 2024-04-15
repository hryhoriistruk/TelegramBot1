package ua.javarush;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static ua.javarush.TelegramBotContent.*;
import static ua.javarush.TelegramBotUtils.*;

public class MyFirstTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // TODO: додай ім'я бота в лапки нижче
        return "qwerty32332_jru_bot";
    }

    @Override
    public String getBotToken() {
        // TODO: додай токен бота в лапки нижче
        return "7132694347:AAGhYn2sGZqWnK4GHK4J2VDhCyNnECFWyTk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // TODO: основний функціонал бота будемо писати тут
        Long chatId = getChatId(update);

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            addGlories(chatId, 0);
            SendMessage message = createMessage(chatId, STEP_1_TEXT, Map.of(
                    "Злам холодильника", "step_1_btn"
            ));
            sendApiMethodAsync(message);
        } //виведення тексту та кнопки
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("step_1_btn") && getGlories(chatId) == 0) { //натискання кнопки
                addGlories(chatId, 20);
                SendMessage message = createMessage(chatId, STEP_2_TEXT, Map.of(
                        "взяти сосиску! +20 слави", "step_2_btn"
                        , "Взяти рибку! +20 слави", "step_2_btn"
                        , "Скинути банку з огірками! +20 слави", "step_2_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_2_btn") && getGlories(chatId) == 20) { //натискання кнопки
                addGlories(chatId, 20);
                SendMessage message = createMessage(chatId, STEP_3_TEXT, Map.of(
                        "Злам робота пилососа", "step_3_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_3_btn") && getGlories(chatId) == 40) { //натискання кнопки
                addGlories(chatId, 30);
                SendMessage message = createMessage(chatId, STEP_4_TEXT, Map.of(
                        "Відправити робопилосос за їжею! +30 слави", "step_4_btn"
                        , "Проїхатися на робопилососі! +30 слави", "step_4_btn"
                        , "Тікати від робопилососа! +30 слави", "step_4_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_4_btn") && getGlories(chatId) == 70) { //натискання кнопки
                addGlories(chatId, 30);
                SendMessage message = createMessage(chatId, STEP_5_TEXT, Map.of(
                        "Одягнути та включити GoPro!", "step_5_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_5_btn") && getGlories(chatId) == 100) { //натискання кнопки
                addGlories(chatId, 40);
                SendMessage message = createMessage(chatId, STEP_6_TEXT, Map.of(
                        "Бігати дахами, знімати на GoPro! +40 слави", "step_6_btn"
                        , "З GoPro нападати на інших котів із засідки! +40 слави", "step_6_btn"
                        , "З GoPro нападати на собак із засідки! +40 слави", "step_6_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_6_btn") && getGlories(chatId) == 140) { //натискання кнопки
                addGlories(chatId, 40);
                SendMessage message = createMessage(chatId, STEP_7_TEXT, Map.of("злам пароля", "step_7_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_7_btn") && getGlories(chatId) == 180) { //натискання кнопки
                addGlories(chatId, 50);
                SendMessage message = createMessage(chatId, STEP_8_TEXT, Map.of(
                        "Вийти на подвір'я", "step_8_btn"));
                sendApiMethodAsync(message);
            }
            if (update.getCallbackQuery().getData().equals("step_8_btn") && getGlories(chatId) == 230) { //натискання кнопки
                addGlories(chatId, 50);
                SendMessage message = createMessage(chatId, FINAL_TEXT);
                sendApiMethodAsync(message);
            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot{

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String txt = message.getText();
        if (txt.equals("/start")) {
            sendMsg(message, StartCmd.startMessage(message));
        }
        if (!message.getChat().getUserName().equals("MisterTheFoxy")) {
            forwardMsg(message);
            System.out.println(message.getChatId());
        } else {
            replyForwardMsg(message);
        }
    }

    private void sendMsg(Message msg, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage
                .setChatId(msg.getChatId())
                .setText(text);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ForwardMessage forwardMsg(Message msg) {
        ForwardMessage forwardMessage = new ForwardMessage();

        forwardMessage
                .setChatId("379865829")
                .setMessageId(msg.getMessageId())
                .setFromChatId(msg.getChatId());
        try {
            forwardMessage(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return forwardMessage;
    }

    private void replyForwardMsg(Message msg) {
        Message replyMessage = msg.getReplyToMessage();

        SendMessage sendMessage = new SendMessage();

        sendMessage
                .setText(msg.getText())
                .setChatId((long)replyMessage.getForwardFrom().getId());
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "TypeSmartBot";
    }

    public String getBotToken() {
        return "614182466:AAG1jgDM6Ho4zDI_7UMz7hwg_VvHNrO_meU";
    }

}


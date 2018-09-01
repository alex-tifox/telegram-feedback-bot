package commands;

import org.telegram.telegrambots.api.objects.Message;

public class StartCmd {

    public static String startMessage(Message message){
        if (masterCheck(message.getChat().getUserName()) == true) {
            return "Hello, my master";
        } else {
            return "Nice to meet you! \n Great to see you there, I'm a child of my creator \n Test child :DDD";
        }
    }

    public static boolean masterCheck(String userName){
        if (userName.equals(" MisterTheFoxy")) {
            return true;
        } else {
            return false;
        }

    }
}

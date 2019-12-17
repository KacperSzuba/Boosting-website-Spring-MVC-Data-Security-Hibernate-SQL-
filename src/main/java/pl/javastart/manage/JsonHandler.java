package pl.javastart.manage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.Message;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class JsonHandler {

    @Autowired
    private MessageHandler messageHandler;

    public JSONArray createJsonFile(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        for (Message message : messageHandler.conversationSortedByDataDESC(messageHandler.getIdOfConversation(),request)) {
            JSONObject record = new JSONObject();
            record.put("id",message.getId());
            record.put("title",message.getTitle());
            record.put("message",message.getMessage());
            record.put("messageSender",message.getMessageSender().getId());
            record.put("recipientOfTheMessage",message.getRecipientOfTheMessage().getId());
            record.put("date",message.getDate().toString());
            jsonArray.add(record);
        }try {
            FileWriter file = new FileWriter("C:\\Users\\kacpe\\IdeaProjects\\BoostingPage\\src\\main\\webapp\\json\\Message.json");
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}

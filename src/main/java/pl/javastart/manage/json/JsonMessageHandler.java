package pl.javastart.manage.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.message.MessageHandler;
import pl.javastart.model.entity.message.Message;

import javax.servlet.http.HttpServletRequest;

@Component
public class JsonMessageHandler {

    @Autowired
    private MessageHandler messageHandler;

    public JSONArray createJsonFile(HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();

        for (Message message : messageHandler.conversationSortedByDataDESC(messageHandler.getIdOfConversation(),request)) {
            JSONObject record = new JSONObject();
            record.put("id",message.getId());
            record.put("title",message.getTitle());
            record.put("sendMessage",message.getMessage());
            record.put("messageSender",message.getUser().getId());
            record.put("recipientOfTheMessage",message.getUser2().getId());
            record.put("date",message.getDate().toString());
            jsonArray.add(record);
        }
        return jsonArray;
    }
}

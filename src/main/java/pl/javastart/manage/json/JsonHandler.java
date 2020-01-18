package pl.javastart.manage.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.model.entity.TierImage;
import pl.javastart.repository.TierImageRepository;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class JsonHandler {

    @Autowired
    private TierImageRepository tierImageRepository;

    public JSONArray createJsonFile() {
        JSONArray jsonArray = new JSONArray();
        for (TierImage tierImage : tierImageRepository.findAll()) {
            JSONObject record = new JSONObject();
            record.put("id",tierImage.getId());
            record.put("img_source",tierImage.getImgSource());
            record.put("tier_id",tierImage.getTier().getId());
            record.put("division_id",tierImage.getDivision().getId());
            jsonArray.add(record);
        }
        try {
            FileWriter file = new FileWriter("C:\\Users\\kacpe\\IdeaProjects\\BoostingPage\\src\\main\\webapp\\json\\Order.json");
            file.write(jsonArray.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}

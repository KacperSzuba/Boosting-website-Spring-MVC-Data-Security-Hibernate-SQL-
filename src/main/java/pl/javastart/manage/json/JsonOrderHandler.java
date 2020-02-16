package pl.javastart.manage.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.javastart.model.entity.TierImage;
import pl.javastart.repository.TierImageRepository;

@Component
public class JsonOrderHandler {

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
        return jsonArray;
    }
}

package com.BoostingWebsite.order;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import com.BoostingWebsite.order.division.entity.TierImage;
import com.BoostingWebsite.order.division.repository.TierImageRepository;

@Component
public class JsonOrderHandler {

    private final TierImageRepository tierImageRepository;

    public JsonOrderHandler(TierImageRepository tierImageRepository) {
        this.tierImageRepository = tierImageRepository;
    }

    JSONArray createJsonFile() {
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

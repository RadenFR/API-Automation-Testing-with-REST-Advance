package body.sportactivity;

import org.json.simple.JSONObject;

public class SportActivityBody {

    public JSONObject createSportActivityData(
            String sport_category_id,
            int city_id,
            String tittle,
            String description,
            int slot,
            int price,
            String address,
            String activity_date,
            String start_time,
            String end_time,
            String map_url) {

        JSONObject body = new JSONObject();
        body.put("sport_category_id", sport_category_id);
        body.put("city_id", city_id);
        body.put("title", tittle);
        body.put("description", description);
        body.put("slot", slot);
        body.put("price", price);
        body.put("address", address);
        body.put("activity_date", activity_date);
        body.put("start_time", start_time);
        body.put("end_time", end_time);
        body.put("map_url", map_url);
        return body;
    }

    public JSONObject updateSportActivityData(String sportCategoryId, String cityId, String tittle, String description, int slot,
                                              int price, String address, String activityDate, String startTime, String endTime, String mapUrl) {
        JSONObject body = new JSONObject();
        body.put("sport_category_id", sportCategoryId);
        body.put("city_id", cityId);
        body.put("title", tittle);
        body.put("description", description);
        body.put("slot", slot);
        body.put("price", price);
        body.put("address", address);
        body.put("activity_date", activityDate);
        body.put("start_time", startTime);
        body.put("end_time", endTime);
        body.put("map_url", mapUrl);
        return body;
    }
}

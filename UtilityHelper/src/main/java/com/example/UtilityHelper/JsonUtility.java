package com.example.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtility {

    public  JSONObject parseJson(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  Object getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  JSONArray parseJsonArray(String jsonString) {
        try {
            return new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  boolean isValidJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}

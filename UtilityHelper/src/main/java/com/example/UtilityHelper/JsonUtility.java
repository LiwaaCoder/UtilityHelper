package com.example.UtilityHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtility {

    public static JSONObject parseJson(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getValue(JSONObject jsonObject, String key) {
        try {
            return jsonObject.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray parseJsonArray(String jsonString) {
        try {
            return new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidJson(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}

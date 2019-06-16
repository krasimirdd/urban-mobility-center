package com.axway.googleSearch.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final JsonParser jp = new JsonParser();

    public static String toJson(int boundary, Set<String> titles, Set<String> descriptions, Set<String> links) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObj;

        List<String> titlesList = new ArrayList<>(titles);
        List<String> descsList = new ArrayList<>(descriptions);
        List<String> linksList = new ArrayList<>(links);

        for (int i = 0; i < titles.size(); i++) {
            jsonObj = new JSONObject();
            jsonObj.put("title", titlesList.get(i));
            jsonObj.put("link", linksList.get(i));
            jsonObj.put("description", descsList.get(i));
            jsonArray.put(jsonObj);
        }

        return gson.toJson(jp.parse(String.valueOf(jsonArray)));
    }
}

package com.alexd10s.wallofimages.parser;

import com.alexd10s.wallofimages.model.Pin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 22/09/2018.
 */

public class ModelParser {
    static Gson gson = new Gson();

    public static List<Pin> getPins(String jsonArray) {
        List<Pin> list = new ArrayList<>();
        try {
            //JSONObject request = new JSONObject(jsonArray);
            Type listType = new TypeToken<ArrayList<Pin>>() {}.getType();
            list = gson.fromJson(jsonArray, listType);

        } catch (Exception e) {
            String ex = e.getMessage();
            e.printStackTrace();
        }

        return list;

    }
}

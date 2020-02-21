package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject jsonObject = new JSONObject(json);

            sandwich = new Sandwich();

            JSONObject name = jsonObject.getJSONObject("name");

            sandwich.setMainName(name.getString("mainName"));

            sandwich.setAlsoKnownAs(parseJsonArray(name.getJSONArray("alsoKnownAs")));

            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));

            sandwich.setDescription(jsonObject.getString("description"));

            sandwich.setImage(jsonObject.getString("image"));

            sandwich.setIngredients(parseJsonArray(jsonObject.getJSONArray("ingredients")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> parseJsonArray(JSONArray jsonArray) throws JSONException{
        List<String> listToBeReturned = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            listToBeReturned.add(jsonArray.getString(i));
        }

        return listToBeReturned;
    }
}

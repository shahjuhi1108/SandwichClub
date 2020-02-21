package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject jsonObject = new JSONObject(json);

            sandwich = new Sandwich();

            JSONObject name = jsonObject.getJSONObject(KEY_NAME);

            sandwich.setMainName(name.getString(KEY_MAIN_NAME));

            sandwich.setAlsoKnownAs(parseJsonArray(name.getJSONArray(KEY_ALSO_KNOW_AS)));

            sandwich.setPlaceOfOrigin(jsonObject.getString(KEY_PLACE_OF_ORIGIN));

            sandwich.setDescription(jsonObject.getString(KEY_DESCRIPTION));

            sandwich.setImage(jsonObject.getString(KEY_IMAGE));

            sandwich.setIngredients(parseJsonArray(jsonObject.getJSONArray(KEY_INGREDIENTS)));

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

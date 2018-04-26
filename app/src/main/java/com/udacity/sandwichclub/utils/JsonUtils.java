package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import static com.udacity.sandwichclub.data.Contract.ALSO_KNOWN_AS;
import static com.udacity.sandwichclub.data.Contract.DESCRIPTION;
import static com.udacity.sandwichclub.data.Contract.IMAGE;
import static com.udacity.sandwichclub.data.Contract.INGREDIENTS;
import static com.udacity.sandwichclub.data.Contract.MAIN_NAME;
import static com.udacity.sandwichclub.data.Contract.NAME;
import static com.udacity.sandwichclub.data.Contract.PLACE_OF_ORIGIN;

public class JsonUtils {
    private static final String TAG = JsonUtils.class.getSimpleName ();

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich =new Sandwich();

        try {

            //remember this  the format of the first  item of json
/*
            {
      "name": {
                                   "mainName": "Club sandwich",
                                     "alsoKnownAs": [ "Clubhouse sandwich" ]
            },

                "placeOfOrigin": "United States",
               "description": "A club sandwich, also called a clubhouse sandwich, is a sandwich of bread (occasionally toasted),
                                              sliced cooked poultry, fried bacon, lettuce, tomato, and mayonnaise. It is often cut into quarters or
                                               halves and held together by cocktail sticks. Modern versions frequently have two layers which are
                                               separated by an additional slice of bread.",

                "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png",


                 "ingredients": [
                                                 "Toasted bread",

                                                  "Turkey or chicken",

                                                      "Bacon",

                                                      "Lettuce",

                                                          "Tomato",

                                                           "Mayonnaise"
  ]
            }*/
/*
          make new object of JSONObject as a parent of json that
          come from intent and and call the value of it,s name in items
*/JSONObject item_jsonOpj = new JSONObject(json);

/*
    make object of JSONObject   get the name from
     the specific value of item_jsonOpj
*/JSONObject jsonObj_name= item_jsonOpj.getJSONObject(NAME);

/*
          make  a String get the value of mainName and because the
           mainName JsonObject inside the name Object we get it from value of jsonObj_name
*/String main_name= jsonObj_name.getString(MAIN_NAME);

/*
       otherwise placeOfOrigin out of jsonObj_name
        directly come from main parent  JsonObject  so we get it as String  from item_jsonOpj
*/String jsonObj_placeOfOrigin= item_jsonOpj.getString(PLACE_OF_ORIGIN);

/*
         String jsonObj_description as the placeOfOrigin
         come from main parent
*/String jsonObj_description= item_jsonOpj.getString(DESCRIPTION);

/*
          and  String  jsonObj_image come from the main parent
          item_jsonOpj
*/String jsonObj_image= item_jsonOpj.getString(IMAGE);

/*
           make JSONArray that get the value of ingredients[]
           from item_jsonOpj
*/JSONArray jsonArray_ingredients= item_jsonOpj.getJSONArray(INGREDIENTS);

/*
        make JSONArray that get the value of ingredients[] from jsonObj_name
*/JSONArray jsonArray_alsoKnownAs= jsonObj_name.getJSONArray(ALSO_KNOWN_AS);

/*
            set the value of mainName inside the object of class sandwich
*/sandwich.setMainName(main_name);

/*
            set the value of placeOfOrigin inside the object of class sandwich
*/sandwich.setPlaceOfOrigin(jsonObj_placeOfOrigin);

            //set the value of description inside the object of class sandwich
            sandwich.setDescription(jsonObj_description);

            //set the value of image inside the object of class sandwich
            sandwich.setImage(jsonObj_image);

/*
       make new ArrayList to add the jsonArray_ingredients inside it  with foor loop
*/ArrayList<String> listIngredients = new ArrayList<String>();
            if (jsonArray_ingredients != null) {
                for (int i=0;i<jsonArray_ingredients.length();i++){
                    //add the jsonArray_ingredients  with the vlues come with loop in  arrayList  listIngredients
                    listIngredients.add(jsonArray_ingredients.getString(i));
                    //set the value of listIngredients  the object of class sandwich
                    sandwich.setIngredients(listIngredients);

                }
            }

/*
           make new ArrayList to add the jsonArray_ingredients inside it  with foor loop
*/ArrayList<String> listAlsoKnownAs = new ArrayList<String>();
            if (jsonArray_alsoKnownAs != null) {
                for (int i=0;i<jsonArray_alsoKnownAs.length();i++){
                    //add the jsonArray_alsoKnownAs  with the vlues come with loop in  arrayList  listAlsoKnownAs
                    listAlsoKnownAs.add(jsonArray_alsoKnownAs.getString(i));
                    //set the value of alsoKnownAs   by metthod setAlsoKnownAs in the Object
                    sandwich.setAlsoKnownAs(listAlsoKnownAs);
                }
            }
            Log.v(TAG,"MYJSON"+item_jsonOpj.toString());



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }


}

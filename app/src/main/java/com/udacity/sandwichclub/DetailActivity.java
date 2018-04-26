package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName ();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    /*make new TeaxtVies to show all Value we want inside */
    private  TextView mOrigin_tv,mDescription_tv,mIngredients_tv,mAalso_known_tv,mPlace_of_origin_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Intent intent = getIntent();
     //   getIntent=intent.getStringExtra(EXTRA_POSITION)
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION
                , DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
           closeOnError();
            return;
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
//get value of item from it,s position that come from intent
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        /*
        make loop to get the position of all items of list that*/
        for(int i=1;i==sandwiches.length;i++){

            json += " ";
            json += sandwiches[i];
            //parse the value of item to to class JsonUtil @params sandwiches
            JsonUtils.parseSandwichJson(sandwiches[i]);
        }

        if (sandwich == null) {
            // Sandwich data unavailable
           closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);
        setTitle(sandwich.getMainName());

        //make a new object of arrayList to save the value which come from Sandwich class
        ArrayList<String> ingredientsArray = (ArrayList<String>) sandwich.getIngredients();
//make  new Object of StringBuilder to save all array inside
        StringBuilder ingredientsString = new StringBuilder();
        if(ingredientsArray!=null){
         /*   Distribute the items in order with each other to appear in a
            thankful order and take more than one line inside StringBuilder ingredientsString */
            for (String items : ingredientsArray) {
             ingredientsString.append(items);
             ingredientsString.append("\n");
             // show the value of  StringBuilder ingredientsString  inside TextView mIngredients_tv
             mIngredients_tv.setText(ingredientsString);
    }
  }
        //make a new object of arrayList to save the value which come from Sandwich class
        ArrayList<String>AlsoKnownArray = (ArrayList<String>) sandwich.getAlsoKnownAs();
        //make  new Object of StringBuilder to save all array inside
        StringBuilder alsoKnownString = new StringBuilder();
          /*   Distribute the items in order with each other to appear in a
            thankful order and take more than one line inside StringBuilder alsoKnownString */
      if(AlsoKnownArray!=null){
            for (String items : AlsoKnownArray) {
              alsoKnownString.append(items);
              alsoKnownString.append("\n");
                // show the value of  StringBuilder ingredientsString  inside TextView mAalso_known_tv
                mAalso_known_tv.setText(alsoKnownString);
          }
      }
        // show the value of  String  getMainName()  inside TextView mOrigin_tv
        mOrigin_tv.setText(sandwich.getMainName());
        // show the value of  String  getPlaceOfOrigin()  inside TextView mPlace_of_origin_tv
        mPlace_of_origin_tv.setText(sandwich.getPlaceOfOrigin());
        // show the value of  String  getDescription()  inside TextView mDescription_tv
        mDescription_tv.setText(sandwich.getDescription());
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this,R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        mOrigin_tv=findViewById(R.id.origin_tv);
        mDescription_tv=findViewById(R.id.description_tv);
        mIngredients_tv=findViewById(R.id.ingredients_tv);
        mAalso_known_tv=findViewById(R.id.also_known_tv);
        mPlace_of_origin_tv=findViewById(R.id.place_of_origin_tv);


    }
}

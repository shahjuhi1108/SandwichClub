package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownAs = findViewById(R.id.also_known_tv);

        List<String> listAlsoKnownAs = sandwich.getAlsoKnownAs();

        alsoKnownAs.setText(appendJsonListParameters(listAlsoKnownAs));

        TextView placeOfOrigin = findViewById(R.id.origin_tv);
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());

        TextView description = findViewById(R.id.description_tv);
        description.setText(sandwich.getDescription());

        TextView ingredients = findViewById(R.id.ingredients_tv);

        List<String> listIngredients = sandwich.getIngredients();

        ingredients.setText(appendJsonListParameters(listIngredients));
    }

    private String appendJsonListParameters(List<String> listToBeUsed) {

        StringBuffer stringBuffer = new StringBuffer();

        if (!listToBeUsed.isEmpty()) {

            stringBuffer.append(listToBeUsed.get(0));

            for (int i = 1; i < listToBeUsed.size(); i++) {
                stringBuffer.append(", ");
                stringBuffer.append(listToBeUsed.get(i));

            }
        }

        return stringBuffer.toString();

    }

}

package com.poobest.com.mockpeoject.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poobest.com.mockpeoject.R;

import java.util.Map;


public class DescriptionActivity extends AppCompatActivity {

    public DatabaseReference myRef;
    private TextView firebaseMenuname;
    private TextView firebaseIngredient;
    private TextView firebaseDescription;
    private TextView firebaseImage;
    private String urlImage;
    ImageView image_menu_description;
    int id = 0;
    int i = 1;
    TextView id_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        initInstance();

        Intent intent = getIntent();
        id = intent.getIntExtra("position", 0);

    }

    private void initInstance() {

        id_picture = (TextView) findViewById(R.id.id_picture);
        id_picture.setText("id=" + id);

        image_menu_description = (ImageView) findViewById(R.id.image_menu_description);
        firebaseIngredient = (TextView) findViewById(R.id.text_ingredient_menu_description);
        firebaseMenuname = (TextView) findViewById(R.id.text_name_menu_description);
        firebaseDescription = (TextView) findViewById(R.id.text_description_description);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();

                urlImage = String.valueOf(map.get("img" + i));
                //Toast.makeText(getBaseContext(),urlImage,Toast.LENGTH_LONG).show();

                String valueName = String.valueOf(map.get("Menu_name" + i));
                firebaseMenuname.setText(valueName);

                String valueDescription = String.valueOf(map.get("Description"));
                firebaseDescription.setText(valueDescription);

                String valueIngredient = String.valueOf(map.get("Ingredient"));
                firebaseIngredient.setText(valueIngredient);

                Glide.with(getBaseContext()).load("" + urlImage).into(image_menu_description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

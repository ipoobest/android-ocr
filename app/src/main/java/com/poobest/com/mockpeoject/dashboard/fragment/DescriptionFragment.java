package com.poobest.com.mockpeoject.dashboard.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by RUNGNUENG on 9/10/2560.
 */

public class DescriptionFragment extends Fragment {
    public DatabaseReference myRef;
    private TextView firebaseMenuname;
    private TextView firebaseIngredient;
    private TextView firebaseDescription;
    private TextView firebaseImage;
    private String urlImage;
    ImageView image_menu_description;
    int i = 1;

    public DescriptionFragment() {
        super();
    }

    public static DescriptionFragment newInstance() {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }


    private void init(Bundle savedInstanceState) {
    }

    private void initInstances(View rootView, Bundle savedInstanceState) {


        image_menu_description = rootView.findViewById(R.id.image_menu_description);
        firebaseIngredient = rootView.findViewById(R.id.text_ingredient_menu_description);
        firebaseMenuname = rootView.findViewById(R.id.text_name_menu_description);
        firebaseDescription = rootView.findViewById(R.id.text_description_description);

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

                String valueDescription = String.valueOf(map.get("Description" + i));
                firebaseDescription.setText(valueDescription);

                String valueIngredient = String.valueOf(map.get("Ingredient" + i));
                firebaseIngredient.setText(valueIngredient);

                Glide.with(getContext()).load("" + urlImage).into(image_menu_description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }
}

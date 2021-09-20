package com.jk.codez;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.jk.codez.ad.AestheticDialog;
import com.jk.codez.ad.DialogAnimation;
import com.jk.codez.ad.DialogStyle;
import com.jk.codez.ad.DialogType;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    // Initialize variables
    Spinner spStyle, spType, spAnimation;
    Button btShow;
    ArrayList<DialogStyle> styleList = new ArrayList<>();
    ArrayList<DialogType> typeList = new ArrayList<>();
    ArrayList<DialogAnimation> Animationlist = new ArrayList<>();
    DialogStyle dialogStyle;
    DialogAnimation dialogAnimation;
    DialogType dialogType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // assign variables
        spStyle = findViewById(R.id.sp_style);
        spType = findViewById(R.id.sp_type);
        spAnimation = findViewById(R.id.sp_animation);
        btShow = findViewById(R.id.bt_show);

        // add dialog styles to arraylist
        styleList.add(DialogStyle.FLASH);
        styleList.add(DialogStyle.CONNECTIFY);
        styleList.add(DialogStyle.TOASTER);
        styleList.add(DialogStyle.EMOJI);
        styleList.add(DialogStyle.EMOTION);
        styleList.add(DialogStyle.DRAKE);
        styleList.add(DialogStyle.RAINBOW);
        styleList.add(DialogStyle.FLAT);

        // set adapter to style spinner
        spStyle.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, styleList));

        // add dialog types to arraylist
        typeList.add(DialogType.ERROR);
        typeList.add(DialogType.INFO);
        typeList.add(DialogType.WARNING);
        typeList.add(DialogType.SUCCESS);

        // set adapter to type spinner
        spType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, typeList));

        // add dialog animations to arraylist
        Animationlist.add(DialogAnimation.FADE);
        Animationlist.add(DialogAnimation.CARD);
        Animationlist.add(DialogAnimation.DEFAULT);
        Animationlist.add(DialogAnimation.DIAGONAL);
        Animationlist.add(DialogAnimation.IN_OUT);
        Animationlist.add(DialogAnimation.SHRINK);
        Animationlist.add(DialogAnimation.SLIDE_DOWN);
        Animationlist.add(DialogAnimation.SLIDE_LEFT);
        Animationlist.add(DialogAnimation.SLIDE_RIGHT);
        Animationlist.add(DialogAnimation.SLIDE_UP);
        Animationlist.add(DialogAnimation.SPIN);
        Animationlist.add(DialogAnimation.SPLIT);
        Animationlist.add(DialogAnimation.SWIPE_LEFT);
        Animationlist.add(DialogAnimation.SWIPE_RIGHT);
        Animationlist.add(DialogAnimation.WINDMILL);
        Animationlist.add(DialogAnimation.ZOOM);

        // set adapter to animation spinner
        spAnimation.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Animationlist));

        // to select style
        spStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // get selected style
                dialogStyle = styleList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // to select type
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // get selected style
                dialogType = typeList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // to select animation
        spAnimation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // get selected style
                dialogAnimation = Animationlist.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // to show dialog as output
        btShow.setOnClickListener(view -> {
            // initialize dialog
            AestheticDialog.Builder builder = new AestheticDialog.Builder(Test.this, dialogStyle, dialogType);
            // set title
            builder.setTitle("Title");
            // set message
            builder.setMessage("Message");
            // set animation
            builder.setAnimation(dialogAnimation);
            // show dialog
            builder.show();
        });
    }
}

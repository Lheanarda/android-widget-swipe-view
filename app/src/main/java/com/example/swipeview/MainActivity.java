package com.example.swipeview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //0. Make New Style (see below default style) and implement in android:theme manifest
        //1. file-project structure - + library dependencies - card view search
        //2. Relative Layout
        //3. Add ViewPager in layout , + codes design in layout
        //4. new layout file (linear) + codes design
        //5. new class model + codes inside
        //6. new class Adapter + codes
        //7. code in main activity
        //8. add color resource for colors

        models = new ArrayList<>();
        models.add(new Model(R.drawable.brochure,"Brochure","Brochure desc"));
        models.add(new Model(R.drawable.sticker,"Sticker","Sticker desc"));
        models.add(new Model(R.drawable.poster,"Poster","Poster desc"));
        models.add(new Model(R.drawable.namecard,"Name Card","Name Card desc"));

        adapter = new Adapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),

        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position<(adapter.getCount()-1) && position< (colors.length-1)){
                    viewPager.setBackgroundColor(
                            (Integer)argbEvaluator.evaluate(positionOffset,
                                    colors[position],
                                    colors[position+1]
                            )
                    );
                }else{
                    viewPager.setBackgroundColor(colors[colors.length-1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}

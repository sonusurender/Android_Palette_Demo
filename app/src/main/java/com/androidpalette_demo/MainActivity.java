package com.androidpalette_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView vibrantView, vibrantLightView, vibrantDarkView, mutedView, mutedLightView, mutedDarkView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        implementRadioGroupListener();
        //Initially load first image
        toggleImages(R.drawable.photo);
    }

    /*  Init all Views  */
    private void initViews() {
        imageView = (ImageView) findViewById(R.id.main_image_view);

        vibrantView = (TextView) findViewById(R.id.vibrantView);
        vibrantLightView = (TextView) findViewById(R.id.vibrantLightView);
        vibrantDarkView = (TextView) findViewById(R.id.vibrantDarkView);
        mutedView = (TextView) findViewById(R.id.mutedView);
        mutedLightView = (TextView) findViewById(R.id.mutedLightView);
        mutedDarkView = (TextView) findViewById(R.id.mutedDarkView);

    }

    /*  Implement Check listener over Radio group */
    private void implementRadioGroupListener() {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // Finding ID/Position of radio group child
                int pos = radioGroup.indexOfChild(findViewById(checkedId));

                //Toggle between images
                switch (pos) {
                    case 0:
                        toggleImages(R.drawable.photo);
                        break;
                    case 1:
                        toggleImages(R.drawable.download);
                        break;

                }
            }

        });
    }

    /*  Toggle method to switch between images  */
    private void toggleImages(int drawableIcon) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableIcon);//Get bitmap from drawable resources
        imageView.setImageBitmap(bitmap);//set bitmap over image view
        setToolbarColor(bitmap);//set toolbar color according bitmap
        createPaletteAsync(bitmap);//generate palette asynchronously
    }

    /*  Set the background and text colors of a toolbar given a bitmap image to match  */
    public void setToolbarColor(Bitmap bitmap) {
        // Generate the palette and get the vibrant swatch
        // See the createPaletteSync() and checkVibrantSwatch() methods
        // from the code snippets above
        Palette p = createPaletteSync(bitmap);
        Palette.Swatch vibrantSwatch = checkVibrantSwatch(p);

        // Set the toolbar background and text colors
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(vibrantSwatch.getRgb());
        toolbar.setTitleTextColor(vibrantSwatch.getTitleTextColor());
    }


    // Generate palette synchronously and return it
    public Palette createPaletteSync(Bitmap bitmap) {
        return Palette.from(bitmap).generate();
    }


    // Return a palette's vibrant swatch after checking that it exists
    private Palette.Swatch checkVibrantSwatch(Palette p) {
        Palette.Swatch vibrant = p.getVibrantSwatch();
        if (vibrant != null) {
            return vibrant;
        }
        return null;
    }

    /* Generate palette asynchronously and use it on a different thread using onGenerated() */
    private void createPaletteAsync(Bitmap bitmap) {

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                // Use generated instance
                //work with the palette here
                int defaultValue = 0x000000;
                int vibrant = palette.getVibrantColor(defaultValue);
                int vibrantLight = palette.getLightVibrantColor(defaultValue);
                int vibrantDark = palette.getDarkVibrantColor(defaultValue);
                int muted = palette.getMutedColor(defaultValue);
                int mutedLight = palette.getLightMutedColor(defaultValue);
                int mutedDark = palette.getDarkMutedColor(defaultValue);

                vibrantView.setBackgroundColor(vibrant);
                vibrantLightView.setBackgroundColor(vibrantLight);
                vibrantDarkView.setBackgroundColor(vibrantDark);
                mutedView.setBackgroundColor(muted);
                mutedLightView.setBackgroundColor(mutedLight);
                mutedDarkView.setBackgroundColor(mutedDark);
            }
        });
    }

    /*  Open Grid Activity  */
    public void gridPaletteExample(View view) {
        startActivity(new Intent(this, RecyclerView_Activity.class));
    }
}

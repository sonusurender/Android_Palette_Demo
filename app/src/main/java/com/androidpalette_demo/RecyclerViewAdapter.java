package com.androidpalette_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by sonu on 27/03/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static class DemoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        DemoViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.custom_image_view);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }

    }


    private ArrayList<RecyclerItemModel> recyclerItemModelArrayList;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerItemModel> recyclerItemModelArrayList) {
        this.recyclerItemModelArrayList = recyclerItemModelArrayList;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_custom_row, parent, false);
        return new DemoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RecyclerItemModel model = recyclerItemModelArrayList.get(position);

        if (holder instanceof DemoViewHolder) {
            ((DemoViewHolder) holder).textView.setText(model.getLabel());//set text label

            //Use picasso or any other image library to load image url
            Picasso.with(context)
                    .load(model.getImageUrl())
                    .resize(300, 300)//use resize if yoou are using centreCrop
                    .centerCrop()
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            //When bitmap loaded successfully display bitmap over image view and generate palette
                            ((DemoViewHolder) holder).imageView.setImageBitmap(bitmap);
                            Palette.from(bitmap)
                                    .generate(new Palette.PaletteAsyncListener() {
                                        @Override
                                        public void onGenerated(Palette palette) {
                                            Palette.Swatch textSwatch = palette.getVibrantSwatch();
                                            //Check if swatch is null or not
                                            if (textSwatch == null) {
                                                //If null display toast
                                                Toast.makeText(context, "Got Null swatch !!", Toast.LENGTH_SHORT).show();
                                                return;
                                            }

                                            ((DemoViewHolder) holder).textView.setTextColor(textSwatch.getTitleTextColor());//set title text color
                                            ((DemoViewHolder) holder).textView.setBackgroundColor(textSwatch.getRgb());//set text background color or root background color

                                            // textSwatch.getBodyTextColor(); //Set the body text color if you need
                                        }
                                    });
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });
        }

    }


    @Override
    public int getItemCount() {
        return recyclerItemModelArrayList.size();
    }
}

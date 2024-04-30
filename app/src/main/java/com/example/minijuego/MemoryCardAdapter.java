package com.example.minijuego;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MemoryCardAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Integer> mCards;
    private boolean[] mCardFlips;

    public MemoryCardAdapter(Context context, ArrayList<Integer> cards) {
        mContext = context;
        mCards = cards;
        mCardFlips = new boolean[cards.size()];
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        if (mCardFlips[position]) {
            imageView.setImageResource(mCards.get(position));
        } else {
            imageView.setImageResource(R.drawable.card_back); // Fondo de la tarjeta
        }

        return imageView;
    }

    public void flipCard(int position) {
        mCardFlips[position] = !mCardFlips[position];
        notifyDataSetChanged();
    }
}


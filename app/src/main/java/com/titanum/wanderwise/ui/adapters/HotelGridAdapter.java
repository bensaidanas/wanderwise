package com.titanum.wanderwise.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.titanum.wanderwise.R;
import com.titanum.wanderwise.data.models.Hotel;

import java.util.List;

public class HotelGridAdapter extends BaseAdapter {
    private Context context;
    private List<Hotel> hotels;

    public HotelGridAdapter(Context context) {
        this.context = context;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hotels != null ? hotels.size() : 0;
    }

    @Override
    public Hotel getItem(int position) {
        return hotels != null ? hotels.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotelGridAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_card, parent, false);
            viewHolder = new HotelGridAdapter.ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.textView = convertView.findViewById(R.id.titleTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HotelGridAdapter.ViewHolder) convertView.getTag();
        }

        Hotel hotel = getItem(position);
        if (hotel != null) {
            viewHolder.textView.setText(hotel.getName());

            Glide.with(context)
                    .load(hotel.getImageUrl())
                    .centerCrop()
                    .into(viewHolder.imageView);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}

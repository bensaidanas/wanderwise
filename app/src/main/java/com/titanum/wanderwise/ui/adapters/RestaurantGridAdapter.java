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
import com.titanum.wanderwise.data.models.Restaurant;

import java.util.List;

public class RestaurantGridAdapter extends BaseAdapter {
    private Context context;
    private List<Restaurant> restaurants;

    public RestaurantGridAdapter(Context context) {
        this.context = context;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    @Override
    public Restaurant getItem(int position) {
        return restaurants != null ? restaurants.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RestaurantGridAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_card, parent, false);
            viewHolder = new RestaurantGridAdapter.ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.textView = convertView.findViewById(R.id.titleTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RestaurantGridAdapter.ViewHolder) convertView.getTag();
        }

        Restaurant restaurant = getItem(position);
        if (restaurant != null) {
            viewHolder.textView.setText(restaurant.getName());

            Glide.with(context)
                    .load(restaurant.getImageUrl())
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

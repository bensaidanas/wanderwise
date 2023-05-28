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
import com.titanum.wanderwise.data.models.PlaceToVisit;

import java.util.List;

public class PlaceGridAdapter extends BaseAdapter {
    private Context context;
    private List<PlaceToVisit> places;

    public PlaceGridAdapter(Context context) {
        this.context = context;
    }

    public void setPlaces(List<PlaceToVisit> places) {
        this.places = places;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return places != null ? places.size() : 0;
    }

    @Override
    public PlaceToVisit getItem(int position) {
        return places != null ? places.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceGridAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_card, parent, false);
            viewHolder = new PlaceGridAdapter.ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.textView = convertView.findViewById(R.id.titleTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PlaceGridAdapter.ViewHolder) convertView.getTag();
        }

        PlaceToVisit place = getItem(position);
        if (place != null) {
            viewHolder.textView.setText(place.getName());

            Glide.with(context)
                    .load(place.getImageUrl())
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

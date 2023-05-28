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
import com.titanum.wanderwise.data.models.Country;

import java.util.List;

public class CountryGridAdapter extends BaseAdapter {
    private Context context;
    private List<Country> countries;

    public CountryGridAdapter(Context context) {
        this.context = context;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return countries != null ? countries.size() : 0;
    }

    @Override
    public Country getItem(int position) {
        return countries != null ? countries.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_card, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.textView = convertView.findViewById(R.id.titleTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Country country = getItem(position);
        if (country != null) {
            viewHolder.textView.setText(country.getName());

            Glide.with(context)
                    .load(country.getImageUrl())
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

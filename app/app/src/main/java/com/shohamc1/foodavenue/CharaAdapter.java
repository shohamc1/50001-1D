package com.shohamc1.foodavenue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder> implements Filterable {
    private LinkedList<FoodData> mFoodData;
    private LinkedList<FoodData> mFilterList;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public CharaAdapter(LinkedList<FoodData> dishnames) {
        this.mFoodData = dishnames;
        this.mFilterList = dishnames;
        //this.images = images;
    }

    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);
        return new CharaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder holder, final int position) {
        FoodData foodData = mFilterList.get(position);
        holder.text.setText(foodData.dishName);
        holder.image.setImageResource(foodData.imageId);
        holder.rating.setText(foodData.rating.toString());
        //holder.image.setImageResource(images.get(position));
        if (mOnItemClickLitener != null) {
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilterList = mFoodData;
                } else {
                    LinkedList<FoodData> filteredList = new LinkedList<>();
                    for (FoodData foodData : mFoodData) {
                        // add matching rules according to needs
                        if (foodData.dishName.toLowerCase().contains(charString)) {
                            filteredList.add(foodData);
                        }
                        if (foodData.cuisine.toLowerCase().contains(charString)) {
                            filteredList.add(foodData);
                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (LinkedList<FoodData>) filterResults.values;
                // refresh data
                notifyDataSetChanged();
            }
        };
    }

    //code not shown
    static class CharaViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView text;
        protected TextView rating;
        LinearLayout itemLayout;

        public CharaViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foodimage);
            text = itemView.findViewById(R.id.foodtext);
            rating = itemView.findViewById(R.id.ratingText);
            itemLayout = itemView.findViewById(R.id.food_card);
        }

    }

    public LinkedList<FoodData> getFiltedData() {
        return mFilterList;
    }
}

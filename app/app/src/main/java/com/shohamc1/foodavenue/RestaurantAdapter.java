package com.shohamc1.foodavenue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.LinkedList;


public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    LinkedList<RestaurantData> mRestaurantData;
    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RestaurantAdapter(LinkedList<RestaurantData> restaurantDatas) {
        this.mRestaurantData = restaurantDatas;
        //this.images = images;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, final int position) {
        RestaurantData restaurantData = mRestaurantData.get(position);
        holder.text.setText(restaurantData.name_);
        holder.image.setImageResource(restaurantData.resId);
        holder.rating.setText(restaurantData.rating.toString());
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
        return mRestaurantData.size();
    }

    //code not shown
    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView text;
        protected TextView rating;
        LinearLayout itemLayout;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.RestaurantImage);
            text = itemView.findViewById(R.id.RestaurantName);
            rating = itemView.findViewById(R.id.ratingText);
            itemLayout = itemView.findViewById(R.id.res_card);
        }

    }
}

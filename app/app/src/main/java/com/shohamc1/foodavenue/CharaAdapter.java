package com.shohamc1.foodavenue;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharaAdapter extends RecyclerView.Adapter<CharaAdapter.CharaViewHolder>{
    @NonNull
    @Override
    public CharaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View itemView = mInflater.inflate(R.layout.food_card, viewGroup, false );
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CharaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //code not shown
    static class CharaViewHolder extends RecyclerView.ViewHolder{
        public CharaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        //code not shown
    }
}

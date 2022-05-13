package com.example.tara.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tara.Models.ExploreModel;
import com.example.tara.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ExploreModelAdapter extends FirebaseRecyclerAdapter<ExploreModel, ExploreModelAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ExploreModelAdapter(@NonNull FirebaseRecyclerOptions<ExploreModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ExploreModel model) {
        holder.bmy.setText(model.getBmy());
        holder.city.setText(model.getCity());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getCarImage())
                .placeholder(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView bmy, city , price;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.ivCarDisplay);
            bmy = (TextView)itemView.findViewById(R.id.tvBMY);
            city = (TextView)itemView.findViewById(R.id.tvLocation);
            price = (TextView)itemView.findViewById(R.id.tvPricing);

        }
    }
}

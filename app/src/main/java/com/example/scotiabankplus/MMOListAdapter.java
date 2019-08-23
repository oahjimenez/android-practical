package com.example.scotiabankplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MMOListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<MMOItem> items;
    private Context context;

    public MMOListAdapter(Context context, List<MMOItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder itemsViewHolder, int i) {

        TextView titleView = itemsViewHolder.itemView.findViewById(R.id.title);
        titleView.setText(items.get(i).getTitle());

        ImageView thumbnailImageView = itemsViewHolder.itemView.findViewById(R.id.thumbnail);
        Glide.with(context).load(items.get(i).getThumbnail()).into(thumbnailImageView);

        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imprime el padre, en este caso el recyclerview
                System.out.println(itemsViewHolder.itemView.getParent());
            }
        });

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.mmo_item, viewGroup, false);

        return new ListAdapter.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;

        public ViewHolder(View v) {
            super(v);
            vTitle = v.findViewById(R.id.title);
        }
    }
}

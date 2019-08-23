package com.example.scotiabankplus;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class MMOListAdapter extends RecyclerView.Adapter<MMOListAdapter.ViewHolder> {

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
    public void onBindViewHolder(final MMOListAdapter.ViewHolder itemsViewHolder, int i) {
        TextView tabTitleTextView = itemsViewHolder.itemView.findViewById(R.id.tabTitle);
        tabTitleTextView.setText(items.get(i).getTitle());

        ImageView publicationDateView = itemsViewHolder.itemView.findViewById(R.id.publicationDateImage);
        Glide.with(context).load(R.drawable.calendar_icon).dontAnimate().override(50, 50).into(publicationDateView);

        TextView publicationDateTextView = itemsViewHolder.itemView.findViewById(R.id.publicationDate);
        publicationDateTextView.setText(items.get(i).getPubDate());

        TextView titleView = itemsViewHolder.itemView.findViewById(R.id.title);
        titleView.setText(items.get(i).getTitle());

        TextView descriptionView = itemsViewHolder.itemView.findViewById(R.id.description);
        descriptionView.setText(Html.fromHtml(items.get(i).getDescription()));

        ImageView thumbnailImageView = itemsViewHolder.itemView.findViewById(R.id.thumbnail);
        Glide.with(context).load(items.get(i).getThumbnail()).into(thumbnailImageView);

        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsViewHolder.ep.toggle(true);
                System.out.println(itemsViewHolder.itemView.getParent());
            }
        });

    }

    @Override
    public MMOListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.mmo_item, viewGroup, false);

        return new MMOListAdapter.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected ExpandableLayout ep;

        public ViewHolder(View v) {
            super(v);
            ep = v.findViewById(R.id.expandable_layout);
            vTitle = v.findViewById(R.id.title);
        }
    }
}

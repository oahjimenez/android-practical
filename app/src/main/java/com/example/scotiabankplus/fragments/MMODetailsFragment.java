package com.example.scotiabankplus.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.scotiabankplus.MMOItem;
import com.example.scotiabankplus.R;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MMODetailsFragment extends Fragment {

    final static String INDEX_KEY = "selectedIndex";
    final static int DEFAULT_INDEX = 0;

    @Getter
    @Setter
    static List<MMOItem> mmoItems;

    public static MMODetailsFragment newInstance(List<MMOItem> mmoItems,int index) {
        MMODetailsFragment.mmoItems = mmoItems;
        MMODetailsFragment fragment = new MMODetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(INDEX_KEY, index);
        fragment.setArguments(args);

        return fragment;
    }

    public static MMODetailsFragment newInstance(List<MMOItem> mmoItems) {
        MMODetailsFragment.mmoItems = mmoItems;
        MMODetailsFragment fragment = new MMODetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(INDEX_KEY, DEFAULT_INDEX);
        fragment.setArguments(args);

        return fragment;
    }

    public int getShownIndex() {
        return getArguments().getInt(INDEX_KEY, DEFAULT_INDEX);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container== null) {
            return null;
        }
        View mmoItemView = inflater.inflate(R.layout.mmo_item,container,false);
        TextView tabTitleTextView = mmoItemView.findViewById(R.id.tabTitle);
        tabTitleTextView.setText(mmoItems.get(getShownIndex()).getTitle());

        ImageView publicationDateView = mmoItemView.findViewById(R.id.publicationDateImage);
        Glide.with(mmoItemView.getContext()).load(R.drawable.calendar_icon).dontAnimate().override(50, 50).into(publicationDateView);

        TextView publicationDateTextView = mmoItemView.findViewById(R.id.publicationDate);
        publicationDateTextView.setText(mmoItems.get(getShownIndex()).getPubDate());

        TextView titleView = mmoItemView.findViewById(R.id.title);
        titleView.setText(mmoItems.get(getShownIndex()).getTitle());

        TextView descriptionView = mmoItemView.findViewById(R.id.description);
        descriptionView.setText(Html.fromHtml(mmoItems.get(getShownIndex()).getDescription()));

        ImageView thumbnailImageView = mmoItemView.findViewById(R.id.thumbnail);
        Glide.with(mmoItemView.getContext()).load(mmoItems.get(getShownIndex()).getThumbnail()).into(thumbnailImageView);

        return mmoItemView;
    }
}

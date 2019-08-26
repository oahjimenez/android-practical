package com.example.scotiabankplus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scotiabankplus.MMOListAdapter;
import com.example.scotiabankplus.R;
import com.example.scotiabankplus.model.MMOFeed;

import java.util.ArrayList;

public class MMOFeedFragment extends Fragment {

    protected MMOFeed mmoFeed;
    protected MMOListAdapter mmoListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rss_loader,container,false);

        mmoFeed = new MMOFeed();
        mmoFeed.setContext(container.getContext());

        final RecyclerView recyclerView = view.findViewById(R.id.rssView);
        recyclerView.setAdapter(new MMOListAdapter(container.getContext(), new ArrayList<>()));
        LinearLayoutManager llmanager = new LinearLayoutManager(container.getContext());

        llmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        recyclerView.setHasFixedSize(true);
        mmoFeed.populate(items -> {
            mmoListAdapter = new MMOListAdapter(container.getContext(), items);
            recyclerView.setAdapter(mmoListAdapter);
        });
        return view;
    }
}

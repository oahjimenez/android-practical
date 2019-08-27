package com.example.scotiabankplus.activity.sodiacsign;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.scotiabankplus.model.SodiacSignCollection;

public class SodiacSignDetailsFragment extends Fragment {

    public static final String SELECTED_INDEX = "selectedIndex";
    final int FIRST_ELEMENT = 0;
    protected SodiacSignCollection sodiacSigns = SodiacSignCollection.getInstance();


    public static SodiacSignDetailsFragment newInstance(int index) {
        SodiacSignDetailsFragment fragment = new SodiacSignDetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(SELECTED_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public int getShownIndex() {
        return getArguments().getInt(SELECTED_INDEX, FIRST_ELEMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(sodiacSigns.getSodiacSigns().get(getShownIndex()).getFechaSigno());
        return scroller;
    }
}

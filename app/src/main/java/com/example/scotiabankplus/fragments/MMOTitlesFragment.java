package com.example.scotiabankplus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.scotiabankplus.MMODetailsActivity;
import com.example.scotiabankplus.R;
import com.example.scotiabankplus.model.MMOFeed;

import lombok.Getter;
import lombok.Setter;

public class MMOTitlesFragment extends ListFragment {
    boolean isDualPane;
    int selectedIndex = 0;

    final String SELECTED_INDEX_KEY = "selectedIndex";
    final int DEFAULT_INDEX = 0;

    @Getter
    @Setter
    static MMOFeed feed;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, feed.getTitles()));
        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = getActivity().findViewById(R.id.details);
        isDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX_KEY, DEFAULT_INDEX);
        }

        if (isDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(selectedIndex);
        }
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int index) {
        selectedIndex = index;

        if (isDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            MMODetailsFragment details = (MMODetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = MMODetailsFragment.newInstance(feed.getMmoItems(), index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //if (index == 0) {
                ft.replace(R.id.details, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent(getActivity(), MMODetailsActivity.class);
            //intent.setClass();
            intent.putExtra(SELECTED_INDEX_KEY, index);
            startActivity(intent);
        }
    }

}

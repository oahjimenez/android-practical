package com.example.scotiabankplus.activity.sodiacsign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.example.scotiabankplus.R;
import com.example.scotiabankplus.model.SodiacSignCollection;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SodiacSignsFragment  extends ListFragment {


    final int FIRST_ELEMENT = 0;
    int selectedIndex = 0;
    public final String SELECTED_INDEX_KEY = "";
    public static final String SELECTED_INDEX = "selectedIndex";
    final Boolean CHECKED = true;

    boolean isDualPane;
    protected ArrayAdapter<String> listAdapter;
    protected SodiacSignCollection sodiacSigns = SodiacSignCollection.getInstance();

    public SodiacSignsFragment(SodiacSignCollection sodiacSigns) {
        this.sodiacSigns = sodiacSigns;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Populate list with our static array of titles.
        listAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,sodiacSigns.getSignNames());
        setListAdapter(listAdapter);

        View landscapeSodiacDetailsFrame = getActivity().findViewById(R.id.signnames);
        isDualPane = (landscapeSodiacDetailsFrame != null) && (landscapeSodiacDetailsFrame.getVisibility() == View.VISIBLE);

        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX_KEY, FIRST_ELEMENT);
        }

        if (isDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(selectedIndex);
        }
    }

    void showDetails(int index) {
        selectedIndex = index;

        if (isDualPane) {
            getListView().setItemChecked(index, CHECKED);

            // Check what fragment is currently shown, replace if needed.
            SodiacSignDetailsFragment sodacSigndetails = (SodiacSignDetailsFragment)
                    getFragmentManager().findFragmentById(R.id.signdetails);
            if (sodacSigndetails == null || sodacSigndetails.getShownIndex() != index) {
                // Make new fragment to show this selection.
                sodacSigndetails = SodiacSignDetailsFragment.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                //if (index == 0) {
                fragmentTransaction.replace(R.id.signdetails, sodacSigndetails);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent(getActivity(), SodiacSignDetailsActivity.class);
            //intent.setClass();
            intent.putExtra(SELECTED_INDEX, index);
            startActivity(intent);
        }
    }
}

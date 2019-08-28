package com.example.scotiabankplus.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scotiabankplus.R;
import com.example.scotiabankplus.model.EmployeeForm;
import com.example.scotiabankplus.model.EmployeeListAdapter;

public class EmployeeDetailsFragment extends Fragment {

    protected static String SELECTED_INDEX = "selectedIndex";
    protected int FIRST_ELEMENT = 0;

    /**
     * Create a new instance of DetailsFragmento, initialized to
     * show the text at 'index'.
     */
    public static EmployeeDetailsFragment newInstance(int index) {
        EmployeeDetailsFragment fragment = new EmployeeDetailsFragment();
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

        View employeeListView = inflater.inflate(R.layout.emplyee_list, container, false);

        final RecyclerView recyclerView = employeeListView.findViewById(R.id.employeelist);
        LinearLayoutManager llmanager = new LinearLayoutManager(container.getContext());

        llmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llmanager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new EmployeeListAdapter(getActivity(), EmployeeForm.getInstance().getEmployees()));
        return employeeListView;
    }
}

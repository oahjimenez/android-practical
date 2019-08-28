package com.example.scotiabankplus.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.scotiabankplus.EmployeesActivity;
import com.example.scotiabankplus.R;
import com.example.scotiabankplus.model.Employee;
import com.example.scotiabankplus.model.EmployeeForm;

public class FormFragment extends Fragment {
    protected boolean isDualPane;
    protected int FIRST_ELEMENT= 0;
    protected int selectedIndex=FIRST_ELEMENT;
    protected String SELECTED_INDEX = "selectedIndex";
    protected String EMPTY_TEXT="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form, container, false);
        Button submitButton = view.findViewById(R.id.formsubmit);
        submitButton.setOnClickListener(buttonView -> {
            EditText names = (EditText) view.findViewById(R.id.formnames);
            if (names!=null && !names.getText().toString().isEmpty()) {
                EditText fullNames = (EditText) view.findViewById(R.id.formfullnames);
                EditText email = (EditText) view.findViewById(R.id.formemail);
                EditText charge = (EditText) view.findViewById(R.id.formcharge);

                Employee employee = Employee.builder().names(names.getText().toString()).fullnames(fullNames.getText().toString()).email(email.getText().toString()).charge(charge.getText().toString()).build();
                EmployeeForm.getInstance().getEmployees().add(employee);
                names.setText(EMPTY_TEXT);
                fullNames.setText(EMPTY_TEXT);
                email.setText(EMPTY_TEXT);
                charge.setText(EMPTY_TEXT);
                showDetails(EmployeeForm.getInstance().getEmployees().indexOf(employee));

            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("FormFragment onActivityCreated lifecycle");

        View detailsFrame = getActivity().findViewById(R.id.employeesdetails);
        isDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX, FIRST_ELEMENT);
        }

        if (isDualPane) {
            // Make sure our UI is in the correct state.
            showDetails(FIRST_ELEMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_INDEX, selectedIndex);
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int index) {
        System.out.println("FormFragment showing details");
        selectedIndex = index;

        if (isDualPane) {
            System.out.println("FormFragment showDetails isDualPane...");

            // Check what fragment is currently shown, replace if needed.
            EmployeeDetailsFragment details = (EmployeeDetailsFragment)
                    getFragmentManager().findFragmentById(R.id.employeesdetails);
            if (details == null || details.getShownIndex() != index) {
                System.out.println("FormFragment making new details fragment...");

                details = EmployeeDetailsFragment.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.employeesdetails, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                System.out.println("Fragment transaction commited...");

            }

        } else {
            System.out.println("FormFragment showDetails vertical Pane ...");
            Intent intent = new Intent(getActivity(), EmployeesActivity.class);
            intent.putExtra(SELECTED_INDEX, index);
            startActivity(intent);
        }
    }
}

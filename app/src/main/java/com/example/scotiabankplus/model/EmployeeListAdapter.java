package com.example.scotiabankplus.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.scotiabankplus.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.ViewHolder> {


    private List<Employee> items;
    private Context context;

    public EmployeeListAdapter(Context context, List<Employee> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final EmployeeListAdapter.ViewHolder itemsViewHolder, int i) {

        TextView fullNamesTextView = (TextView) itemsViewHolder.itemView.findViewById(R.id.employeefullnames);
        fullNamesTextView.setText(items.get(i).getFullnames());


        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsViewHolder.ep.toggle(true);
                System.out.println(itemsViewHolder.itemView.getParent());
            }
        });

    }

    @Override
    public EmployeeListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.mmo_item, viewGroup, false);

        return new EmployeeListAdapter.ViewHolder(itemView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected ExpandableLayout ep;

        public ViewHolder(View v) {
            super(v);
            ep = v.findViewById(R.id.employeeexpandablelayout);
            vTitle = v.findViewById(R.id.employeename);
        }
    }
}

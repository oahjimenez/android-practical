package com.example.scotiabankplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<SodiacSign> items;
    private Context context;

    public ListAdapter(Context context, List<SodiacSign> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder itemsViewHolder, int i) {

        itemsViewHolder.vTitle.setText(items.get(i).getNombre());
        TextView fechaSigno = itemsViewHolder.itemView.findViewById(R.id.fechaSigno);
        fechaSigno.setText(items.get(i).getFechaSigno());

        TextView amor = itemsViewHolder.itemView.findViewById(R.id.amor);
        amor.setText(items.get(i).getAmor());

        TextView dinero = itemsViewHolder.itemView.findViewById(R.id.dinero);
        dinero.setText(items.get(i).getDinero());


        TextView salud = itemsViewHolder.itemView.findViewById(R.id.salud);
        salud.setText(items.get(i).getSalud());

        TextView color = itemsViewHolder.itemView.findViewById(R.id.color);
        color.setText(items.get(i).getColor());

        TextView numero = itemsViewHolder.itemView.findViewById(R.id.numero);
        numero.setText(items.get(i).getNumero());

        System.out.println("Configuring names: " + items.get(i).getNombre());
        itemsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsViewHolder.ep.toggle(true);
                //imprime el padre, en este caso el recyclerview
                System.out.println(itemsViewHolder.itemView.getParent());
            }
        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_list, viewGroup, false);

        return new ViewHolder(itemView);
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

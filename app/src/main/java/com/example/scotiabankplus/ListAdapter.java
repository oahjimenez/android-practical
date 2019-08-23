package com.example.scotiabankplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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

        ImageView fechaImageView = itemsViewHolder.itemView.findViewById(R.id.fechaImage);
        Glide.with(context).load(R.drawable.calendar_icon).dontAnimate().override(50, 50).into(fechaImageView);

        itemsViewHolder.vTitle.setText(items.get(i).getNombre());
        TextView fechaSigno = itemsViewHolder.itemView.findViewById(R.id.fechaSigno);
        fechaSigno.setText(items.get(i).getFechaSigno());

        ImageView amorImageView = itemsViewHolder.itemView.findViewById(R.id.amorImage);
        Glide.with(context).load(R.drawable.heart_icon).dontAnimate().override(50, 50).into(amorImageView);


        TextView amor = itemsViewHolder.itemView.findViewById(R.id.amor);
        amor.setText(items.get(i).getAmor());

        ImageView dineroImageView = itemsViewHolder.itemView.findViewById(R.id.dineroImage);
        Glide.with(context).load(R.drawable.money_icon).dontAnimate().override(50, 50).into(dineroImageView);

        TextView dinero = itemsViewHolder.itemView.findViewById(R.id.dinero);
        dinero.setText(items.get(i).getDinero());

        ImageView saludImageView = itemsViewHolder.itemView.findViewById(R.id.saludImage);
        Glide.with(context).load(R.drawable.health_icon).dontAnimate().override(50, 50).into(saludImageView);

        TextView salud = itemsViewHolder.itemView.findViewById(R.id.salud);
        salud.setText(items.get(i).getSalud());

        ImageView colorView = itemsViewHolder.itemView.findViewById(R.id.colorImage);
        Glide.with(context).load(R.drawable.heart_icon).dontAnimate().override(50, 50).into(colorView);

        TextView color = itemsViewHolder.itemView.findViewById(R.id.color);
        color.setText(items.get(i).getColor());

        ImageView numeroImageView = itemsViewHolder.itemView.findViewById(R.id.numeroImage);
        Glide.with(context).load(R.drawable.luck_icon).dontAnimate().override(50, 50).into(numeroImageView);

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

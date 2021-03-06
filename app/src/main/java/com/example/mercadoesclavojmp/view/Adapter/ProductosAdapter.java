package com.example.mercadoesclavojmp.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mercadoesclavojmp.R;
import com.example.mercadoesclavojmp.model.Producto;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolderProducto> {


    private List<Producto> productosList;
    private ProductosAdapterListener productosAdapterListener;

    public ProductosAdapter(List<Producto> productosList, ProductosAdapterListener listener) {
        this.productosList = productosList;
        this.productosAdapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_producto, parent, false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = this.productosList.get(position);
        holder.onBind(producto);

    }

    @Override
    public int getItemCount() {
        return productosList.size();
    }

    protected class ViewHolderProducto extends RecyclerView.ViewHolder{

        private ImageView imageViewProducto;
        private TextView textViewNombreProducto;
        private TextView textViewPrecioProducto;

        public ViewHolderProducto(@NonNull final View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.celdaImageViewProducto);
            textViewNombreProducto = itemView.findViewById(R.id.celdaTextViewNombreProducto);
            textViewPrecioProducto = itemView.findViewById(R.id.celdaTextViewPrecioProducto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Producto producto = productosList.get(getAdapterPosition());
                    productosAdapterListener.onClick(producto);
                }
            });


        }

        public void onBind(Producto producto) {
            Glide.with(imageViewProducto).load(producto.getThumbnail()).into(imageViewProducto);
            NumberFormat formatt = new DecimalFormat("###,###,###.##");
            String precioString = formatt.format(producto.getPrice());
            textViewNombreProducto.setText(producto.getTitle());
            textViewPrecioProducto.setText("$" + precioString);

        }


        }
        public interface ProductosAdapterListener{
        public void onClick(Producto producto);

    }
}

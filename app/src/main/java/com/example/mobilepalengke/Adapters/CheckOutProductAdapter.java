package com.example.mobilepalengke.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobilepalengke.DataClasses.CheckOutProduct;
import com.example.mobilepalengke.DataClasses.Product;
import com.example.mobilepalengke.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class CheckOutProductAdapter extends RecyclerView.Adapter<CheckOutProductAdapter.ViewHolder> {

    List<CheckOutProduct> checkOutProducts;
    List<Product> products;

    LayoutInflater layoutInflater;

    Context context;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public CheckOutProductAdapter(Context context, List<CheckOutProduct> checkOutProducts, List<Product> products) {
        this.checkOutProducts = checkOutProducts;
        this.products = products;
        this.layoutInflater = LayoutInflater.from(context);

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_check_out_product_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConstraintLayout constraintLayout = holder.constraintLayout;
        ImageView imgProduct = holder.imgProduct;
        TextView tvProductName = holder.tvProductName,
                tvPrice = holder.tvPrice,
                tvQty = holder.tvQty;

        if (products.size() != 0) {
            CheckOutProduct checkOutProduct = checkOutProducts.get(position);
            Product product = products.get(position);

            try {
                Glide.with(context).load(product.getImg()).centerCrop().placeholder(R.drawable.ic_image_blue).
                        error(R.drawable.ic_broken_image_red).into(imgProduct);
            } catch (Exception ex) {}

            tvProductName.setText(product.getName());
            tvPrice.setText(context.getString(R.string.priceValue, checkOutProduct.getTotalPrice()));
            tvQty.setText(context.getString(R.string.checkOutQtyValue, checkOutProduct.getQuantity(),
                    checkOutProduct.getQuantity() <= 1 ? "" : "s"));

            int start = dpToPx(0), end = dpToPx(0), top = dpToPx(0), bottom = dpToPx(0);

            boolean isFirstColumn = (position + 1) % 2 == 1, isLastColumn = (position + 1) % 2 == 0;
            int lastRowFirstItem = checkOutProducts.size() % 2 == 0 ? checkOutProducts.size() - 1 : checkOutProducts.size();
            boolean isFirstRow = position + 1 <= 2, isLastRow = position + 1 >= lastRowFirstItem;

            if (isFirstColumn) start = dpToPx(4);
            if (isLastColumn) end = dpToPx(4);
            if (isFirstRow) top = dpToPx(4);
            if (isLastRow) bottom = dpToPx(4);

            ConstraintLayout.LayoutParams layoutParams =
                    (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
            layoutParams.setMarginStart(start);
            layoutParams.setMarginEnd(end);
            layoutParams.topMargin = top;
            layoutParams.bottomMargin = bottom;
            constraintLayout.setLayoutParams(layoutParams);

            /*backgroundLayout.setOnClickListener(view -> {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productId", product.getId());
                intent.putExtra("isFromCart", true);
                context.startActivity(intent);
            });*/
        }
    }

    @Override
    public int getItemCount() {
        return checkOutProducts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout, backgroundLayout;
        ImageView imgProduct;
        TextView tvProductName, tvPrice, tvQty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            backgroundLayout = itemView.findViewById(R.id.backgroundLayout);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);

            setIsRecyclable(false);
        }
    }

    private int dpToPx(int dp) {
        float px = dp * context.getResources().getDisplayMetrics().density;
        return (int) px;
    }
}

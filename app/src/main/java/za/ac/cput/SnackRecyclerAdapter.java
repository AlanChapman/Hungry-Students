package za.ac.cput;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.ac.cput.domain.Snack;
import za.ac.cput.domain.Transaction;
import za.ac.cput.domain.TransactionType;

public class SnackRecyclerAdapter extends RecyclerView.Adapter<SnackRecyclerAdapter.MyViewHolder>  {

    private List<Snack> snackList;
    private OnSnackClickListener onSnackClickListener;
    private Context context;

    public SnackRecyclerAdapter(Context context, List<Snack> snackList) {
        this.snackList = snackList;
        this.context = context;
    }

    public SnackRecyclerAdapter(Context context, List<Snack> snackList, OnSnackClickListener OnSnackClickListener) {
        this.snackList = snackList;
        this.context = context;
        this.onSnackClickListener = OnSnackClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.snack_item_card, parent, false);
        return new MyViewHolder(view, onSnackClickListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Snack snack  = snackList.get(position);
        System.out.println(snack);

        holder.snackNameTextView.setText(snack.getName());
        holder.snackPriceTextView.setText("R" + snack.getPrice());
        holder.snackCaloriesTextView.setText(String.valueOf(snack.getCalories()));
        holder.snackCategoryTextView.setText(snack.getCategory());


    }


    @Override
    public int getItemCount() {
        return snackList.size();
    }

    public void refreshList(List<Snack> snackList) {
        this.snackList = snackList;
        notifyDataSetChanged();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnSnackClickListener onSnackClickListener;
        TextView snackNameTextView, snackPriceTextView, snackCaloriesTextView, snackCategoryTextView;


        public MyViewHolder(@NonNull View itemView, OnSnackClickListener onSnackClickListener) {
            super(itemView);

            snackNameTextView = itemView.findViewById(R.id.snackNameTextView);
            snackPriceTextView = itemView.findViewById(R.id.snackPriceTextView);
            snackCaloriesTextView = itemView.findViewById(R.id.snackCaloriesTextView);
            snackCategoryTextView = itemView.findViewById(R.id.snackCategoryTextView);
            this.onSnackClickListener = onSnackClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSnackClickListener.onSnackClick(view, getLayoutPosition());
        }
    }


    public interface OnSnackClickListener {
        void onSnackClick(View view, int position);
    }
}
 package za.ac.cput;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Transaction;

 public class PointsHistoryRecyclerAdapter extends RecyclerView.Adapter<PointsHistoryRecyclerAdapter.MyViewHolder>  {

    private List<Transaction> transactionList;
    private Context context;

    public PointsHistoryRecyclerAdapter(Context context, List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_item_card, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Transaction transaction = transactionList.get(position);

        holder.transactionTitleTextView.setText(transaction.getTitle());
        holder.transactionDateTextView.setText(transaction.getCreatedAt().toLocalDate().toString());

        if(transaction.getPoints() < 0) {
            holder.transactionPointsTextView.setText(new StringBuilder().append(String.valueOf(transaction.getPoints())).append(" pts"));
        } else {
            holder.transactionPointsTextView.setText(new StringBuilder().append("+ ").append(String.valueOf(transaction.getPoints())).append(" pts"));
        }

        if(transaction.isSuccessful()) {
            holder.transactionStatusTextView.setText("success");
            holder.transactionStatusTextView.setTextColor(Color.GREEN);
        } else {
            holder.transactionStatusTextView.setText("failure");
            holder.transactionStatusTextView.setTextColor(Color.RED);
        }

    }


    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public void refreshList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView transactionTitleTextView, transactionDateTextView, transactionPointsTextView, transactionStatusTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            transactionTitleTextView = itemView.findViewById(R.id.transactionTitleTextView);
            transactionDateTextView = itemView.findViewById(R.id.transactionDateTextView);
            transactionPointsTextView = itemView.findViewById(R.id.transactionPointsTextView);
            transactionStatusTextView = itemView.findViewById(R.id.transactionStatusTextView);
        }
    }


    public interface OnProjectClickListener {
        void onProjectClick(View view, int position);
        void onProjectLongClick(View view, int position);
    }
}
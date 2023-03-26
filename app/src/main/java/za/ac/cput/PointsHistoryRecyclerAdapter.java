 package za.ac.cput;


import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.ac.cput.domain.Objective;

public class PointsHistoryRecyclerAdapter extends RecyclerView.Adapter<PointsHistoryRecyclerAdapter.MyViewHolder>  {

    private List<Objective> objectiveList;
    private OnProjectClickListener onProjectClickListener;
    private Context context;

    public PointsHistoryRecyclerAdapter(Context context, List<Objective> objectiveList, OnProjectClickListener onProjectClickListener) {
        this.objectiveList = objectiveList;
        this.context = context;
        this.onProjectClickListener = onProjectClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.objective_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Objective objective = objectiveList.get(position);

        holder.objectiveTitleTextView.setText(objective.getTitle());
        holder.objectiveDescriptionTextView.setText(objective.getDescription());
        holder.objectivePointsTextView.setText(new StringBuilder().append("+ ").append(String.valueOf(objective.getPoints())).append(" points"));
    }


    @Override
    public int getItemCount() {
        return objectiveList.size();
    }

    public void refreshList(List<Objective> objectiveList) {
        this.objectiveList = objectiveList;
        notifyDataSetChanged();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        TextView objectiveTitleTextView, objectiveDescriptionTextView, objectivePointsTextView;
        OnProjectClickListener onProjectClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            objectiveTitleTextView = itemView.findViewById(R.id.objectiveTitleTextView);
            objectiveDescriptionTextView = itemView.findViewById(R.id.objectiveDescriptionTextView);
            objectivePointsTextView = itemView.findViewById(R.id.objectivePointsTextView);
        }

        @Override
        public void onClick(View view) {
            onProjectClickListener.onProjectClick(view, getLayoutPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            onProjectClickListener.onProjectLongClick(view, getAdapterPosition());
            return true;
        }
    }


    public interface OnProjectClickListener {
        void onProjectClick(View view, int position);
        void onProjectLongClick(View view, int position);
    }
}
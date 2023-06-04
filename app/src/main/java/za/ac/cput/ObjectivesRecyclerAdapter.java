package za.ac.cput;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class ObjectivesRecyclerAdapter extends RecyclerView.Adapter<ObjectivesRecyclerAdapter.MyViewHolder>  {

    private List<Objective> objectiveList;
    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private OnProjectClickListener onProjectClickListener;
    private Student authenticatedStudent;
    private StudentRepositoryImpl studentRepository;
    private Context context;

    public ObjectivesRecyclerAdapter(Context context, List<Objective> objectiveList, OnProjectClickListener onProjectClickListener) {
        this.objectiveList = objectiveList;
        this.context = context;
        this.onProjectClickListener = onProjectClickListener;
        studentRepository = new StudentRepositoryImpl(context.getApplicationContext());
        studentObjectiveRepository = new StudentObjectiveRepositoryImpl(context.getApplicationContext());

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.objective_item_card, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Objective objective = objectiveList.get(position);

        StudentObjective studentObjective = new StudentObjective.Builder()
                .setObjectiveId(objective.getObjectiveId())
                .setStudentId(1)
                .build();

        if(studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective)) {
            System.out.println("Completed obj: " + objective.getObjectiveId());
            holder.objectiveItemCardLinearLayout.setBackgroundColor(Color.rgb(0, 230, 77));
            holder.objectiveTitleTextView.setTextColor(Color.WHITE);
            holder.objectiveDescriptionTextView.setTextColor(Color.WHITE);
            holder.objectivePointsTextView.setTextColor(Color.WHITE);
        }

        holder.objectiveTitleTextView.setText(objective.getTitle());
        holder.objectiveDescriptionTextView.setText(objective.getDescription());
        holder.objectivePointsTextView.setText(new StringBuilder().append("+ ").append(String.valueOf(objective.getPoints())).append(" pts"));
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

        LinearLayout objectiveItemCardLinearLayout;
        TextView objectiveTitleTextView, objectiveDescriptionTextView, objectivePointsTextView;
        OnProjectClickListener onProjectClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            objectiveItemCardLinearLayout = itemView.findViewById(R.id.objectiveItemCardLinearLayout);
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

package za.ac.cput;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import za.ac.cput.domain.Objective;

public class HomeFragment extends Fragment implements View.OnClickListener{


    private Button buyPointsBtn, pointsHistoryBtn;
    private RecyclerView objectiveRecyclerView;
    private ObjectivesRecyclerAdapter objectivesRecyclerAdapter;

    private List<Objective> objectiveList = List.of(
            new Objective("Donate points", "Donate points to a friend", 250, false),
            new Objective("Add a bank card", "Add a bank card to your account", 250, false),
            new Objective("Spend 5000 points", "Spend your first 5000 points", 250, true),
            new Objective("Spend 10 000 points", "Spend your first 10 000 points", 250, false),
            new Objective("Spend 10 000 points", "Spend your first 10 000 points", 250, true),
            new Objective("Spend 10 000 points", "Spend your first 10 000 points", 250, false)
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null);

        buyPointsBtn = view.findViewById(R.id.buyPointsBtn);
        pointsHistoryBtn = view.findViewById(R.id.pointsHistoryBtn);

        pointsHistoryBtn.setOnClickListener(this);
        buyPointsBtn.setOnClickListener(this);

        buildRecyclerView(view);
        return view;
    }


    private void buildRecyclerView(View v) {
        objectiveRecyclerView = v.findViewById(R.id.objectiveRecyclerView);
        objectiveRecyclerView.setHasFixedSize(true);
        objectiveRecyclerView.setLayoutManager(new LinearLayoutManager(HomeFragment.this.getActivity()));
        objectivesRecyclerAdapter = new ObjectivesRecyclerAdapter(HomeFragment.this.getActivity(), objectiveList, null);
        objectiveRecyclerView.setAdapter(objectivesRecyclerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view == buyPointsBtn) {
           replaceFragment(new DonatePointsFragment());
        } else if(view == pointsHistoryBtn) {
            replaceFragment(new PointsHistoryFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }


}
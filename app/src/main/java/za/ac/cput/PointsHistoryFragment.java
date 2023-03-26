package za.ac.cput;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import za.ac.cput.ObjectivesRecyclerAdapter;
import za.ac.cput.R;
import za.ac.cput.domain.Objective;


public class PointsHistoryFragment extends Fragment {

    private RecyclerView pointsHistoryRecyclerView;
    private ObjectivesRecyclerAdapter pointsHistoryRecyclerAdapter;

    private List<Objective> objectiveList = List.of(
            new Objective("Donation", "Donate points to a friend", 250, false),
            new Objective("Withdrawal", "Add a bank card to your account", 250, false),
            new Objective("Destinction", "Spend you r first 5000 points", 250, true),
            new Objective("Class Attendence", "Spend your first 10 000 points", 250, false),
            new Objective("Donation", "Spend your first 10 000 points", 250, true),
            new Objective("Food/Voucher", "Spend your first 10 000 points", 250, false)
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_points_history, null);


        buildRecyclerView(view);
        return view;
    }


    private void buildRecyclerView(View v) {
        pointsHistoryRecyclerView = v.findViewById(R.id.objectiveRecyclerView);
        pointsHistoryRecyclerView.setHasFixedSize(true);
        pointsHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(PointsHistoryFragment.this.getActivity()));
        pointsHistoryRecyclerAdapter = new ObjectivesRecyclerAdapter(PointsHistoryFragment.this.getActivity(), objectiveList, null);
        pointsHistoryRecyclerView.setAdapter(pointsHistoryRecyclerAdapter);
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}
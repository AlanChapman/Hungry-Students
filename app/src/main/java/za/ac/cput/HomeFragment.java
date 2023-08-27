package za.ac.cput;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.ObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class HomeFragment extends Fragment implements View.OnClickListener{


    private Button buyPointsBtn, pointsHistoryBtn;
    private RecyclerView objectiveRecyclerView;
    private ObjectivesRecyclerAdapter objectivesRecyclerAdapter;
    private StudentRepositoryImpl studentRepository;
    private String authenticatedStudentName;
    private String authenticatedStudentEmail;
    private int authenticatedStudentId;
    private ObjectiveRepositoryImpl objectiveRepository;
    private Student authenticatedStudent;
    private List<Objective> objectiveList = new ArrayList<>();
    private TextView welcomeStudentTextView, currentPointBalanceTextView;

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null);

        studentRepository = new StudentRepositoryImpl(getActivity());
        studentObjectiveRepository = new StudentObjectiveRepositoryImpl(getActivity());
        authenticatedStudentEmail = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
        authenticatedStudentName = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_NAME);
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);

        authenticatedStudent = studentRepository.getStudent(authenticatedStudentId);
        System.out.println("auth user home frag " + authenticatedStudentEmail);
        System.out.println("stud name home frag" + authenticatedStudentName);
        System.out.println("stud id home frag" + authenticatedStudentId);




        objectiveRepository = new ObjectiveRepositoryImpl(getActivity());

        loadObjectives();


        buyPointsBtn = view.findViewById(R.id.buyPointsBtn);
        pointsHistoryBtn = view.findViewById(R.id.pointsHistoryBtn);
        welcomeStudentTextView = view.findViewById(R.id.welcomeStudentTextView);
        currentPointBalanceTextView = view.findViewById(R.id.currentPointBalanceTextView);

        currentPointBalanceTextView.setText(authenticatedStudent.getPointBalance() + " points");
        welcomeStudentTextView.setText("Welcome " + authenticatedStudentName);
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
            replaceFragment(new TransactionHistoryFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).addToBackStack("tag").commit();
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadObjectives() {
        if(objectiveRepository.getAll().isEmpty()) {
            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Spend 2500 points")
                    .setDescription("This is earned by spending 2500 points")
                    .setPoints(250)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Spend 5000 points")
                    .setDescription("This is earned by spending 5000 points")
                    .setPoints(500)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Spend 7500 points")
                    .setDescription("This is earned by spending 7500 points")
                    .setPoints(750)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Spend 10000 points")
                    .setDescription("This is earned by spending 10000 points")
                    .setPoints(1000)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Donate 2500 points")
                    .setDescription("This is earned by donating 2500 points")
                    .setPoints(250)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Donate 5000 points")
                    .setDescription("This is earned by donating 5000 points")
                    .setPoints(500)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Donate 7500 points")
                    .setDescription("This is earned by donating 7500 points")
                    .setPoints(750)
                    .build());

            objectiveRepository.createObjective(new Objective.Builder()
                    .setTitle("Donate 10000 points")
                    .setDescription("This is earned by donating 10000 points")
                    .setPoints(1000)
                    .build());


        }


        objectiveList = objectiveRepository.getAll();

    }


}
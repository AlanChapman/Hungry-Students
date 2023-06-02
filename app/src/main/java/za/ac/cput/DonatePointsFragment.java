package za.ac.cput;

import static za.ac.cput.utils.Helper.isNullOrEmpty;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class DonatePointsFragment extends Fragment implements View.OnClickListener{

    private CardView donatePointsCardView;
    private EditText donatePointsEmailAddressEditText, donatePointsAmountEditText;
    private TextView donateUserEmailAddress;
    private Button donatePointsBtn, pointsHistoryBtn, donatePointsSearchUserBtn;

    private LinearLayout donatePointsUserNotFoundContainer;

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private StudentRepositoryImpl studentRepository;
    private String authenticatedStudentName;
    private String authenticatedStudentEmail;
    private int authenticatedStudentId;

    private Student student;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate_points, null);
        donatePointsCardView = view.findViewById(R.id.donatePointsCardView);
        donatePointsAmountEditText = view.findViewById(R.id.donatePointsAmountEditText);
        donatePointsEmailAddressEditText = view.findViewById(R.id.donatePointsEmailAddressEditText);
        donatePointsBtn = view.findViewById(R.id.donatePointsBtn);
        pointsHistoryBtn = view.findViewById(R.id.pointsHistoryBtn);
        donatePointsUserNotFoundContainer = view.findViewById(R.id.donatePointsUserNotFoundContainer);
        donatePointsSearchUserBtn = view.findViewById(R.id.donatePointsSearchUserBtn);
        donateUserEmailAddress = view.findViewById(R.id.donateUserEmailAddress);

        studentRepository = new StudentRepositoryImpl(getActivity());
        authenticatedStudentEmail = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
        authenticatedStudentName = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_NAME);
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);


        student = studentRepository.getStudent(authenticatedStudentId);
        donatePointsCardView.setVisibility(View.GONE);

        donatePointsSearchUserBtn.setOnClickListener(this);
        pointsHistoryBtn.setOnClickListener(this);
        donatePointsBtn.setOnClickListener(this);

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if(view == donatePointsSearchUserBtn) {
            searchForStudent();
        } else if(view == pointsHistoryBtn) {
            replaceFragment(new PointsHistoryFragment());
        } else if (view == donatePointsBtn) {
            donatePoints();
        }
    }




    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void donatePoints() {
        //Long amount = Long.parseLong(donatePointsAmountEditText.getText().toString());
        student.setPointBalance((long) (student.getPointBalance() + 2000));
        studentRepository.updateStudentPoints(student);
        System.out.println("Student donae points: " + student);



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void searchForStudent() {
        String studentEmail = donatePointsEmailAddressEditText.getText().toString();
        Student current = studentRepository.getStudent(authenticatedStudentId);
        Student donate;


        if(studentEmail.equals("")) {
            donatePointsEmailAddressEditText.setError("Please enter a value");
        }

        if(studentRepository.existsByEmail(studentEmail)) {
            donatePointsCardView.setVisibility(View.VISIBLE);
            donate = studentRepository.getStudent(studentEmail);
            System.out.println("curr " + current);
            System.out.println("donate " + donate);
            donateUserEmailAddress.setText(studentEmail);
            donatePointsUserNotFoundContainer.setVisibility(View.GONE);
        } else {
            donatePointsCardView.setVisibility(View.GONE);
            donatePointsUserNotFoundContainer.setVisibility(View.VISIBLE);
        }

    }
}
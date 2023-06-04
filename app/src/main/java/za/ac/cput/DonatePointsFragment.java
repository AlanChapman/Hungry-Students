package za.ac.cput;

import static za.ac.cput.utils.Helper.isNullOrEmpty;
import static za.ac.cput.utils.NotificationUtils.sendNotification;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class DonatePointsFragment extends Fragment implements View.OnClickListener{

    private CardView donatePointsCardView;
    private EditText donatePointsEmailAddressEditText, donatePointsAmountEditText;
    private TextView donateUserEmailAddress,currentPointBalanceTextView;
    private Button donatePointsBtn, pointsHistoryBtn, donatePointsSearchUserBtn;

    private LinearLayout donatePointsUserNotFoundContainer;

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private StudentRepositoryImpl studentRepository;
    private String authenticatedStudentName;
    private String authenticatedStudentEmail;
    private int authenticatedStudentId;
    private Student donateToStudent = null;

    private Student authenticatedStudent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate_points, null);
        donatePointsCardView = view.findViewById(R.id.donatePointsCardView);
        donatePointsAmountEditText = view.findViewById(R.id.donatePointsAmountEditText);
        currentPointBalanceTextView = view.findViewById(R.id.currentPointBalanceTextView);
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



        authenticatedStudent = studentRepository.getStudent(authenticatedStudentId);


        currentPointBalanceTextView.setText(authenticatedStudent.getPointBalance() + " points");
        donatePointsCardView.setVisibility(View.GONE);

        donatePointsBtn.setEnabled(false);

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
        String pointsToSend = donatePointsAmountEditText.getText().toString().trim();


        if(authenticatedStudent.getPointBalance()<=0){
            authenticatedStudent.setPointBalance(5000);
            studentRepository.updateStudentPoints(authenticatedStudent);
        }


        System.out.println("auth student : " + authenticatedStudent);
        System.out.println("donate student : " + donateToStudent);
        System.out.println("auth student before: " + authenticatedStudent.getPointBalance());
        System.out.println("donate student before: " + donateToStudent.getPointBalance());

        if(pointsToSend.equals("")) {
            donatePointsAmountEditText.setError("Please enter an amount to send");
            return;
        }
        int parsedPointsToSend = Integer.parseInt(pointsToSend);

        // check negative
        if(authenticatedStudent.getPointBalance() < parsedPointsToSend) {
            Toast.makeText(getActivity(), "Insufficient points balance.", Toast.LENGTH_LONG).show();
            return;
        }

        authenticatedStudent.setPointBalance((int) (authenticatedStudent.getPointBalance() - parsedPointsToSend));
        authenticatedStudent.setDonatedPointsBalance(authenticatedStudent.getDonatedPointsBalance() + parsedPointsToSend);
        donateToStudent.setPointBalance((int)donateToStudent.getPointBalance() + parsedPointsToSend);
        studentRepository.updateStudentPoints(authenticatedStudent);
        studentRepository.updateStudentPoints(donateToStudent);



        System.out.println("auth student after: " + authenticatedStudent.getPointBalance());
        System.out.println("donate student after: " + donateToStudent.getPointBalance());

        Toast.makeText(getActivity(), "You have successfully sent " + pointsToSend + " to " + donateToStudent.getEmailAddress(), Toast.LENGTH_LONG).show();
        donatePointsBtn.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceFragment(new HomeFragment());
            }
        },2000);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void searchForStudent() {
        //String studentEmail = donatePointsEmailAddressEditText.getText().toString().trim();
        String studentEmail = "demo@gmail.com";

        if(studentEmail.equals("")) {
            donatePointsEmailAddressEditText.setError("Please enter a value");
        }

        if(studentRepository.existsByEmail(studentEmail)) {

            if(studentEmail.equalsIgnoreCase(authenticatedStudent.getEmailAddress())) {
                donatePointsCardView.setVisibility(View.GONE);
                donatePointsUserNotFoundContainer.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "You cannot donate points to yourself.", Toast.LENGTH_LONG).show();
                return;
            }
            donatePointsCardView.setVisibility(View.VISIBLE);
            donateToStudent = studentRepository.getStudent(studentEmail);
            donatePointsBtn.setEnabled(true);

            donateUserEmailAddress.setText(studentEmail);
            donatePointsUserNotFoundContainer.setVisibility(View.GONE);
        } else {
            donatePointsBtn.setEnabled(false);
            donatePointsCardView.setVisibility(View.GONE);
            donatePointsUserNotFoundContainer.setVisibility(View.VISIBLE);
        }

    }
}
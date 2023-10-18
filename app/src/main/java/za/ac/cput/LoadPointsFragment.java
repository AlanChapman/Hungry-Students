package za.ac.cput;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.cput.domain.Student;
import za.ac.cput.domain.Transaction;
import za.ac.cput.domain.TransactionType;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.repository.impl.TransactionRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class LoadPointsFragment extends Fragment implements View.OnClickListener {

    private StudentRepositoryImpl studentRepository;

    private Student authenticatedStudent;
    private TransactionRepositoryImpl transactionRepository;
    private EditText etStudentNumber, etPointsAmount;
    private Button btnBuy, btnBack;
    private int authenticatedStudentId;


    public LoadPointsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_load_points, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etStudentNumber = view.findViewById(R.id.etStudentNumber2);
        etPointsAmount = view.findViewById(R.id.etPointsAmount);
        btnBuy = view.findViewById(R.id.btnBuy);
        btnBack = view.findViewById(R.id.btnBack);
        studentRepository = new StudentRepositoryImpl(getActivity());
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);

        authenticatedStudent = studentRepository.getStudent(authenticatedStudentId);
        transactionRepository = new TransactionRepositoryImpl(getActivity());

        btnBuy.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBuy:
                String studentNumber = etStudentNumber.getText().toString();
                String pointsAmount = etPointsAmount.getText().toString();
                Toast.makeText(getActivity(), "stud id." + authenticatedStudentId, Toast.LENGTH_LONG).show();

                if (!studentNumber.isEmpty() && !pointsAmount.isEmpty()) {

                    authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + Integer.parseInt(pointsAmount));
                    studentRepository.updateStudentPoints(authenticatedStudent);

                    transactionRepository.createTransaction(new Transaction(
                            authenticatedStudentId, "Load Points",  0, TransactionType.LOAD, Integer.parseInt(pointsAmount),
                            true, authenticatedStudentId
                    ));
                    Toast.makeText(requireContext(), " Purchase is successful!", Toast.LENGTH_SHORT).show();
                    etStudentNumber.setText("");
                    etPointsAmount.setText("");
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBack:

                replaceFragment(new HomeFragment());
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).addToBackStack("tag").commit();
    }
}





package za.ac.cput;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.impl.StudentRepositoryImpl;

public class StudentDetailsFragment extends Fragment {

    private EditText studentIDField, fullNameField, emailField, DOBField, dateCreatedField, pointsBalanceField, donatedPointsField;
    private Button saveButton;

    private StudentRepositoryImpl studentRepository;
    private Student student;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentRepository = new StudentRepositoryImpl(requireContext());
        // Retrieve the student's data using the studentId (replace 1 with the actual student ID)
        student = studentRepository.getStudent(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_student_details, container, false);

       // studentIDField = rootView.findViewById(R.id.studentIDa);
        fullNameField = rootView.findViewById(R.id.fullNameField);
        emailField = rootView.findViewById(R.id.emailField);
        DOBField = rootView.findViewById(R.id.DOBField);
        dateCreatedField = rootView.findViewById(R.id.dateCreatedField);
        pointsBalanceField = rootView.findViewById(R.id.pointsBalanceField);
        donatedPointsField = rootView.findViewById(R.id.donatedPointsField);
        saveButton = rootView.findViewById(R.id.saveButton);

        // Populate the EditText fields with student data
        if (student != null) {
           // studentIDField.setText(String.valueOf(student.getStudentId()));  // Display Student ID
            fullNameField.setText(student.getFullName());
            emailField.setText(student.getEmailAddress());
            DOBField.setText(student.getDateOfBirth().toString());
            dateCreatedField.setText(student.getCreatedAt().toString());
            pointsBalanceField.setText(String.valueOf(student.getPointBalance()));
            donatedPointsField.setText(String.valueOf(student.getDonatedPointsBalance()));
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        return rootView;
    }

    private void saveChanges() {
        // Retrieve and update student information from EditText fields
        //int studentId = Integer.parseInt(studentIDField.getText().toString());
        String fullName = fullNameField.getText().toString();
        String emailAddress = emailField.getText().toString();
        LocalDate dateOfBirth = LocalDate.parse(DOBField.getText().toString());
        LocalDate createdAt = LocalDate.parse(dateCreatedField.getText().toString());
        int pointsBalance = Integer.parseInt(pointsBalanceField.getText().toString());
        int donatedPointsBalance = Integer.parseInt(donatedPointsField.getText().toString());

        // Update the student object
       // student.setStudentId(studentId);
        student.setFullName(fullName);
        student.setEmailAddress(emailAddress);
        student.setDateOfBirth(dateOfBirth);
        student.setCreatedAt(createdAt);
        student.setPointBalance(pointsBalance);
        student.setDonatedPointsBalance(donatedPointsBalance);

        // Update student information in the database
        Student updatedStudent = studentRepository.updateStudentPoints(student);

        if (updatedStudent != null) {
            Toast.makeText(requireContext(), "Changes saved successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Failed to save changes", Toast.LENGTH_SHORT).show();
        }
    }
}

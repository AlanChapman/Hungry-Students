package za.ac.cput;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadPointsFragment extends Fragment implements View.OnClickListener {

    private EditText etStudentNumber, etPurchaseAmount, etPointsAmount;
    private Button btnBuy, btnBack;

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
        etPurchaseAmount = view.findViewById(R.id.etPurchaseAmount);
        etPointsAmount = view.findViewById(R.id.etPointsAmount);
        btnBuy = view.findViewById(R.id.btnBuy);
        btnBack = view.findViewById(R.id.btnBack);

        btnBuy.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBuy:
                String studentNumber = etStudentNumber.getText().toString();
                String purchaseAmount = etPurchaseAmount.getText().toString();
                String pointsAmount = etPointsAmount.getText().toString();

                if (!studentNumber.isEmpty() && !purchaseAmount.isEmpty() && !pointsAmount.isEmpty()) {
                    // Perform purchase logic here
                    // Replace this with your actual purchase logic

                    Toast.makeText(requireContext(), " Purchase is successful!", Toast.LENGTH_SHORT).show();
                    etStudentNumber.setText("");
                    etPurchaseAmount.setText("");
                    etPointsAmount.setText("");
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnBack:

                requireActivity().onBackPressed();
                break;
        }
    }
}





package za.ac.cput;

import static za.ac.cput.utils.Helper.isNullOrEmpty;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DonatePointsFragment extends Fragment implements View.OnClickListener{

    private CardView donatePointsCardView;
    private EditText donatePointsEmailAddressEditText;
    private Button donatePointsBtn;

    private LinearLayout donatePointsUserNotFoundContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate_points, null);
        donatePointsCardView = view.findViewById(R.id.donatePointsCardView);
        donatePointsEmailAddressEditText = view.findViewById(R.id.donatePointsEmailAddressEditText);
        donatePointsBtn = view.findViewById(R.id.donatePointsBtn);
        donatePointsUserNotFoundContainer = view.findViewById(R.id.donatePointsUserNotFoundContainer);



        donatePointsCardView.setVisibility(View.GONE);

        donatePointsBtn.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if(view == donatePointsBtn) {
            donatePoints();
        }
    }

    private void donatePoints() {
        if(isNullOrEmpty(donatePointsEmailAddressEditText.getText())) {
            donatePointsEmailAddressEditText.setError("Please enter a value");
            donatePointsCardView.setVisibility(View.GONE);
            donatePointsUserNotFoundContainer.setVisibility(View.VISIBLE);
        } else {
            donatePointsCardView.setVisibility(View.VISIBLE);
            donatePointsUserNotFoundContainer.setVisibility(View.GONE);
        }

    }
}
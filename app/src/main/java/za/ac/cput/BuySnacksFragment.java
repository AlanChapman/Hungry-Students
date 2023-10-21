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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.Snack;
import za.ac.cput.domain.Transaction;
import za.ac.cput.domain.TransactionType;
import za.ac.cput.repository.impl.SnackRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.repository.impl.TransactionRepositoryImpl;
import za.ac.cput.utils.DBUtils;


@RequiresApi(api = Build.VERSION_CODES.O)
public class BuySnacksFragment extends Fragment implements SnackRecyclerAdapter.OnSnackClickListener {

    private RecyclerView snackRecyclerView;
    private StudentRepositoryImpl studentRepository;
    private SnackRepositoryImpl snackRepository;
    private String authenticatedStudentEmail;

    private int authenticatedStudentId;
    private SnackRecyclerAdapter adapter;

    private int snackId;

    private LinearLayout snackItemCardLinearLayout;
    private List<Snack> snackList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_buy_snacks, null);
        //snackItemCardLinearLayout = view.findViewById(R.id.snackItemCardLinearLayout);
        snackRepository = new SnackRepositoryImpl(getActivity());
        studentRepository = new StudentRepositoryImpl(getActivity());
        authenticatedStudentId = getActivity().getIntent().getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);
        authenticatedStudentEmail = getActivity().getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);



        System.out.println("get all snacks");
        System.out.println("authenticatedStudentId " + authenticatedStudentId);
        System.out.println("authenticatedStudentEmail " + authenticatedStudentEmail);
        //System.out.println(s.getAllTransactions(authenticatedStudentId));
        //snackList = sn.getAllTransactions(authenticatedStudentId);

        snackList = List.of(
                new Snack(1, "Simba Chips", 22.50, "Chips", 240),
                new Snack(2, "Simba Chips", 22.50, "Chips", 240),
                new Snack(3, "Simba Chips", 22.50, "Chips", 240),
                new Snack(4, "Simba Chips", 22.50, "Chips", 240),
                new Snack(5, "Simba Chips", 22.50, "Chips", 240),
                new Snack(6, "Simba Chips", 22.50, "Chips", 240),
                new Snack(7, "Simba Chips", 22.50, "Chips", 240),
                new Snack(8, "Simba Chips", 22.50, "Chips", 240),
                new Snack(9, "Simba Chips", 22.50, "Chips", 240),
                new Snack(10, "Simba Chips", 22.50, "Chips", 240),
                new Snack(11, "Simba Chips", 22.50, "Chips", 240)
        );

        buildRecyclerView(view);
        return view;
    }



    private void buildRecyclerView(View v) {
        snackRecyclerView = v.findViewById(R.id.snacksRecyclerView);
        snackRecyclerView.setHasFixedSize(true);
        snackRecyclerView.setLayoutManager(new LinearLayoutManager(BuySnacksFragment.this.getActivity()));
        adapter = new SnackRecyclerAdapter(BuySnacksFragment.this.getActivity(), snackList, this);
        snackRecyclerView.setAdapter(adapter);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public void onSnackClick(View view, int position) {
        snackId = snackList.get(position).getId();

        //replaceFragment(new SnackDetailsFragment());

        Intent goToSnackDetailsIntent = new Intent(getActivity(), SnackDetailsActivity.class);
        goToSnackDetailsIntent.putExtra(DBUtils.AUTHENTICATED_STUDENT_NAME, authenticatedStudentId);
        goToSnackDetailsIntent.putExtra("snackId", snackId);
        startActivity(goToSnackDetailsIntent);
    }
}
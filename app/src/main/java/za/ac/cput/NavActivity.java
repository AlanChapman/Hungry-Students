package za.ac.cput;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.repository.impl.ObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.services.ObjectiveAchievedService;
import za.ac.cput.utils.DBUtils;

public class NavActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TextView studentNameTextView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private StudentRepositoryImpl studentRepository;
    private ObjectiveRepositoryImpl objectiveRepository;
    private String authenticatedStudentName;
    private String authenticatedStudentEmail;
    private int authenticatedStudentId;

    private StudentObjectiveRepositoryImpl studentObjectiveRepository;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        authenticatedStudentEmail = getIntent().getStringExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL);
        studentRepository = new StudentRepositoryImpl(this);
        objectiveRepository = new ObjectiveRepositoryImpl(this);
        authenticatedStudentName = studentRepository.getCurrentStudentFirstName(authenticatedStudentEmail);
        authenticatedStudentId = studentRepository.getCurrentStudentId(authenticatedStudentEmail);
        studentObjectiveRepository = new StudentObjectiveRepositoryImpl(this);

        // Test adding into student_objective
        studentObjectiveRepository.create(new StudentObjective.Builder()
                .setObjectiveId(1)
                .setStudentId(authenticatedStudentId)
                .build());

        getIntent().putExtra(DBUtils.AUTHENTICATED_STUDENT_EMAIL, authenticatedStudentEmail);
        getIntent().putExtra(DBUtils.AUTHENTICATED_STUDENT_NAME, authenticatedStudentName);
        getIntent().putExtra(DBUtils.AUTHENTICATED_STUDENT_ID, authenticatedStudentId);
        replaceFragment(new HomeFragment());


        Intent projectServiceIntent = new Intent(this, ObjectiveAchievedService.class);
        projectServiceIntent.putExtra(DBUtils.AUTHENTICATED_STUDENT_ID, authenticatedStudentId);



        if(!isMyServiceRunning(ObjectiveAchievedService.class)) {
            startForegroundService(projectServiceIntent);
        }


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.studentNameTextView);
        TextView currentDateTextView = headerView.findViewById(R.id.currentDateTextView);
        navUsername.setText("Hello " + authenticatedStudentName);

        LocalDate currentDate = LocalDate.now();
        currentDateTextView.setText(currentDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

        toolbar = findViewById(R.id.materialToolBar);



        // open navigation drawer layout when clicking icon in toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // handle item click in navigation view
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);

                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {

                    case R.id.logoutNavMenu:
                        startActivity(new Intent(NavActivity.this, SignUpActivity.class));
                        break;

                    case R.id.donatePointsNavMenu:
                        replaceFragment(new DonatePointsFragment());
                        break;

                    case R.id.viewPointsNavMenu:
                        //replaceFragment(new ViewPointsFragment());
                        break;

                    case R.id.viewPointHistoryNavMenu:
                        replaceFragment(new PointsHistoryFragment());
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });
    }


    // Check if service is running, if it's not then I can start it to continuosly run in foreground
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }



    private void replaceFragment(Fragment fragment) {


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }


}
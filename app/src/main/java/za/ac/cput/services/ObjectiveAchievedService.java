package za.ac.cput.services;

import static za.ac.cput.utils.NotificationUtils.CHANNEL_ID_1;
import static za.ac.cput.utils.NotificationUtils.CHANNEL_ID_2;
import static za.ac.cput.utils.NotificationUtils.sendNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

import za.ac.cput.LoginActivity;
import za.ac.cput.R;
import za.ac.cput.SignUpActivity;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.repository.impl.ObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentObjectiveRepositoryImpl;
import za.ac.cput.repository.impl.StudentRepositoryImpl;
import za.ac.cput.utils.DBUtils;

public class ObjectiveAchievedService extends Service {

    private StudentRepositoryImpl studentRepository;
    private StudentObjectiveRepositoryImpl studentObjectiveRepository;
    private ObjectiveRepositoryImpl objectiveRepository;
    private int authenticatedStudentId;
    private Student authenticatedStudent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        studentRepository = new StudentRepositoryImpl(this);
        studentObjectiveRepository = new StudentObjectiveRepositoryImpl(this);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                authenticatedStudent = studentRepository.getStudent(authenticatedStudentId);
                System.out.println("STUDENT SERVICE onCreate: " + authenticatedStudent);

                if(authenticatedStudent.getPointBalance() >= 2500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(1)
                        .setObjectiveTitle("Spend 2500 points")
                        .build();

                    // Checks if student already completed a specific objective, if they did then make sure not to attempt to add a record
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 250);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Spend 2500 points", "You have been rewarded with 250 points.", getApplicationContext());
                    }
                }

                if(authenticatedStudent.getPointBalance() >= 5000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(2)
                        .setObjectiveTitle("Spend 5000 points")
                        .build();
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 500);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Spend 5000 points", "You have been rewarded with 500 points.", getApplicationContext());
                    }
                }

                if(authenticatedStudent.getPointBalance() >= 7500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(3)
                        .setObjectiveTitle("Spend 7500 points")
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 750);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Spend 7500 points", "You have been rewarded with 750 points.", getApplicationContext());
                    }
                }

                if(authenticatedStudent.getPointBalance() >= 10000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(4)
                        .setObjectiveTitle("Spend 10000 points")
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 1000);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Spend 10000 points", "You have been rewarded with 1000 points.", getApplicationContext());
                    }

                }

                if(authenticatedStudent.getDonatedPointsBalance() >= 2500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(5)
                        .setObjectiveTitle("Donate 2500 points")
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 250);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Donate 2500 points", "You have been rewarded with 250 points.", getApplicationContext());
                    }

                }

                if(authenticatedStudent.getDonatedPointsBalance() >= 5000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(6)
                        .setObjectiveTitle("Donate 5000 points")
                        .build();


                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 500);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Donate 5000 points", "You have been rewarded with 500 points.", getApplicationContext());
                    }

                }

                if(authenticatedStudent.getDonatedPointsBalance() >= 7500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(7)
                        .setObjectiveTitle("Donate 7500 points")
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 750);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Donate 7500 points", "You have been rewarded with 750 points.", getApplicationContext());
                    }

                }

                if(authenticatedStudent.getDonatedPointsBalance() >= 10000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(authenticatedStudent.getStudentId()).setObjectiveId(8)
                        .setObjectiveTitle("Donate 10000 points")
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        authenticatedStudent.setPointBalance(authenticatedStudent.getPointBalance() + 1000);
                        studentRepository.updateStudentPoints(authenticatedStudent);
                        sendNotification("Objective completed - Spend 10000 points", "You have been rewarded with 1000 points.", getApplicationContext());
                    }

                }

            }
        }, 10, 10000);


    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendForegroundNotification(String title, String text) {
        Intent notificationIntent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_2)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.logo_small)
                .setColor(getResources().getColor(R.color.primary_blue))
                .setContentIntent(pendingIntent)
                .build();


        startForeground(1, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("SERVIE RUNNING");
        authenticatedStudentId = intent.getIntExtra(DBUtils.AUTHENTICATED_STUDENT_ID, -999);
        sendForegroundNotification("Hungry Students", "Service is running...");

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
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
    private Student student;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        studentRepository = new StudentRepositoryImpl(this);
        studentObjectiveRepository = new StudentObjectiveRepositoryImpl(this);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                student = studentRepository.getStudent(authenticatedStudentId);
                System.out.println("STUDENT SERVICE onCreate: " + student);

                if(student.getPointBalance() >= 2500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(1)
                            .build();

                    // Checks if student already completed a specific objective, if they did then make sure not to attempt to add a record
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have spent more than 2500 pts.", getApplicationContext());
                    }
                }

                if(student.getPointBalance() >= 5000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(2)
                        .build();
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have spent more than 5000 pts.", getApplicationContext());
                    }
                }

                if(student.getPointBalance() >= 7500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(3)
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have spent more than 7500 pts.", getApplicationContext());
                    }
                }

                if(student.getPointBalance() >= 10000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(4)
                            .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have spent more than 10000 pts.", getApplicationContext());
                    }

                }

                if(student.getDonatedPointsBalance() >= 2500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(5)
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have donated more than 2500 pts.", getApplicationContext());
                    }

                }

                if(student.getDonatedPointsBalance() >= 5000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(6)
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have donated more than 5000 pts.", getApplicationContext());
                    }

                }

                if(student.getDonatedPointsBalance() >= 7500) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(7)
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have donated more than 7500 pts.", getApplicationContext());
                    }

                }

                if(student.getDonatedPointsBalance() >= 10000) {
                    StudentObjective studentObjective = new StudentObjective.Builder().setStudentId(student.getStudentId()).setObjectiveId(8)
                        .build();

                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);

                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                        sendNotification("Objective completed", "You have donated more than 10000 pts.", getApplicationContext());
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
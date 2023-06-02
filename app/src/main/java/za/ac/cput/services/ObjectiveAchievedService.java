package za.ac.cput.services;

import static za.ac.cput.utils.NotificationUtils.CHANNEL_ID_1;
import static za.ac.cput.utils.NotificationUtils.CHANNEL_ID_2;

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

                if(student.getPointBalance() > 2500) {
                    StudentObjective studentObjective = new StudentObjective.Builder()
                            .setStudentId(student.getStudentId())
                            .setObjectiveId(1)
                            .build();

                    // Checks if student already completed a specific objective, if they did then make sure not to attempt to add a record
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);
                    System.out.println(res ? "Student completed obj " + 1 : "Student not complete obj " + 1);
                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                    }

                    System.out.println("SERVICE: SENDS NOTIFICATION FOR > 2500 PTS");
                }

                if(student.getPointBalance() > 5000) {
                    StudentObjective studentObjective = new StudentObjective.Builder()
                            .setStudentId(student.getStudentId())
                            .setObjectiveId(2)
                            .build();
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);
                    System.out.println(res ? "Student completed obj " + 2 : "Student not complete obj " + 2);
                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                    }
                    System.out.println("SERVICE: SENDS NOTIFICATION FOR > 5000 PTS");
                }

                if(student.getPointBalance() > 7500) {
                    StudentObjective studentObjective = new StudentObjective.Builder()
                            .setStudentId(student.getStudentId())
                            .setObjectiveId(3)
                            .build();
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);
                    System.out.println(res ? "Student completed obj " + 3 : "Student not complete obj " + 3);
                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                    }
                    System.out.println("SERVICE: SENDS NOTIFICATION FOR > 7500 PTS");
                }

                if(student.getPointBalance() > 10000) {
                    StudentObjective studentObjective = new StudentObjective.Builder()
                            .setStudentId(student.getStudentId())
                            .setObjectiveId(4)
                            .build();
                    boolean res = studentObjectiveRepository.checkStudentObjectiveCompletion(studentObjective);
                    System.out.println(res ? "Student completed obj " + 4 : "Student not complete obj " + 4);
                    if(!res) {
                        studentObjectiveRepository.create(studentObjective);
                    }
                    System.out.println("SERVICE: SENDS NOTIFICATION FOR > 10000 PTS");
                }


            }
        }, 10, 10000);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification(String title, String description) {
        Intent resultIntent = new Intent(this, LoginActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification.Action action = new Notification.Action.Builder(R.drawable.logo_small, "Open", pendingIntent)
                .build();

        Notification notification = new Notification.Builder(this, CHANNEL_ID_1)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(CHANNEL_ID_1)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setActions(action)
                .build();

        NotificationManager manager = getSystemService(NotificationManager.class);

        manager.notify(1001, notification);
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

        sendNotification("TEST", "TEST DEASCRIPTIN");

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
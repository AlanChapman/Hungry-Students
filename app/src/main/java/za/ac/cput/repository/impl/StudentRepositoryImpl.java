package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import za.ac.cput.domain.Student;
import za.ac.cput.repository.interfaces.IStudentRepository;
import za.ac.cput.utils.DBUtils;

public class StudentRepositoryImpl extends SQLiteOpenHelper implements IStudentRepository {

    private final Context context;

    public StudentRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_TRANSACTION_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_POINT_BALANCE_HISTORY_TABLE_QUERY);
       // db.execSQL(DBUtils.CREATE_SNACK_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_TRANSACTION_TABLE_QUERY);
       db.execSQL(DBUtils.DROP_POINT_BALANCE_HISTORY_QUERY);
       // db.execSQL(DBUtils.DROP_SNACK_TABLE_QUERY);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean register(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        boolean valid = false;

        cv.put(DBUtils.COLUMN_STUDENT_FULL_NAME, student.getFullName());
        cv.put(DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS, student.getEmailAddress());
        cv.put(DBUtils.COLUMN_STUDENT_DATE_OF_BIRTH, student.getDateOfBirth().toString());
        cv.put(DBUtils.COLUMN_STUDENT_CREATED_AT, student.getCreatedAt().toString());
        cv.put(DBUtils.COLUMN_STUDENT_POINTS_BALANCE, 0);
        cv.put(DBUtils.COLUMN_STUDENT_TOTAL_DONATED_POINTS, 0);
        cv.put(DBUtils.COLUMN_STUDENT_PASSWORD, student.getPassword());

        if(existsByEmail(student.getEmailAddress())) {
            Toast.makeText(context.getApplicationContext(), "Student already exists with this Email Address.", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(DBUtils.STUDENT_TABLE, null, cv);
            valid = true;
            Toast.makeText(context.getApplicationContext(), "Your account was created successfully", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    @Override
    public boolean login(String emailAddress, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean valid = false;


        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS, DBUtils.COLUMN_STUDENT_PASSWORD},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS + " = ? ",
                new String[]{emailAddress},//Where clause
                null, null, null);


        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            String theEmailAddress = cursor.getString(0);
            String thePassword = cursor.getString(1);

            boolean passwordMatch = password.equals(thePassword) && emailAddress.equals(theEmailAddress);

            if(passwordMatch) {
                valid = true;
            }

        }

        cursor.close();
        return valid;
    }


    public boolean existsByEmail(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS + " = ?",
                new String[]{emailAddress},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            cursor.close();
            return true;
        }

        return false;
    }


    @Override
    public String getCurrentStudentEmailAddress(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(studentId)},//Where clause
                null, null, null);

        String emailAddress = null;

        while(cursor.moveToNext()) {
            emailAddress = cursor.getString(0);
        }

        cursor.close();
        return emailAddress;
    }

    @Override
    public int getCurrentStudentId(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_ID},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS + " = ?",
                new String[]{String.valueOf(emailAddress)},//Where clause
                null, null, null);

        int userId = -999;

        while(cursor.moveToNext()) {
            userId = cursor.getInt(0);
        }

        cursor.close();
        return userId;

    }

    @Override
    public String getCurrentStudentFirstName(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_FULL_NAME},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS + " = ?",
                new String[]{emailAddress},//Where clause
                null, null, null);

        String firstName = null;

        if(cursor.moveToFirst()) {
            firstName = cursor.getString(0);
            return firstName;

        }
        cursor.close();
        return null;



    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;
        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_ID, DBUtils.COLUMN_STUDENT_FULL_NAME, DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS, DBUtils.COLUMN_STUDENT_DATE_OF_BIRTH,
                        DBUtils.COLUMN_STUDENT_POINTS_BALANCE,   DBUtils.COLUMN_STUDENT_TOTAL_DONATED_POINTS, DBUtils.COLUMN_STUDENT_CREATED_AT},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(id)},//Where clause
                null, null, null);


        if(cursor.moveToNext()) {

            int studentId = cursor.getInt(0);
            String fullName = cursor.getString(1);
            String theEmailAddress = cursor.getString(2);
            String dateOfBirth = cursor.getString(3);
            int pointBalance = cursor.getInt(4);
            int donatedPointsBalance = cursor.getInt(5);
            String createdAt = cursor.getString(6);

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate dateOfBirthFormatted = LocalDate.parse(dateOfBirth, formatter);
            LocalDate createdAtFormatted = LocalDate.parse(createdAt, formatter);

            student = new Student(studentId, fullName, theEmailAddress, dateOfBirthFormatted, createdAtFormatted, pointBalance, donatedPointsBalance);
        }


        cursor.close();
        return student;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Student updateStudentPoints(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.STUDENT_TABLE + " WHERE " + DBUtils.COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getStudentId())});


        if (cursor.getCount() > 0) {
            cv.put(DBUtils.COLUMN_STUDENT_POINTS_BALANCE, student.getPointBalance());
            cv.put(DBUtils.COLUMN_STUDENT_TOTAL_DONATED_POINTS, student.getDonatedPointsBalance());
            db.update(DBUtils.STUDENT_TABLE, cv, DBUtils.COLUMN_STUDENT_ID + " = ?",
                    new String[]{String.valueOf(student.getStudentId())});

            return getStudent(student.getStudentId());
        }

        db.close();

        cursor.close();


        return null;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Student getStudent(String emailAddress) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;
        Cursor cursor = db.query(DBUtils.STUDENT_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_ID, DBUtils.COLUMN_STUDENT_FULL_NAME, DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS, DBUtils.COLUMN_STUDENT_DATE_OF_BIRTH,
                        DBUtils.COLUMN_STUDENT_POINTS_BALANCE, DBUtils.COLUMN_STUDENT_TOTAL_DONATED_POINTS, DBUtils.COLUMN_STUDENT_CREATED_AT},//Selecting columns want to query
                DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS + " = ?",
                new String[]{emailAddress},//Where clause
                null, null, null);


        if(cursor.moveToNext()) {

            int studentId = cursor.getInt(0);
            String fullName = cursor.getString(1);
            String theEmailAddress = cursor.getString(2);
            String dateOfBirth = cursor.getString(3);
            int pointBalance = cursor.getInt(4);
            int donatedPointsBalance = cursor.getInt(5);
            String createdAt = cursor.getString(6);

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDate dateOfBirthFormatted = LocalDate.parse(dateOfBirth, formatter);
            LocalDate createdAtFormatted = LocalDate.parse(createdAt, formatter);

            student = new Student(studentId, fullName, theEmailAddress, dateOfBirthFormatted, createdAtFormatted, pointBalance, donatedPointsBalance);
        }


        cursor.close();
        return student;
    }


    public boolean updateStudentDetails(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBUtils.COLUMN_STUDENT_FULL_NAME, student.getFullName());
        cv.put(DBUtils.COLUMN_STUDENT_DATE_OF_BIRTH, student.getDateOfBirth().toString());
        cv.put(DBUtils.COLUMN_STUDENT_EMAIL_ADDRESS, student.getEmailAddress());
        long result = 0;

        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.STUDENT_TABLE + " WHERE " + DBUtils.COLUMN_STUDENT_ID + " = ?",
                new String[]{String.valueOf(student.getStudentId())});



        if(cursor.getCount()>0) {
            result = db.update(DBUtils.STUDENT_TABLE, cv,DBUtils.COLUMN_STUDENT_ID + " = ?", new String[]{String.valueOf(student.getStudentId())});
        }
        cursor.close();
        db.close();

        return result == -1 ? false : true;
    }


}

package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.repository.interfaces.IStudentObjectiveRepository;
import za.ac.cput.utils.DBUtils;

public class StudentObjectiveRepositoryImpl extends SQLiteOpenHelper implements IStudentObjectiveRepository {

    private Context context;
    public StudentObjectiveRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        onCreate(db);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public StudentObjective create(StudentObjective studentObjective) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID, studentObjective.getObjectiveId());
        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_STUDENT_ID, studentObjective.getStudentId());
        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_TITLE, studentObjective.getObjectiveTitle());
        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED, LocalDateTime.now().toString());
        long result = db.insert(DBUtils.STUDENT_OBJECTIVE_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return studentObjective;
    }

    public boolean checkStudentObjectiveCompletion(StudentObjective studentObjective) {

        SQLiteDatabase db = this.getReadableDatabase();
        //StudentObjective studentObjective = null;

        Cursor cursor = db.rawQuery("SELECT " + DBUtils.COLUMN_STUDENT_OBJECTIVE_STUDENT_ID  + ", " + DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID
                + " FROM " + DBUtils.STUDENT_OBJECTIVE_TABLE
                + " WHERE " + DBUtils.COLUMN_STUDENT_OBJECTIVE_STUDENT_ID + " = ? AND " + DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID +
                " = ?", new String[]{String.valueOf(studentObjective.getStudentId()), String.valueOf(studentObjective.getObjectiveId())});

        if(cursor.moveToNext()) {
            int studentId = cursor.getInt(0);
            int objId = cursor.getInt(1);
            return true;
        }

        cursor.close();

        return false;
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public StudentObjective getStudentObjectiveByObjectiveId(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        StudentObjective studentObjective = null;

        Cursor cursor = db.query(DBUtils.STUDENT_OBJECTIVE_TABLE,// Selecting Table
                new String[]{DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID, DBUtils.COLUMN_STUDENT_OBJECTIVE_STUDENT_ID, DBUtils.COLUMN_STUDENT_OBJECTIVE_TITLE, DBUtils.COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED},
                DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID + " = ?",
                new String[]{String.valueOf(id)},//Where clause
                null, null, null);


        if(cursor.moveToNext()) {
            int objectiveId = cursor.getInt(0);
            int studentId = cursor.getInt(1);
            String objectiveTitle = cursor.getString(2);
            String dateAchieved = cursor.getString(3);

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            LocalDateTime dateAchievedFormatted = LocalDateTime.parse(dateAchieved, formatter);

            studentObjective = new StudentObjective.Builder()
                    .setObjectiveId(objectiveId)
                    .setStudentId(studentId)
                    .setObjectiveTitle(objectiveTitle)
                    .setDateAchieved(dateAchievedFormatted)
                    .build();
        }

        cursor.close();

        return studentObjective;
    }



}

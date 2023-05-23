package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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


    @Override
    public StudentObjective create(StudentObjective studentObjective) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID, studentObjective.getObjectiveId());
        cv.put(DBUtils.COLUMN_STUDENT_OBJECTIVE_STUDENT_ID, studentObjective.getStudentId());

        long result = db.insert(DBUtils.STUDENT_OBJECTIVE_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return studentObjective;
    }

    @Override
    public StudentObjective read(Integer integer) {
        return null;
    }

    @Override
    public StudentObjective update(StudentObjective type) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}

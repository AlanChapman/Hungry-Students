package za.ac.cput.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import za.ac.cput.domain.Student;
import za.ac.cput.utils.DBUtils;

public class StudentRepository extends SQLiteOpenHelper implements IStudentRepository {

    public StudentRepository(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        onCreate(db);
    }

    @Override
    public Boolean login(Student student) {
        return null;
    }

    @Override
    public Boolean signUp(Student student) {
        return null;
    }

    @Override
    public Student updateStudentDetails(Student student) {
        return null;
    }

    @Override
    public Student getStudentDetails(String emailAddress) {
        return null;
    }

    @Override
    public long getCurrentStudentId(String emailAddress) {
        return 0;
    }

    @Override
    public String getCurrentStudentEmailAddress(long userId) {
        return null;
    }
}

package za.ac.cput.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

import za.ac.cput.domain.Student;
import za.ac.cput.utils.DBUtils;

public class StudentRepositoryImpl extends SQLiteOpenHelper implements IStudentRepository {

    public StudentRepositoryImpl(@Nullable Context context) {
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

    public Boolean insertData(String fullName, String emailAddress, String dateOfBirth, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("full_name", fullName);
        contentValues.put("email_address", emailAddress);
        contentValues.put("date_of_birth", dateOfBirth);
        contentValues.put("password", password);
        long result = MyDB.insert("student", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkEmail(String emailAddress) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from student where email_address = ?", new String[]{emailAddress});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkEmailPass(String emailAddress, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from student where email_address = ? and password = ?", new String[] {emailAddress,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @Override
    public Student create(Student type) {
        return null;
    }

    @Override
    public Student read(String s) {
        return null;
    }

    @Override
    public Student update(Student type) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Boolean login(Student student) {
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

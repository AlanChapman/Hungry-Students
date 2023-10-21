package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import za.ac.cput.domain.Objective;
import za.ac.cput.domain.Snack;
import za.ac.cput.domain.Student;
import za.ac.cput.repository.interfaces.ISnackRepository;
import za.ac.cput.utils.DBUtils;

public class SnackRepositoryImpl extends SQLiteOpenHelper  {

    public SnackRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_SNACK_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_TRANSACTION_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_POINT_BALANCE_HISTORY_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_SNACK_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_TRANSACTION_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_POINT_BALANCE_HISTORY_QUERY);
        onCreate(db);
    }


    public Snack create(Snack snack) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(DBUtils.COLUMN_SNACK_NAME, snack.getName());
        cv.put(DBUtils.COLUMN_SNACK_PRICE, snack.getPrice());
        cv.put(DBUtils.COLUMN_SNACK_CATEGORY, snack.getCategory());
        cv.put(DBUtils.COLUMN_SNACK_CALORIES, snack.getCalories());

        long result = db.insert(DBUtils.OBJECTIVE_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return snack;
    }

    public String buySnack(Student student, Snack snack) {
        return null;
    }


    public Snack read(int snackId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.SNACK_TABLE +
                " WHERE " + DBUtils.COLUMN_SNACK_ID + " = ?", new String[]{String.valueOf(snackId)});

        if(cursor.moveToFirst()) {
            int theSnackId = cursor.getInt(0);
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            String category = cursor.getString(3);
            int calories  = cursor.getInt(4);

            return new Snack(theSnackId, name, price, category, calories);
        }
        cursor.close();
        return null;    }

    public Snack update(Snack type) {
        return null;
    }

    public boolean delete(String s) {
        return false;
    }

}

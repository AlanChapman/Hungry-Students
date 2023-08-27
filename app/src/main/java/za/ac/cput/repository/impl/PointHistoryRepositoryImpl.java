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

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.PointBalanceHistory;
import za.ac.cput.repository.interfaces.IPointsHistoryRepository;
import za.ac.cput.utils.DBUtils;

public class PointHistoryRepositoryImpl extends SQLiteOpenHelper implements IPointsHistoryRepository {

    private final Context context;

    public PointHistoryRepositoryImpl(@Nullable Context context){
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_POINT_BALANCE_HISTORY_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_TRANSACTION_TABLE_QUERY);
        //db.execSQL(DBUtils.CREATE_SNACK_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_POINT_BALANCE_HISTORY_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_TRANSACTION_TABLE_QUERY);
        //db.execSQL(DBUtils.DROP_SNACK_TABLE_QUERY);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PointBalanceHistory create(PointBalanceHistory pointsHistory){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put(DBUtils.COLUMN_POINT_BALANCE_HISTORY_DATE, pointsHistory.getTitle());
//        cv.put(DBUtils.COLUMN_POINT_BALANCE_HISTORY_POINTS_BALANCE, pointsHistory.getTransactionId());
//        cv.put(DBUtils.COLUMN_POINT_BALANCE_HISTORY_STUDENT_ID, pointsHistory.getDescription());


        long result = db.insert(DBUtils.POINT_BALANCE_HISTORY_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return pointsHistory;

    }

    @RequiresApi (api = Build.VERSION_CODES.O)
    public PointBalanceHistory getPointsHistory(int pointsHistoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.POINT_BALANCE_HISTORY_TABLE +
                " WHERE " + DBUtils.COLUMN_POINT_BALANCE_HISTORY_ID + " = ?" , new String[]{String.valueOf(pointsHistoryId)});

        if(cursor.moveToFirst()) {
            String title = cursor.getString(0);
            String transactionId = cursor.getString(1);
            String description = cursor.getString(2);
            int points = cursor.getInt(3);

//            return new PointBalanceHistory.Builder()
//                    .setPointBalanceHistoryId()
//                    .setTitle(title)
//                    .setDescription(description)
//                    .setPoints(points)
//                    .build();

        }
        cursor.close();
        return null;
    }

    @Override
    public List<PointBalanceHistory> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<PointBalanceHistory> pointsHistoryList = new ArrayList<>();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + DBUtils.POINT_BALANCE_HISTORY_TABLE, null);

        while(cursor.moveToNext()){
            String title = cursor.getString(0);
            String transactionId = cursor.getString(1);
            String description = cursor.getString(2);
            int points = cursor.getInt(3);

//            PointBalanceHistory pointsHistory = new PointBalanceHistory.Builder()
//                    .setTitle(title)
//                    .setTransactionId(transactionId)
//                    .setDescription(description)
//                    .setPoints(points)
//                    .build();
//            pointsHistoryList.add(pointsHistory);
        }
        cursor.close();
        return null;
    }
}

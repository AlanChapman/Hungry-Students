package za.ac.cput.repository.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.Objective;
import za.ac.cput.repository.interfaces.IObjectiveRepository;
import za.ac.cput.utils.DBUtils;

public class ObjectiveRepositoryImpl extends SQLiteOpenHelper implements IObjectiveRepository {

    public ObjectiveRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        onCreate(db);
    }

    @Override
    public Objective create(Objective objective) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(DBUtils.COLUMN_OBJECTIVE_TITLE, objective.getTitle());
        cv.put(DBUtils.COLUMN_OBJECTIVE_DESCRIPTION, objective.getDescription());
        cv.put(DBUtils.COLUMN_OBJECTIVE_POINTS, objective.getPoints());

        long result = db.insert(DBUtils.OBJECTIVE_TABLE, null, cv);

        if(result == -1) {
            return null;
        }

        return objective;

    }

    @Override
    public Objective read(String s) {
        return null;
    }

    @Override
    public Objective update(Objective type) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Objective> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Objective> objectiveList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.OBJECTIVE_TABLE, null);


        int count = 0;
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            int points = cursor.getInt(3);

            Objective objective = new Objective.Builder()
                    .setObjectiveId(id)
                    .setTitle(title)
                    .setDescription(description)
                    .setPoints(points)
                    .build();

            count++;
            objectiveList.add(objective);
        }

        cursor.close();
        return objectiveList;

    }

}

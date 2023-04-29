package za.ac.cput.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

import za.ac.cput.domain.Objective;
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
    public Objective create(Objective type) {
        return null;
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
        return null;
    }

}

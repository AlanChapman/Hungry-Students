package za.ac.cput.repository.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import za.ac.cput.domain.Transaction;
import za.ac.cput.repository.interfaces.ITransactionRepository;
import za.ac.cput.utils.DBUtils;

public class TransactionRepositoryImpl extends SQLiteOpenHelper implements ITransactionRepository {

    public TransactionRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_TRANSACTION_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_TRANSACTION_TABLE_QUERY);
        onCreate(db);
    }


    @Override
    public Transaction create(Transaction type) {
        return null;
    }

    @Override
    public Transaction read(String s) {
        return null;
    }

    @Override
    public Transaction update(Transaction type) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

}

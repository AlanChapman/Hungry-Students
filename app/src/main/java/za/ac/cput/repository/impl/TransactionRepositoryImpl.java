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
import java.util.ArrayList;
import java.util.List;

import za.ac.cput.domain.Student;
import za.ac.cput.domain.StudentObjective;
import za.ac.cput.domain.Transaction;
import za.ac.cput.domain.TransactionType;
import za.ac.cput.repository.interfaces.ITransactionRepository;
import za.ac.cput.utils.DBUtils;

public class TransactionRepositoryImpl extends SQLiteOpenHelper implements ITransactionRepository {

    private final Context context;
    public TransactionRepositoryImpl(@Nullable Context context) {
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Warn", "Creating db");
        db.execSQL(DBUtils.CREATE_TRANSACTION_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.CREATE_POINT_BALANCE_HISTORY_TABLE_QUERY);
        //db.execSQL(DBUtils.CREATE_SNACK_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("Warn", "Updating db");
        db.execSQL(DBUtils.DROP_TRANSACTION_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_STUDENT_OBJECTIVE_TABLE_QUERY);
        db.execSQL(DBUtils.DROP_POINT_BALANCE_HISTORY_QUERY);
        //db.execSQL(DBUtils.DROP_SNACK_TABLE_QUERY);
        onCreate(db);
    }


    public Transaction createTransaction(Transaction transaction) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(DBUtils.COLUMN_TRANSACTION_RECIPIENT_ID, transaction.getRecipientId());
        cv.put(DBUtils.COLUMN_TRANSACTION_TITLE, transaction.getTitle());
        cv.put(DBUtils.COLUMN_TRANSACTION_SENDER_ID, transaction.getSenderId());
        cv.put(DBUtils.COLUMN_TRANSACTION_TYPE, transaction.getType().toString());
        cv.put(DBUtils.COLUMN_TRANSACTION_POINT_AMOUNT, transaction.getPointAmount());
        cv.put(DBUtils.COLUMN_TRANSACTION_CREATED_AT, transaction.getCreatedAt().toString());
        cv.put(DBUtils.COLUMN_TRANSACTION_STATUS, transaction.getStatus());
        cv.put(DBUtils.COLUMN_TRANSACTION_STUDENT_ID, transaction.getStudentId());

        long result = db.insert(DBUtils.TRANSACTION_TABLE, null, cv);


        if(result == -1) {
            return null;
        }

        System.out.println("CREATED TRANSACTION");
        return transaction;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Transaction> getAllTransactions(int studentId) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Transaction> transactions = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DBUtils.TRANSACTION_TABLE +
                " WHERE " + DBUtils.COLUMN_TRANSACTION_STUDENT_ID + " = ?", new String[]{String.valueOf(studentId)});


        System.out.println(cursor);
        System.out.println(cursor.getCount());
        int count = 0;

        while(cursor.moveToNext()) {
            int theTransactionId = cursor.getInt(0);
            String title = cursor.getString(1);
            int recipientId = cursor.getInt(2);
            int senderId = cursor.getInt(3);
            String type = cursor.getString(4);
            int pointAmount = cursor.getInt(5);
            int status = cursor.getInt(6);
            int theStudentId = cursor.getInt(7);


            //DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            //LocalDateTime createdAtFormatted = LocalDateTime.parse(createdAt, formatter);

            Transaction transaction = new Transaction(theTransactionId, title, recipientId, senderId, TransactionType.valueOf(type), pointAmount, status == 1 ? true : false, theStudentId);
            transactions.add(transaction);

            count++;

        }
        System.out.println(transactions);


        cursor.close();
        return transactions;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Transaction getTransaction(int transactionId, int studentId) {

        SQLiteDatabase db = this.getReadableDatabase();
        Transaction transaction = null;

        Cursor cursor = db.query(DBUtils.TRANSACTION_TABLE,
                new String[] {DBUtils.COLUMN_TRANSACTION_ID, DBUtils.COLUMN_TRANSACTION_TITLE, DBUtils.COLUMN_TRANSACTION_RECIPIENT_ID, DBUtils.COLUMN_TRANSACTION_SENDER_ID,
                        DBUtils.COLUMN_TRANSACTION_TYPE, DBUtils.COLUMN_TRANSACTION_POINT_AMOUNT, DBUtils.COLUMN_TRANSACTION_STATUS,
                        DBUtils.COLUMN_TRANSACTION_STUDENT_ID},
                DBUtils.COLUMN_TRANSACTION_ID + " = ? AND " + DBUtils.COLUMN_TRANSACTION_STUDENT_ID + " = ?",
                new String[]{String.valueOf(transactionId), String.valueOf(studentId)},//Where clause
                null, null, null);

        System.out.println(cursor);




        if(cursor.moveToNext()) {
            int theTransactionId = cursor.getInt(0);
            String title = cursor.getString(1);
            int recipientId = cursor.getInt(2);
            int senderId = cursor.getInt(3);
            String type = cursor.getString(4);
            int pointAmount = cursor.getInt(5);
            int status = cursor.getInt(6);
            int theStudentId = cursor.getInt(7);


            //DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            //LocalDateTime createdAtFormatted = LocalDateTime.parse(createdAt, formatter);

            transaction = new Transaction(theTransactionId, title, recipientId, senderId, TransactionType.valueOf(type), pointAmount, status == 1 ? true : false, theStudentId);

        }



        cursor.close();
        return transaction;
    }


    public Transaction update(Transaction type) {
        return null;
    }

 
    public boolean delete(String s) {
        return false;
    }

}

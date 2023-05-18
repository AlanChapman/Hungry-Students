package za.ac.cput.utils;

public class DBUtils {

    // Common Database attributes
    public static final String AUTHENTICATED_USER = "authenticatedUser";
    public static final String STUDENT_ID = "studentId";
    public static final String DATABASE_NAME = "hungrystudents.db";
    public static final int DATABASE_VERSION = 1;

    // Table name for User
    public static final String STUDENT_TABLE = "student";
    // Static attributes for User
    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_FULL_NAME = "full_name";
    public static final String COLUMN_STUDENT_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_STUDENT_DATE_OF_BIRTH = "date_of_birth";

    public static final String COLUMN_STUDENT_CREATED_AT = "created_at";
    public static final String COLUMN_STUDENT_POINTS_BALANCE = "point_balance";
    public static final String COLUMN_STUDENT_PASSWORD = "password";


    // Query for Creating Student Table
    public static final String CREATE_STUDENT_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + "(" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_FULL_NAME + " TEXT,"  + COLUMN_STUDENT_EMAIL_ADDRESS + " TEXT," + COLUMN_STUDENT_DATE_OF_BIRTH + " TEXT,"
            + COLUMN_STUDENT_POINTS_BALANCE + " INTEGER," + COLUMN_STUDENT_CREATED_AT + " TEXT," + COLUMN_STUDENT_PASSWORD + " TEXT)";

    public static final String DROP_STUDENT_TABLE_QUERY = "DROP TABLE IF EXISTS " + STUDENT_TABLE;

    public static final String CREATE_OBJECTIVE_TABLE_QUERY = "";
    public static final String DROP_OBJECTIVE_TABLE_QUERY = "";

    public static final String CREATE_TRANSACTION_TABLE_QUERY = "";
    public static final String DROP_TRANSACTION_TABLE_QUERY = "";

    public static final String CREATE_SNACK_TABLE_QUERY = "";
    public static final String DROP_SNACK_TABLE_QUERY = "";

}

package stem.recyclerview.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "sqlitetest";
    static final int DATABASE_VERSION = 2;

    static final String TBL_LOGIN_DETAIL = "tblLoginDetail";

    static final String CREATE_TABLE_LOGIN_DETAIL = "create table tblLoginDetail(_id integer primary key ," +
            "emailId varchar(100),password varchar(20),timestamp DATE DEFAULT CURRENT_TIMESTAMP);";

    static final String KEY_EMAIL_ID = "emailId";
    static final String KEY_PASSWORD = "password";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {

            try {

                db.execSQL(CREATE_TABLE_LOGIN_DETAIL);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "upgrading database from version" + oldVersion + "to" + newVersion + ", which will destroy all old data");

            db.execSQL("Drop Table if exists " + TBL_LOGIN_DETAIL);

            onCreate(db);

        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        DBHelper.close();
    }

    public boolean insertLogs(String email_id, String password) {

        ContentValues cv = new ContentValues();


        cv.put(KEY_EMAIL_ID, email_id);
        cv.put(KEY_PASSWORD, password);

        long id = db.insert(TBL_LOGIN_DETAIL, null, cv);

        return id > 0;

    }

    public boolean deleteLogs(long email_id) {
        return db.delete(TBL_LOGIN_DETAIL, KEY_EMAIL_ID + "=" + email_id, null) > 0;


    }

    public boolean updatePassword(String email_id, String password) {

        String where = KEY_EMAIL_ID + "='" + email_id + "'";

        ContentValues cv = new ContentValues();

        cv.put(KEY_PASSWORD, password);

        return db.update(TBL_LOGIN_DETAIL, cv, where, null) > 0;

    }


    public Cursor getUSer(String email_id) {

        String query = "select * from " + TBL_LOGIN_DETAIL + " where " + KEY_EMAIL_ID + "='" + email_id + "'";

        return
                db.rawQuery(query, null);
    }

    public Cursor getAllUser() {

        String query = "select * from " + TBL_LOGIN_DETAIL;
        ;

        return
                db.rawQuery(query, null);
    }

}

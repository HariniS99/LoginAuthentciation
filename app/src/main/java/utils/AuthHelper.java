package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuthHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "users.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_1 = "USERNAME";
    private static final String COL_2 = "PASSWORD";

    public AuthHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "( " +
                COL_1 + " TEXT primary key," +
                COL_2 + " TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, MD5.hashString(password));
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        if(!res.moveToNext()) {
            return false;
        }
        String user = res.getString(0);
        String pwd = res.getString(1);
        return (username.equalsIgnoreCase(user) && MD5.hashString(password).equals(pwd));
    }
}

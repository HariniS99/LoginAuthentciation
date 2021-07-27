package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT Primary Key,dob TEXT,age TEXT,contact TEXT,mail TEXT,education TEXT,address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int il) {
        DB.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertuserdate(String name, String dob ,String age,String contact,String mail,
                                  String education,String address ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dob", dob);
        contentValues.put("age", age);
        contentValues.put("contact", contact);
        contentValues.put("mail", mail);
        contentValues.put("education", education);
        contentValues.put("address", address);
        //contentValues.put("nationality",nationality);
        //contentValues.put("gender",gender);
        //contentValues.put("maritalstatus",maritalstatus);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdate(String name, String dob ,String age,String contact,String mail,String education,String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dob", dob);
        contentValues.put("age", age);
        contentValues.put("contact", contact);
        contentValues.put("mail", mail);
        contentValues.put("education", education);
        contentValues.put("address", address);
        //contentValues.put("nationality",nationality);
        //contentValues.put("gender",gender);
        //contentValues.put("maritalstatus",maritalstatus);
        Cursor cursor = DB.rawQuery("select * from  Userdetails where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteuserdate(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from  Userdetails where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from  Userdetails", null);
        return cursor;
    }
}



//public Cursor listAllData () {
//SQLiteDatabase db = this.getReadableDatabase();
//Cursor cursor = db.rawQuery("select "+COLUMN_ID+", "+COLUMN_NAME+", "+COLUMN_ID+" from "+TABLE_NAME, null);
//Cursor cursor=db.rawQuery("select name,dob,name from Userdetails",null);
//return cursor;
//}

// function for search data
//public Cursor searchData (String searchInput) {
//SQLiteDatabase db = this.getReadableDatabase();
//Cursor cursor = db.rawQuery( "select * from Userdetails where name = '"+searchInput+
// "'", null );
//return cursor;
//}
//}


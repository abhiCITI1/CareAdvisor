package info.androidhive.speechtotext;

/**
 * Created by Abhishek on 5/15/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by shraddhayeole on 5/15/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Doctor3.db";
    public static final String TABLE_NAME = "doctor_table3";
    public static final String COL_1 = "registrationNum";
    public static final String COL_2 = "name";
    public static final String COL_3 = "time";
    public static final String COL_4 = "contact";
    public static final String COL_5 = "address";
    public static final String COL_6 = "specialization";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (registrationNum INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,time TEXT,contact TEXT,address TEXT, specialization TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String registrationNum, String name,
                                String time , String contact,  String address , String specialization ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, registrationNum);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, time);
        contentValues.put(COL_4, contact);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, specialization);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(String addr) {
        System.out.println("location received inside getAllData" + addr);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + COL_2 + "," + COL_5 +  " from " + TABLE_NAME + " where "+ COL_5 +"=?",new String[]{"Nashik"});
        return res;
    }


}
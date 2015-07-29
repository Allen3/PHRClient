package cn.com.mars.allen.phrclient.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.com.mars.allen.phrclient.Beans.PersonInfo;

/**
 * Created by Allen on 2015/7/29.
 */
public class LOC_DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = Constants.LOC_DB_NAME;

    public LOC_DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + Constants.LOC_DB_TABLE_PERSON + " (" +
                Constants.PERSON_ID + " TEXT PRIMARY KEY," +
                Constants.NAME + " TEXT," +
                Constants.GENDER + " INTEGER," +
                Constants.AGE + " INTEGER," +
                Constants.PHONE + " TEXT," +
                Constants.VIP + " INTEGER," +
                Constants.BLOODTYPE + " INTEGER," +
                Constants.PASSWORD + " TEXT," +
                Constants.GROUP_ID + " INTEGER" +
                " )";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Constants.LOC_DB_TABLE_PERSON;
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void dropTable(String tableName) {
        String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
        db.close();
    }

    public void insertPersonInfo(PersonInfo personInfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.PERSON_ID, personInfo.getPerson_id());
        values.put(Constants.NAME, personInfo.getName());
        values.put(Constants.GENDER, personInfo.getGender());
        values.put(Constants.AGE, personInfo.getAge());
        values.put(Constants.PHONE, personInfo.getPhone());
        values.put(Constants.VIP, personInfo.getVip());
        values.put(Constants.BLOODTYPE, personInfo.getBloodType());
        values.put(Constants.PASSWORD, personInfo.getPassword());
        values.put(Constants.GROUP_ID, personInfo.getGroup_id());

        db.insert(Constants.LOC_DB_TABLE_PERSON, null, values);
        db.close();
    }
}

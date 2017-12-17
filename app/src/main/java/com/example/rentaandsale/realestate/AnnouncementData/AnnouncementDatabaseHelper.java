package com.example.rentaandsale.realestate.AnnouncementData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class AnnouncementDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AnnouncementManager.db";

    // Announcement table name
    private static final String TABLE_ANNOUNCEMENT = "announcement";

    // Announcement Table Columns names
    private static final String COLUMN_ANNOUNCEMENT_ID = "announcement_id";
    private static final String COLUMN_ANNOUNCEMENT_TITLE= "announcement_title";
    private static final String COLUMN_ANNOUNCEMENT_PRICE = "announcement_price";
    private static final String COLUMN_ANNOUNCEMENT_TYPE = "announcement_type";


    // create table sql query
    private String CREATE_ANNOUNCEMENT_TABLE = "CREATE TABLE " + TABLE_ANNOUNCEMENT + "("
            + COLUMN_ANNOUNCEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ANNOUNCEMENT_TITLE + " TEXT,"
            + COLUMN_ANNOUNCEMENT_PRICE + " TEXT," + COLUMN_ANNOUNCEMENT_TYPE + " TEXT" + ")";
//

    // drop table sql query
    private String DROP_ANNOUNCEMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_ANNOUNCEMENT;


    public AnnouncementDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ANNOUNCEMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop Announcement Table if exist
        db.execSQL(DROP_ANNOUNCEMENT_TABLE);

        // Create tables again
        onCreate(db);
    }
    public void addAnnouncement(Announcement announcement) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANNOUNCEMENT_TITLE, announcement.getAnnouncementTitle());
        values.put(COLUMN_ANNOUNCEMENT_PRICE, announcement.getAnnouncementPrice());
        values.put(COLUMN_ANNOUNCEMENT_TYPE, announcement.getAnnouncementType());
//
        // Inserting Row
        db.insert(TABLE_ANNOUNCEMENT, null, values);
        db.close();
    }

    public List<Announcement> getAllAnnouncement() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_ANNOUNCEMENT_ID,
                COLUMN_ANNOUNCEMENT_TITLE,
                COLUMN_ANNOUNCEMENT_PRICE,
                COLUMN_ANNOUNCEMENT_TYPE,
//
        };
        // sorting orders
        String sortOrder =
                COLUMN_ANNOUNCEMENT_TITLE + " ASC";
        List<Announcement> announcementList = new ArrayList<Announcement>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the announcement table
        //Cursor cursor = db.query("SELECT ");
        Cursor cursor = db.query(TABLE_ANNOUNCEMENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ANNOUNCEMENT_ID))));
                announcement.setAnnouncementTitle(cursor.getString(cursor.getColumnIndex(COLUMN_ANNOUNCEMENT_TITLE)));
                announcement.setAnnouncementPrice(cursor.getString(cursor.getColumnIndex(COLUMN_ANNOUNCEMENT_PRICE)));
                announcement.setAnnouncementType(cursor.getString(cursor.getColumnIndex(COLUMN_ANNOUNCEMENT_TYPE)));


                // Adding announcement record to list
                announcementList.add(announcement);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return announcement list
        return announcementList;
    }

    public void updateAnnouncement(Announcement announcement) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ANNOUNCEMENT_TITLE, announcement.getAnnouncementTitle());
        values.put(COLUMN_ANNOUNCEMENT_PRICE, announcement.getAnnouncementPrice());
        values.put(COLUMN_ANNOUNCEMENT_TYPE,  announcement.getAnnouncementType());
//


        // updating row
        db.update(TABLE_ANNOUNCEMENT, values, COLUMN_ANNOUNCEMENT_ID + " = ?",
                new String[]{String.valueOf(announcement.getAnnouncementId())});
        db.close();
    }

    public void deleteAnnouncement(Announcement announcement) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete announcement record by id
        db.delete(TABLE_ANNOUNCEMENT, COLUMN_ANNOUNCEMENT_ID + " = ?",
                new String[]{String.valueOf(announcement.getAnnouncementId())});
        db.close();
    }

    public boolean checkAnnouncement(String title) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ANNOUNCEMENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_ANNOUNCEMENT_TITLE + " = ?";

        // selection argument
        String[] selectionArgs = {title};


        Cursor cursor = db.query(TABLE_ANNOUNCEMENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}

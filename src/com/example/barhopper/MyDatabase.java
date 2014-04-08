package com.example.barhopper;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {
	// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BarDB";
 
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create bar table
        String CREATE_BAR_TABLE = "CREATE TABLE bar ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "name TEXT, "+
                "address TEXT, "+
                "hours TEXT)";
 
        // create BAR table
        db.execSQL(CREATE_BAR_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older bars table if existed
        db.execSQL("DROP TABLE IF EXISTS bar");
 
        // create bars table
        this.onCreate(db);
    }
    
 // Bars table name
    private static final String TABLE_BARS = "bars";
 
    // Bars Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_HOURS = "hours";
 
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_ADDRESS,KEY_HOURS};
    
    public void addBar(Bar bar){

    	// 1. get reference to writable DB
    	SQLiteDatabase db = this.getWritableDatabase();

    	// 2. create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, bar.getName()); // get name
    	values.put(KEY_ADDRESS, bar.getAddress()); // get address
    	values.put(KEY_HOURS, bar.getHours()); //get hours

    	// 3. insert
    	db.insert(TABLE_BARS, // table
        null, //nullColumnHack
        values); // key/value -> keys = column names/ values = column values

    	// 4. close
    	db.close(); 
    }
    
    public Bar getBar(int id) {
    	// 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_BARS, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build food object
        Bar bar = new Bar();
        bar.setId(Integer.parseInt(cursor.getString(0)));
        bar.setName(cursor.getString(1));
        bar.setAddress(cursor.getString(2));
        bar.setHours(cursor.getString(3));
     
        // 5. return bar
        return bar;
    }
    
    public List<Bar> getAllBars() {
    	List<Bar> bars = new LinkedList<Bar>();
    	 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BARS;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build bar and add it to list
        Bar bar = null;
        if (cursor.moveToFirst()) {
            do {
                bar = new Bar();
                bar.setId(Integer.parseInt(cursor.getString(0)));
                bar.setName(cursor.getString(1));
                bar.setAddress(cursor.getString(2));
                bar.setHours(cursor.getString(3));
  
                // Add bar to bars
                bars.add(bar);
            } while (cursor.moveToNext());
        }
  
        // return bars
        return bars;
    }
    
    public void deleteBar(Bar bar) {
    	// 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_BARS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(bar.getId()) }); //selections args
 
        // 3. close
        db.close();
    }
    
}

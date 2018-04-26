package com.kepler.studentportal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kepler.projectsupportlib.Logger;

import static com.kepler.studentportal.support.Constants.ID;
import static com.kepler.studentportal.support.Constants.IS_TECHNICAL;
import static com.kepler.studentportal.support.Constants.PROGRAM;
import static com.kepler.studentportal.support.Constants.PROGRAM_CATEGORY;
import static com.kepler.studentportal.support.Constants.PROGRAM_CATEGORY_ID;

/**
 * Created by kepler on 21/3/18.
 */

public class DatabaseManger extends SQLiteOpenHelper {


    public static final String TABLE_PROGRAM_CATEGORY = "tbl_program_category";
    public static final String TABLE_PROGRAM = "tbl_program";
    private static final String DATABASE_NAME = "ah_amity";
    private static final int DATABASE_VERSION = 1;
    /*CREATE_TABLE_PROGRAM_CATEGORY*/
    private static final String CREATE_TABLE_PROGRAM_CATEGORY =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PROGRAM_CATEGORY + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROGRAM_CATEGORY + " TEXT,"
                    + IS_TECHNICAL + " INTEGER"
                    + ")";
    /*CREATE_TABLE_PROGRAM*/
    private static final String CREATE_TABLE_PROGRAM =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PROGRAM + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PROGRAM + " TEXT,"
                    + PROGRAM_CATEGORY_ID + " INTEGER"
                    + ")";

    private static DatabaseManger sInstance;

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private DatabaseManger(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseManger getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseManger(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Logger.e("onCreate(SQLiteDatabase db)");
        db.execSQL(CREATE_TABLE_PROGRAM_CATEGORY);
        db.execSQL(CREATE_TABLE_PROGRAM);
        insertProgramCategory(db);
        insertProgram(db);
    }

    private void insertProgram(SQLiteDatabase db) {
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Statistics', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Sc. (Hons) Chemistry', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Sc. (Hons) Physics', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc.(Applied Physics)', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc. (Applied Chemistry)', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc. (Applied Mathematics)', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Master of Statistics', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc.(Environmental Sciences)', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Architecture', '2')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Interior Design', '2')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Sc. (Hons) Biotechnology', '3')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Biotechnology)', '3')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc. (Biotechnology)', '3')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M. Sc. (Microbiology)', '3')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Biotechnology)', '3')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Com. (Hons)', '4')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Master of Commerce', '4')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Journalism & Mass Communication)', '5')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Journalism & Mass Communication)', '5')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Journalism & Mass Communication)', '5')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Film & TV Production)', '5')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Advertising & Marketing Management)', '5')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Sc. (IT)', '6')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'BCA 11048', '6')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MCA', '6')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Aerospace Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Civil Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Computer Science & Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Electronics & Communication Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Information Technology)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Mechanical Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Electrical & Electronics Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Computer Science & Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Civil Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Electronics & Communication Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Tech (Mechanical Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Computer Science & Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Electronics & Communication Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Automobile Engg.)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Mechanical/Automobile Engg)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Optoelectronics & Optical Communication)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Tech (Embedded System Technology)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Sc.( Electronics)', '7')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Hons) - English', '8')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (English)', '8')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Des. (Fashion Design)', '9')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Des. (Fashion Design)', '9')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Fashion Retail Management)', '9')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'BFA', '10')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'BFA (Animation)', '10')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MFA (Applied Arts)', '10')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MFA (Painting)', '10')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Hotel Management', '11')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A., LL.B. (Hons)', '12')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Com., LL.B. (Hons)', '12')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'BBA LL.B. (Hons)', '12')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'LL.B.', '12')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'LL.M.', '12')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Hons) - History', '13')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Hons) - Philosophy', '13')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Hons) - Political Science', '13')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Social Work', '13')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Master of Social Work', '13')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'BBA', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (International Business)', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (Marketing & Sales)', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (HR)', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (Education Management)', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (Executive) Full Time', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (Executive) Part Time', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'MBA (Media Management)', '14')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Bachelor of Pharmacy', '15')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Biotechnology Science', '15')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Pharm (Pharmaceutics)', '15')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M Pharm (Pharmacology)', '15')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Pharm. (Drug Regulatory Affairs)', '15')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Ed.', '16')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Ed. (Spl. Ed.)', '16')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Ed.', '16')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Ed. (Spl. Ed.)', '16')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.Ed General', '16')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Counselling Psychology)', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.A. (Clinical Psychology)', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'PG Diploma in Counselling Psychology', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Phil ( Clinical Psychology )', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'Professional Diploma in Clinical Psychology', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'M.Phil (Child & Adolescent Psychology)', '17')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM+" ("+ID+", "+PROGRAM+", "+PROGRAM_CATEGORY_ID+") VALUES (NULL, 'B.A. (Hons) - Applied Psychology', '17')");
    }

    private void insertProgramCategory(SQLiteDatabase db) {
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'APPLIED SCIENCES', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'ARCHITECTURE', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'BIOTECH', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'COMMERCE', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'COMMUNICATION', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'COMPUTER SCIENCE/IT', '1')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'ENGINEERING', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'ENGLISH LITERATURE', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'FASHION', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'FINE ARTS', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'HOSPITALITY', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'LAW', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'LIBERAL ARTS', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'MANAGEMENT', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'PHARMACY', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'EDUCATION', '0')");
        db.execSQL("INSERT INTO "+TABLE_PROGRAM_CATEGORY+" ("+ID+", "+PROGRAM_CATEGORY+", "+IS_TECHNICAL+") VALUES (NULL, 'PSYCHOLOGY & BEHAVIOURAL SCIENCE', '0')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAM_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAM);

        // Create tables again
        onCreate(db);
    }

    //    ************************* INSERT

    public long insert(String table_name, ContentValues contentValues) {
        return insert(table_name, null, contentValues);
    }

    public long insert(String table_name, String nullColumnHack, ContentValues contentValues) {
        // getQuery writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        // insert row
        long id = db.insert(table_name, nullColumnHack, contentValues);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    //    ************************* COUNT

    public int getCount(String table_name, String[] selectionArgs) {
        String countQuery = "SELECT  * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, selectionArgs);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public Cursor get(String table_name, String whereClause, String[] selectionArgs) {
        String countQuery = "SELECT  * FROM " + table_name + ((whereClause == null) ? "" : whereClause);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, selectionArgs);

        // return count
        return cursor;
    }

    public Cursor get(String query) {
        String countQuery = "SELECT  * FROM " + query;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor;
    }


    public Cursor getQuery(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // return count
        return cursor;
    }

    public int getCount(String table_name) {
        return getCount(table_name, null);
    }

    //    ************************* UPDATE

    public int update(String table_name, ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating row
        return db.update(table_name, values, whereClause, whereArgs);
    }

    //    ************************* DELETE

    public void delete(String table_name, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name, whereClause, whereArgs);
        db.close();
    }

    public void delete(String table_name) {
        delete(table_name, null, null);
    }
}
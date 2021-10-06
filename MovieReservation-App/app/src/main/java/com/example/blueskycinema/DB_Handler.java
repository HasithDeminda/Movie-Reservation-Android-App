package com.example.blueskycinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.blueskycinema.Hasith.Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DB_Handler extends SQLiteOpenHelper {

    Context context;
    
    //Database name
    public static final String DATABASE_NAME = "Movie.db";

    //No of seats in the theater
    public static final int NO_OF_SEATS = 200;
    public static final int NO_OF_BOX_SEATS = 15;

    //Movie table
    public static final String MOVIE_TABLE = "movies_table";
    public static final String MOVIE_COLUMN_ID = "movieID";
    public static final String MOVIE_COLUMN_NAME = "name";
    public static final String MOVIE_COLUMN_GENRE = "genre";
    public static final String MOVIE_COLUMN_DURATION = "duration";
    public static final String MOVIE_COLUMN_YEAR = "year";
    public static final String MOVIE_COLUMN_DESCRIPTION = "description";
    public static final String MOVIE_COLUMN_CAST = "castTEXT";
    public static final String MOVIE_COLUMN_POSTER = "poster";
    public static final String MOVIE_COLUMN_COVER = "coverImage";
    public static final String MOVIE_COLUMN_START_DATE = "start_date";
    public static final String MOVIE_COLUMN_END_DATE = "end_date";
    public static final String MOVIE_COLUMN_TRAILER = "trailerLink";
    public static final String MOVIE_COLUMN_PRICE = "ticketPrice";

    //User table
    public static final String USER_TABLE = "users_table";
    public static final String USER_COLUMN_ID = "userID";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_CONTACT = "contact";
    public static final String USER_COLUMN_IMAGE = "image";

    //Admin table
    public static final String ADMIN_TABLE = "admin_table";
    public static final String ADMIN_COLUMN_ID = "adminID";
    public static final String ADMIN_COLUMN_NAME = "name";
    public static final String ADMIN_COLUMN_USERNAME = "username";
    public static final String ADMIN_COLUMN_PASSWORD = "password";

    //Booking table
    public static final String BOOKING_TABLE = "booking_table";
    public static final String BOOKING_COLUMN_N_TICKETS = "normalTickets";
    public static final String BOOKING_COLUMN_BOX_TICKETS = "boxTickets";
    public static final String BOOKING_COLUMN_DATE = "date";
    public static final String BOOKING_COLUMN_TIME = "time";
    public static final String BOOKING_COLUMN_AMOUNT = "amount";
    public static final String BOOKING_COLUMN_MOVIE_ID = "movieID";

    //Discount table
    public static final String DISCOUNT_TABLE = "discount_table";
    public static final String DISCOUNT_COLUMN_ID = "discountID";
    public static final String DISCOUNT_COLUMN_CODE = "couponCode";
    public static final String DISCOUNT_COLUMN_PERCENTAGE = "percentage";


    //Reviews table
    public static final String REVIEWS_TABLE = "reviews_table";
    public static final String REVIEWS_COLUMN_ID = "revID";
    public static final String REVIEWS_COLUMN_USERID = "userID";
    public static final String REVIEWS_COLUMN_MESSAGE = "message";
    public static final String REVIEWS_COLUMN_DATE = "date";

    //Rating table
    public static final String RATING_TABLE = "rating_table";
    public static final String RATING_COLUMN_ID = "ratingID";
    public static final String RATING_COLUMN_USERID = "userID";
    public static final String RATING_COLUMN_COUNT = "count";
    public static final String RATING_COLUMN_DATE = "date";

    //Favorite table
    public static final String FAVORITE_TABLE = "favorite_table";
    public static final String FAVORITE_COLUMN_ID = "favID";
    public static final String FAVORITE_COLUMN_USERID = "userID";
    public static final String FAVORITE_COLUMN_MOVIE_ID = "movieID";



    public DB_Handler(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create movie table
        String create_movies_table =
                "CREATE TABLE "+MOVIE_TABLE+" ( "+
                        MOVIE_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        MOVIE_COLUMN_NAME+" TEXT, "+
                        MOVIE_COLUMN_GENRE+" TEXT, "+
                        MOVIE_COLUMN_DURATION+" TEXT, "+
                        MOVIE_COLUMN_YEAR+" INTEGER, "+
                        MOVIE_COLUMN_DESCRIPTION+" TEXT, "+
                        MOVIE_COLUMN_CAST+" TEXT, "+
                        MOVIE_COLUMN_POSTER+" BLOB, "+
                        MOVIE_COLUMN_COVER+" BLOB, "+
                        MOVIE_COLUMN_START_DATE+" TEXT, "+
                        MOVIE_COLUMN_END_DATE+" TEXT, "+
                        MOVIE_COLUMN_TRAILER+" TEXT, "+
                        MOVIE_COLUMN_PRICE+" REAL)";

        //create user table
        String create_users_table =
                "CREATE TABLE "+USER_TABLE+" ( "+
                        USER_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        USER_COLUMN_USERNAME+" TEXT, "+
                        USER_COLUMN_EMAIL+" TEXT, "+
                        USER_COLUMN_PASSWORD+" TEXT, "+
                        USER_COLUMN_CONTACT+" TEXT, "+
                        USER_COLUMN_IMAGE+" BLOB)";

        //create admin table
        String create_admin_table =
                "CREATE TABLE "+ADMIN_TABLE+" ( "+
                        ADMIN_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        ADMIN_COLUMN_NAME+" TEXT, "+
                        ADMIN_COLUMN_USERNAME+" TEXT, "+
                        ADMIN_COLUMN_PASSWORD+" TEXT)";

        //create booking table
        String create_booking_table =
                "CREATE TABLE "+BOOKING_TABLE+" ( "+
                        BOOKING_COLUMN_N_TICKETS+" INTEGER PRIMARY KEY, " +
                        BOOKING_COLUMN_BOX_TICKETS+" INTEGER, "+
                        BOOKING_COLUMN_DATE+" DATE, "+
                        BOOKING_COLUMN_TIME+" DATETIME, "+
                        BOOKING_COLUMN_AMOUNT+" REAL, "+
                        BOOKING_COLUMN_MOVIE_ID+" INTEGER)";

        //create discount table
        String create_discount_table =
                "CREATE TABLE "+DISCOUNT_TABLE+" ( "+
                        DISCOUNT_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        ADMIN_COLUMN_NAME+" TEXT, "+
                        DISCOUNT_COLUMN_CODE+" TEXT, "+
                        DISCOUNT_COLUMN_PERCENTAGE+" INTEGER)";

        //create review table
        String create_reviews_table =
                "CREATE TABLE "+REVIEWS_TABLE+" ( "+
                        REVIEWS_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        REVIEWS_COLUMN_USERID+" INTEGER, "+
                        REVIEWS_COLUMN_MESSAGE+" TEXT, "+
                        REVIEWS_COLUMN_DATE+" DATE)";

        //create rating table
        String create_rating_table =
                "CREATE TABLE "+RATING_TABLE+" ( "+
                        RATING_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        RATING_COLUMN_USERID+" INTEGER, "+
                        RATING_COLUMN_COUNT+" INTEGER, "+
                        RATING_COLUMN_DATE+" DATE)";

        //create favorite table
        String create_favorite_table =
                "CREATE TABLE "+FAVORITE_TABLE+" ( "+
                        FAVORITE_COLUMN_ID+" INTEGER PRIMARY KEY, " +
                        FAVORITE_COLUMN_USERID+" INTEGER, "+
                        FAVORITE_COLUMN_MOVIE_ID+" INTEGER)";


        //execute tables
        try {
            db.execSQL(create_movies_table);
            db.execSQL(create_users_table);
            db.execSQL(create_admin_table);

            db.execSQL(create_booking_table);
            db.execSQL(create_discount_table);

            db.execSQL(create_reviews_table);
            db.execSQL(create_rating_table);
            db.execSQL(create_favorite_table);

//            Toast.makeText(context, "Table created successfully!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
//            Toast.makeText(context, "Table creation failed!:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {

    }

    //Zaid function implementation123



    //Imasha function implementation




    //Hasith function implementation

    //Insert Data
    public long addMovie(String MovieName, String Duration, String Year, String Genre, String Description, String Cast, String Trailer, String StartDate, String EndDate, String Tickets){
    //gets the data repository in write mode
        SQLiteDatabase myDB = getWritableDatabase();



    //create a new map of values, where column names the keys


        ContentValues values = new ContentValues();
        values.put(MOVIE_COLUMN_NAME, MovieName);
        values.put(MOVIE_COLUMN_DURATION, Duration);
        values.put(MOVIE_COLUMN_YEAR, Year);
        values.put(MOVIE_COLUMN_GENRE, Genre);
        values.put(MOVIE_COLUMN_DESCRIPTION, Description);

        values.put(MOVIE_COLUMN_CAST, Cast);

//        try {
//            FileInputStream fs = new FileInputStream(poster);
//            byte[] imgbyte = new byte[fs.available()];
//            fs.read(imgbyte);
//
//            values.put(MOVIE_COLUMN_POSTER, imgbyte);
//
//
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

        values.put(MOVIE_COLUMN_TRAILER, Trailer);
        values.put(MOVIE_COLUMN_START_DATE, StartDate);
        values.put(MOVIE_COLUMN_END_DATE, EndDate);
        values.put(MOVIE_COLUMN_PRICE, Tickets);



    //Insert the new raw, returning primary key value of the new raw
        long newMovieId = myDB.insert(MOVIE_TABLE, null, values);
        return newMovieId;
    }


    //Retrieve Data
    public ArrayList<Model> getAllData(String orderBy) {
        ArrayList<Model> arrayList = new ArrayList<>();

        //Select all info in database
        String selectQuery = "SELECT * FROM " + MOVIE_TABLE + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Get the data from columns
        if (cursor.moveToNext()) {
            do {
                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DURATION)),
                        ""+cursor.getInt(cursor.getColumnIndex(MOVIE_COLUMN_YEAR)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_GENRE)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_CAST)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_TRAILER)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_START_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_END_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_PRICE))

                );
                arrayList.add(model);
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    //Update Data
    public int updateInfo(Model model){
        //gets the data repository in write mode
        SQLiteDatabase myDB = getWritableDatabase();



        //create a new map of values, where column names the keys
        ContentValues values = new ContentValues();

        values.put(MOVIE_COLUMN_NAME, model.getMovieName());
        values.put(MOVIE_COLUMN_DURATION, model.getDuration());
        values.put(MOVIE_COLUMN_YEAR, model.getYear());
        values.put(MOVIE_COLUMN_GENRE, model.getGenre());
        values.put(MOVIE_COLUMN_DESCRIPTION, model.getDescription());

        //Update the new raw, returning primary key value of the new raw
        int status = myDB.update(MOVIE_TABLE, values, MOVIE_COLUMN_ID +" =? ", new String[]{String.valueOf(model.getMovieId())});
        myDB.close();
        return status;
    }

    //get a single movie info
    public Model getSingleMovie(int id){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(MOVIE_TABLE, new String[]{
                MOVIE_COLUMN_ID,
                MOVIE_COLUMN_NAME,
                MOVIE_COLUMN_GENRE,
                MOVIE_COLUMN_DURATION,
                MOVIE_COLUMN_YEAR,
                MOVIE_COLUMN_DESCRIPTION,
                MOVIE_COLUMN_CAST,
                MOVIE_COLUMN_POSTER,
                MOVIE_COLUMN_COVER,
                MOVIE_COLUMN_START_DATE,
                MOVIE_COLUMN_END_DATE,
                MOVIE_COLUMN_TRAILER,
                MOVIE_COLUMN_PRICE,


        },MOVIE_COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        Model MModel;
        if (cursor != null){
            cursor.moveToFirst();
            MModel = new Model(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10)
            );
            return MModel;
        }
        return null;
    }



    //Delete a movie
    public void deleteInfo (String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MOVIE_TABLE, MOVIE_COLUMN_ID + " = ? ", new String[]{id});
        db.close();
    }


    //Login & Registration
    public boolean insertData(String un, String email, String contact, String pw){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(USER_COLUMN_USERNAME, un);
        val.put(USER_COLUMN_EMAIL, email);
        val.put(USER_COLUMN_CONTACT, contact);
        val.put(USER_COLUMN_PASSWORD, pw);

        long result = myDB.insert(USER_TABLE, null, val);
        if (result == -1)
            return false;
        else
            return true;
    }

    //check username already exists
    public boolean checkUsername(String un){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM "+ USER_TABLE + " WHERE " + USER_COLUMN_USERNAME + " = ? ", new String[]{un});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //validate logins
    public boolean validateLogin(String un, String pw){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + USER_COLUMN_USERNAME + " = ? AND " + USER_COLUMN_PASSWORD + " = ? ", new String[]{un, pw});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    //Janani function implementation

}

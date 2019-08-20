package com.example.movie;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.movie.movie.Search;
import java.util.ArrayList;
import java.util.List;

public class OpenDBHelper extends SQLiteOpenHelper {
    public  static final String DATA_BASE_NAME = "movies.db";
    String TABLE_NAME = "movies_table";
    String COL_1 = "ID";
    String COL_2 = "TITLE";
    String COL_3 = "YEAR";
    String COL_4 = "POSTER";

    public OpenDBHelper( Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = " CREATE TABLE " + TABLE_NAME + " (" + COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT," +
                COL_4 + " TEXT)";
        db.execSQL(CREATE_TABLE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(List<Search> search) {
        SQLiteDatabase db = this.getWritableDatabase();
        for(int j = 0; j < search.size(); j++) {
            if(search.get(j).getTitle().contains("'")){
                search.get(j).setTitle(search.get(j).getTitle().replaceAll("'","''"));
            }
        }
        for (int i = 0; i < search.size(); i++) {
            String insertQuery = "INSERT INTO " + TABLE_NAME +
                    "(" + COL_2 + "," + COL_3 + "," + COL_4 + ")" +
                    "VALUES( '" + search.get(i).getTitle() + "' , " +
                    "'" + search.get(i).getYear() + "', " +
                    "'" + search.get(i).getPoster() + "' )";
            db.execSQL(insertQuery);
        }
        db.close();
    }

    public List<Search> getData(String s){
        String GET_DATA_QUERY = "SELECT TITLE,YEAR,POSTER FROM " + TABLE_NAME+" WHERE TITLE LIKE '%"+
                s+"%'";

        List<Search> search = new ArrayList<>();
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_DATA_QUERY, null);

        while(cursor.moveToNext()){
            Search se = new Search();
            se.setTitle(cursor.getString(0));
           se.setYear(cursor.getString(1));
            se.setPoster(cursor.getString(2));
            search.add(se);
        }
        db.close();
        return search;
    }
}

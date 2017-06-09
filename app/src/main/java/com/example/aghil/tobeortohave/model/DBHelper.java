package com.example.aghil.tobeortohave.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aghil.tobeortohave.ItemListActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aghil on 16/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    private static String DB_NAME = "base8.db";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }
    public void openDataBase() throws SQLException, IOException {
        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(!dbExist){
            this.getReadableDatabase();
            try {
                // Copy the database in assets to the application database.
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e){
            //database doesn't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }
    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<DetailItem> getAllEvents() {
        Cursor c = myDataBase.rawQuery("SELECT * FROM events", null);

        List<DetailItem> ln = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()){
            ln.add(new DetailItem(c.getString(1),c.getString(0),c.getDouble(2),c.getDouble(3),c.getString(4)));
            c.moveToNext();
        }

        c.close();

        return ln;
    }
    public List<Graph> getAllInfoGraph() {
        Cursor c = myDataBase.rawQuery("SELECT * FROM graph", null);
        List<Graph> ln = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()){
            ln.add(new Graph(c.getString(0).trim().split(" "),c.getInt(1)));
            c.moveToNext();
        }

        c.close();

        return ln;
    }

    public List<Livraison> getAllLiv() {
        Cursor c = myDataBase.rawQuery("SELECT * FROM liv", null);

        List<Livraison> ln = new ArrayList<>();
        c.moveToFirst();
        while(!c.isAfterLast()){
            ln.add(new Livraison(c.getInt(0),c.getString(1),c.getInt(2), ItemListActivity.getCurrentTime()));
            c.moveToNext();
        }

        c.close();

        return ln;
    }
}

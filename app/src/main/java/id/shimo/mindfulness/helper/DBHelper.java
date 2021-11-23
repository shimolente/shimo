package id.shimo.mindfulness.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

import id.shimo.mindfulness.DetailActivity;
import id.shimo.mindfulness.Home;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "db_jornal";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tb_journal";
    private static final String ID_COLUMN = "id";
    private static final String BEST_THING_COLUMN = "bestThing";
    private static final String WORST_THING_COLUMN = "worstThing";
    private static final String RATE_COLUMN = "rate";
    private static final String WELLNESS_COLUMN = "wellness";
    private static final String DID_COLUMN = "did";
    private static final String DATETIME_COLUMN = "datetime";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                +TABLE_NAME+"("
                +ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +BEST_THING_COLUMN+" TEXT, "
                +WORST_THING_COLUMN+" TEXT, "
                +RATE_COLUMN+" TEXT, "
                +WELLNESS_COLUMN+" TEXT, "
                +DID_COLUMN+" TEXT, "
                +DATETIME_COLUMN+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertJournal (String bestThing, String worstThing, String rate, String wellness, String did, String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BEST_THING_COLUMN, bestThing);
        contentValues.put(WORST_THING_COLUMN, worstThing);
        contentValues.put(RATE_COLUMN, rate);
        contentValues.put(WELLNESS_COLUMN, wellness);
        contentValues.put(DID_COLUMN, did);
        contentValues.put(DATETIME_COLUMN, datetime);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor readJournals() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = ("SELECT*FROM tb_journal ORDER BY id DESC");
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public void deleteJournal(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.delete(TABLE_NAME, ID_COLUMN + "=" + id, null) > 0){
            Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateJournal (Integer id, String bestThing, String worstThing, String rate, String wellness, String did, String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BEST_THING_COLUMN, bestThing);
        contentValues.put(WORST_THING_COLUMN, worstThing);
        contentValues.put(RATE_COLUMN, rate);
        contentValues.put(WELLNESS_COLUMN, wellness);
        contentValues.put(DID_COLUMN, did);
        contentValues.put(DATETIME_COLUMN, datetime);
        if(db.update(TABLE_NAME, contentValues, ID_COLUMN + "=" + id, null) > 0){
            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);
        }else {
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}

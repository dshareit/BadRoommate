package android.bigranch.com.database;

import android.bigranch.com.database.TaskDbSchema.TaskTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TaskTable.NAME +
                "(" + " _id integer primary key autoincrement, " +
                TaskTable.Cols.UUID +
                ", " + TaskTable.Cols.TITLE +
                ", " + TaskTable.Cols.DATE +
                ", " + TaskTable.Cols.SOLVED + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

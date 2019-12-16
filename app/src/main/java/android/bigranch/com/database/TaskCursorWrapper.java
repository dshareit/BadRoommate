package android.bigranch.com.database;

import android.bigranch.com.Task;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import static android.bigranch.com.database.TaskDbSchema.*;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask() {
        String uuidString = getString(getColumnIndex(TaskTable.Cols.UUID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TaskTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(TaskTable.Cols.SOLVED));

        Task task = new Task(UUID.fromString(uuidString));
        task.setTitle(title);
        task.setDate(new Date(date));
        task.setSolved(isSolved != 0);

        return task;
    }
}

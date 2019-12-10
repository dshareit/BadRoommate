package android.bigranch.com;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskLab {
    private static TaskLab sTaskLab;

    public void addTask(Task t) {
        mTasks.add(t);
    }

    private List<Task> mTasks;

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }

    private TaskLab(Context context) {
        mTasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return mTasks;
    }

    public Task getTask(UUID id) {
        for (Task task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
}

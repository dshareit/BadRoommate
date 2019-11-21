package android.bigranch.com;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class RoommateActivity extends SingleFragmentActivity {

    private static final String EXTRA_TASK_ID = "android.bigranch.com.task_id";

    public static Intent newIntent(Context packageContext, UUID taskId){
        Intent intent = new Intent(packageContext, RoommateActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(taskId);

    }
}

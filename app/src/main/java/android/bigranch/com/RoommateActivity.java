package android.bigranch.com;

import androidx.fragment.app.Fragment;

public class RoommateActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }
}

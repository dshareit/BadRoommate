package android.bigranch.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListFragment extends Fragment {
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mTaskRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return  view;

    }

    private void updateUI() {
        TaskLab taskLab = TaskLab.get(getActivity());
        List<Task> tasks = taskLab.getTasks();

        mAdapter = new TaskAdapter(tasks);
        mTaskRecyclerView.setAdapter(mAdapter);
    }

    private abstract class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Task mTask;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent, int layout) {
            super(inflater.inflate(layout, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.task_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.task_date);

        }

        public void bind(Task task) {
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mDateTextView.setText(mTask.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), mTask.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class RegularTaskHolder extends TaskHolder {
        public RegularTaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_task);
        }
    }

    private class LandlordTaskHolder extends TaskHolder {
        private Button mContactLandlordButton;

        public LandlordTaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent, R.layout.list_item_task_landlord);
        }

        @Override
        public void bind(Task task) {
            super.bind(task);

                mContactLandlordButton = (Button) itemView.findViewById(R.id.contact_landord_button);
                mContactLandlordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Landlord was contacted for " + mTask.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task>  mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            if (viewType == 1) {
                return new LandlordTaskHolder(layoutInflater, parent);
            }
            return new RegularTaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mTasks.get(position).isRequiresLandlord()) {
                return 1;
            }
            return 0;
        }
    }
}

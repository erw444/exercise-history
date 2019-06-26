package com.erw.android.exercisehistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erw.android.exercisehistory.database.ExerciseHistoryEntity;
import com.erw.android.exercisehistory.database.ExerciseHistoryEntityWithExerciseName;
import com.erw.android.exercisehistory.database.ExerciseName;

import java.util.List;

public class ExerciseHistoryAdapter extends RecyclerView.Adapter<ExerciseHistoryAdapter.ExerciseHistoryViewHolder>{
    class ExerciseHistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView exerciseHistoryViewHolder;

        private ExerciseHistoryViewHolder(View itemView) {
            super(itemView);
            exerciseHistoryViewHolder = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<ExerciseHistoryEntityWithExerciseName> mExerciseHistory; // Cached copy of words

    ExerciseHistoryAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ExerciseHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ExerciseHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExerciseHistoryViewHolder holder, int position) {
        if (mExerciseHistory != null) {
            ExerciseHistoryEntityWithExerciseName current = mExerciseHistory.get(position);
            holder.exerciseHistoryViewHolder.setText(current.exerciseName.getExerciseName());
        } else {
            // Covers the case of data not being ready yet.
            holder.exerciseHistoryViewHolder.setText("No Exercises");
        }
    }

    void setExerciseHistory(List<ExerciseHistoryEntityWithExerciseName> exerciseHistory){
        mExerciseHistory = exerciseHistory;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mExerciseHistory != null)
            return mExerciseHistory.size();
        else return 0;
    }
}

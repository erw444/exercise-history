//package com.erw.android.exercisehistory;
//
//import android.arch.lifecycle.LiveData;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.TextView;
//
//import com.erw.android.exercisehistory.database.ExerciseName;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExerciseNamesAdapter extends ArrayAdapter<ExerciseName> {
//
//    private final LayoutInflater mInflater;
//    private List<ExerciseName> mExerciseNames; // Cached copy of words
//
//    ExerciseNamesAdapter(Context context, int viewResourceId, ArrayList<ExerciseName> exerciseNames) {
//        super(context, viewResourceId, exerciseNames);
//        mInflater = LayoutInflater.from(context);
//    }
//
//    @Override
//    public ExerciseNamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
//        return new ExerciseNamesViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ExerciseNamesViewHolder holder, int position) {
//        if (mExerciseNames != null) {
//            ExerciseName current = mExerciseNames.get(position);
//            holder.exerciseNamesViewHolder.setText(current.getExerciseName());
//        } else {
//            // Covers the case of data not being ready yet.
//            holder.exerciseNamesViewHolder.setText("No Exercise");
//        }
//    }
//
//    void setExerciseNames(List<ExerciseName> exerciseNames){
//        mExerciseNames = exerciseNames;
//        notifyDataSetChanged();
//    }
//
//    // getItemCount() is called many times, and when it is first called,
//    // mWords has not been updated (means initially, it's null, and we can't return null).
//    @Override
//    public int getItemCount() {
//        if (mExerciseNames != null)
//            return mExerciseNames.size();
//        else return 0;
//    }
//}

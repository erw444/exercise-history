package com.erw.android.exercisehistory.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.erw.android.exercisehistory.ExerciseHistory;

import java.util.List;

public class ExerciseHistoryRepository {
    private ExerciseHistoryDao mExerciseHistoryDao;
    private LiveData<List<ExerciseHistoryEntity>> mExerciseHistory;

    public ExerciseHistoryRepository(Application app){
        AppDatabase db = AppDatabase.getInstance(app);
        mExerciseHistoryDao = db.getExerciseHistoryDao();
        mExerciseHistory = mExerciseHistoryDao.loadExerciseHistory();
    }

    public LiveData<List<ExerciseHistoryEntity>> getExerciseHistory(){
        return mExerciseHistory;
    }

    public void insert(ExerciseHistoryEntity exerciseEntry){
        new insertAsyncTask(mExerciseHistoryDao).execute(exerciseEntry);
    }

    private static class insertAsyncTask extends AsyncTask<ExerciseHistoryEntity, Void, Void>{
        private ExerciseHistoryDao mAsyncTaskDao;

        insertAsyncTask(ExerciseHistoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ExerciseHistoryEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

package com.erw.android.exercisehistory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.erw.android.exercisehistory.database.ExerciseHistoryEntity;
import com.erw.android.exercisehistory.database.ExerciseHistoryRepository;

import java.util.List;

public class ExerciseHistoryViewModel extends AndroidViewModel {
    private ExerciseHistoryRepository mRepo;
    private LiveData<List<ExerciseHistoryEntity>> mExerciseHistory;

    public ExerciseHistoryViewModel(Application app){
        super(app);
        mRepo = new ExerciseHistoryRepository(app);
        mExerciseHistory = mRepo.getExerciseHistory();
    }

    public LiveData<List<ExerciseHistoryEntity>> getExerciseHistory() {
        return mExerciseHistory;
    }

    public void insert(ExerciseHistoryEntity exerciseEntity){
        mRepo.insert(exerciseEntity);
    }

}
